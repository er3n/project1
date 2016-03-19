package org.abacus.user.core.handler;

import java.util.ArrayList;
import java.util.List;

import org.abacus.organization.core.persistance.repository.OrganizationRepository;
import org.abacus.organization.shared.entity.OrganizationEntity;
import org.abacus.user.core.persistance.UserDao;
import org.abacus.user.core.persistance.repository.AuthorityRepository;
import org.abacus.user.core.persistance.repository.GroupAuthorityRepository;
import org.abacus.user.core.persistance.repository.GroupRepository;
import org.abacus.user.core.persistance.repository.UserGroupRepository;
import org.abacus.user.core.persistance.repository.UserOrganizationRepository;
import org.abacus.user.core.persistance.repository.UserRepository;
import org.abacus.user.shared.GroupNameInUseException;
import org.abacus.user.shared.UserExistsInGroupException;
import org.abacus.user.shared.UserNameExistsException;
import org.abacus.user.shared.entity.SecAuthorityEntity;
import org.abacus.user.shared.entity.SecGroupAuthorityEntity;
import org.abacus.user.shared.entity.SecGroupEntity;
import org.abacus.user.shared.entity.SecUserEntity;
import org.abacus.user.shared.entity.SecUserGroupEntity;
import org.abacus.user.shared.entity.SecUserOrganizationEntity;
import org.abacus.user.shared.event.CreateGroupEvent;
import org.abacus.user.shared.event.CreateUserEvent;
import org.abacus.user.shared.event.DeleteGroupEvent;
import org.abacus.user.shared.event.GroupCreatedEvent;
import org.abacus.user.shared.event.GroupDeletedEvent;
import org.abacus.user.shared.event.GroupUpdatedEvent;
import org.abacus.user.shared.event.ReadAuthoritiesEvent;
import org.abacus.user.shared.event.ReadGroupsEvent;
import org.abacus.user.shared.event.ReadOrganizationsEvent;
import org.abacus.user.shared.event.ReadUserEvent;
import org.abacus.user.shared.event.RequestReadAuthoritiesEvent;
import org.abacus.user.shared.event.RequestReadGroupsEvent;
import org.abacus.user.shared.event.RequestReadOrganizationsEvent;
import org.abacus.user.shared.event.RequestReadUserEvent;
import org.abacus.user.shared.event.UpdateGroupEvent;
import org.abacus.user.shared.event.UpdateUserEvent;
import org.abacus.user.shared.event.UserCreatedEvent;
import org.abacus.user.shared.event.UserUpdatedEvent;
import org.abacus.user.shared.holder.SearchUserCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service(value="userEventHandler")
public class UserEventHandler implements UserService{
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;
	
	@Autowired
	private UserGroupRepository userGroupRepository;
	
	@Autowired
	private GroupRepository groupRepository;
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Autowired
	private GroupAuthorityRepository groupAuthorityRepository;
	
	@Autowired
	private UserOrganizationRepository userOrganizationRepository;
	
	@Autowired
	private OrganizationRepository organizationRepository;

	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public ReadUserEvent requestUser(RequestReadUserEvent event) {
		List<SecUserEntity> userEntityList = userDao.findUser(event.getCriteria());
		ReadUserEvent result = new ReadUserEvent(userEntityList);	
		return result;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public UserCreatedEvent createUser(CreateUserEvent event) throws UserNameExistsException {
		
		SecUserEntity secUser = event.getUser();
		List<SecGroupEntity> userGroups = event.getUserGroups();
		List<OrganizationEntity> organizationList = event.getOrganizationList();
		String userCreated = event.getUserCreated();
		
		
		String username = secUser.getId();
		boolean isUserExists = userRepository.exists(username);
		if (isUserExists) {
			throw new UserNameExistsException(username);
		}
		
		secUser.setPassword(md5PasswordEncoder.encodePassword(secUser.getPassword(), null));
		secUser.setActive(true);
		
		secUser = userRepository.save(secUser);
		
		List<SecUserOrganizationEntity> userOrganizations = new ArrayList<>();
		for(OrganizationEntity organization : organizationList){
			SecUserOrganizationEntity userOrganization = new SecUserOrganizationEntity();
			userOrganization.setUser(secUser);
			userOrganization.setOrganization(organization);
			userOrganization.createHook(userCreated);
			userOrganizations.add(userOrganization);
		}
		
		userOrganizationRepository.save(userOrganizations);
		
		

		List<SecUserGroupEntity> memberships = new ArrayList<>();
		for (SecGroupEntity group : userGroups) {
			SecUserGroupEntity membership = new SecUserGroupEntity();
			membership.setUser(secUser);
			membership.setGroup(group);
			membership.createHook(userCreated);
			memberships.add(membership);
		}
		
		userGroupRepository.save(memberships);
		
		UserCreatedEvent createdEvent = new UserCreatedEvent(secUser);
		
		return createdEvent;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public UserUpdatedEvent updateUser(UpdateUserEvent event) {

		SecUserEntity updatingUser = event.getUser();
		List<SecGroupEntity> userGroups = event.getUserGroupList();
		List<OrganizationEntity> organizationList = event.getOrganizationList();
		String userUpdated = event.getUserUpdated();
		
		updatingUser = userRepository.save(updatingUser);
		
		userOrganizationRepository.delete(updatingUser.getId());
		
		List<SecUserOrganizationEntity> userOrganizations = new ArrayList<>();
		for(OrganizationEntity organization : organizationList){
			SecUserOrganizationEntity userOrganization = new SecUserOrganizationEntity();
			userOrganization.setUser(updatingUser);
			userOrganization.setOrganization(organization);
			userOrganization.updateHook(userUpdated);
			userOrganizations.add(userOrganization);
		}
		
		userOrganizationRepository.save(userOrganizations);
		
		
		userGroupRepository.delete(updatingUser.getId());
		
		List<SecUserGroupEntity> memberships = new ArrayList<>();
		for (SecGroupEntity group : userGroups) {
			if (group.getId().longValue()>1){//Zystem Eklenemesin
				SecUserGroupEntity membership = new SecUserGroupEntity();
				membership.setUser(updatingUser);
				membership.setGroup(group);
				membership.updateHook(userUpdated);
				memberships.add(membership);	
			}
		}
		
		userGroupRepository.save(memberships);
		
		UserUpdatedEvent updatedEvent = new UserUpdatedEvent(updatingUser);

		return updatedEvent;
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public ReadGroupsEvent requestGroup(RequestReadGroupsEvent event) {
		
		List<SecGroupEntity> groupList = null;
		if(StringUtils.hasText(event.getUsername())){
			groupList =userRepository.findUserGroups(event.getUsername());
		}else{
			groupList = groupRepository.findAllNormal();
		}
		ReadGroupsEvent readEvent = new ReadGroupsEvent(groupList);
		return readEvent;

	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public GroupCreatedEvent createGroup(CreateGroupEvent event) throws GroupNameInUseException {

		SecGroupEntity group = event.getGroup();
		List<SecAuthorityEntity> selectedAuthorities = event.getAuthorities();
		String userCreated = event.getUserCreated();

		
		SecGroupEntity existingGroupEntitiy = groupRepository.findByName(group.getName());
		if(existingGroupEntitiy != null){
			throw new GroupNameInUseException();
		}
		
		group = groupRepository.save(group);
		
		List<SecGroupAuthorityEntity> groupAuthorities = new ArrayList<>();
		for(SecAuthorityEntity authority : selectedAuthorities){
			SecGroupAuthorityEntity groupAuthority = new SecGroupAuthorityEntity();
			groupAuthority.setAuthority(authority);
			groupAuthority.setGroup(group);
			groupAuthority.createHook(userCreated);
			groupAuthorities.add(groupAuthority);
		}
		
		groupAuthorityRepository.save(groupAuthorities);
		
		return new GroupCreatedEvent(group);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public GroupUpdatedEvent updateGroup(UpdateGroupEvent event) throws GroupNameInUseException {
		
		SecGroupEntity group = event.getGroup();
		List<SecAuthorityEntity> selectedAuthorities = event.getAuthorities();
		String userCreated = event.getUserCreated();

		SecGroupEntity existingGroupEntitiy = groupRepository.findByName(group.getName());
		if(existingGroupEntitiy != null && !existingGroupEntitiy.getId().equals(group.getId()) ){
			throw new GroupNameInUseException();
		}
		
		group = groupRepository.save(group);
		
		groupAuthorityRepository.deleteByGroupId(group.getId());
		
		List<SecGroupAuthorityEntity> groupAuthorities = new ArrayList<>();
		for(SecAuthorityEntity authority : selectedAuthorities){
			SecGroupAuthorityEntity groupAuthority = new SecGroupAuthorityEntity();
			groupAuthority.setAuthority(authority);
			groupAuthority.setGroup(group);
			groupAuthority.createHook(userCreated);
			groupAuthorities.add(groupAuthority);
		}
		
		groupAuthorityRepository.save(groupAuthorities);
		
		return new GroupUpdatedEvent(group);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public GroupDeletedEvent deleteGroup(DeleteGroupEvent event) throws UserExistsInGroupException {
		
		Long groupId = event.getGroupId();
		
		Long count = userGroupRepository.userCount(groupId);
		boolean isGroupHasAnyMember = count > 0;
		
		if(isGroupHasAnyMember){
			throw new UserExistsInGroupException();
		}
		
		groupAuthorityRepository.deleteByGroupId(groupId);
		
		groupRepository.delete(groupId);
		
		return new GroupDeletedEvent();
	}


	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public ReadAuthoritiesEvent requestAuthorities(RequestReadAuthoritiesEvent event) {
		List<SecAuthorityEntity> groupAuthorities = new ArrayList<SecAuthorityEntity>();
		
		List<SecAuthorityEntity> retAuthorities = null;
		if(event.getGroupId() != null){
			retAuthorities = groupRepository.findGroupAuthorities(event.getGroupId());
		}else{
			retAuthorities = authorityRepository.findAllOrderByCode();
		}
		for (SecAuthorityEntity ret : retAuthorities) {
			groupAuthorities.add(ret);
		}
		
		ReadAuthoritiesEvent readEvent = new ReadAuthoritiesEvent(groupAuthorities);
		
		return readEvent;
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public ReadOrganizationsEvent requestOrganization(
			RequestReadOrganizationsEvent event) {
		List<OrganizationEntity> organizationList = null;
		
		if(StringUtils.hasText(event.getUsername())){
			organizationList = organizationRepository.findByUsername(event.getUsername());
		}else{
			organizationList = organizationRepository.findByOrganizationTree(event.getOrganization().getId());
		}
		
		return new ReadOrganizationsEvent(organizationList);
	}
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SecUserEntity> findUser(SearchUserCriteria searchUserCriteria) {

		List<SecUserEntity> searchreResults = userDao
				.findUser(searchUserCriteria);

		return searchreResults;
	}

}
