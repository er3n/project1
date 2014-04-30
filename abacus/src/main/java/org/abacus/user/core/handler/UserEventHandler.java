package org.abacus.user.core.handler;

import java.util.ArrayList;
import java.util.List;

import org.abacus.user.core.persistance.UserDao;
import org.abacus.user.core.persistance.repository.GroupMemberRepository;
import org.abacus.user.core.persistance.repository.GroupRepository;
import org.abacus.user.core.persistance.repository.UserRepository;
import org.abacus.user.shared.UserNameExistsException;
import org.abacus.user.shared.entity.SecGroupEntity;
import org.abacus.user.shared.entity.SecGroupMemberEntity;
import org.abacus.user.shared.entity.SecUserEntity;
import org.abacus.user.shared.event.CreateGroupEvent;
import org.abacus.user.shared.event.CreateUserEvent;
import org.abacus.user.shared.event.DeleteGroupEvent;
import org.abacus.user.shared.event.GroupCreatedEvent;
import org.abacus.user.shared.event.GroupDeletedEvent;
import org.abacus.user.shared.event.GroupUpdatedEvent;
import org.abacus.user.shared.event.ReadAuthoritiesEvent;
import org.abacus.user.shared.event.ReadGroupsEvent;
import org.abacus.user.shared.event.ReadUserEvent;
import org.abacus.user.shared.event.ReadUserGroupsEvent;
import org.abacus.user.shared.event.RequestReadAuthoritiesEvent;
import org.abacus.user.shared.event.RequestReadGroupsEvent;
import org.abacus.user.shared.event.RequestReadUserEvent;
import org.abacus.user.shared.event.RequestReadUserGroupsEvent;
import org.abacus.user.shared.event.UpdateGroupEvent;
import org.abacus.user.shared.event.UpdateUserEvent;
import org.abacus.user.shared.event.UserCreatedEvent;
import org.abacus.user.shared.event.UserUpdatedEvent;
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
	private GroupMemberRepository groupMemberRepository;
	
	@Autowired
	private GroupRepository groupRepository;

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
		String userCreated = event.getUserCreated();
		
		
		String username = secUser.getId();
		boolean isUserExists = userRepository.exists(username);
		if (isUserExists) {
			throw new UserNameExistsException();
		}
		
		secUser.setPassword(md5PasswordEncoder.encodePassword(secUser.getPassword(), null));
		secUser.setActive(true);
		
		secUser = userRepository.save(secUser);

		List<SecGroupMemberEntity> memberships = new ArrayList<>();
		for (SecGroupEntity group : userGroups) {
			SecGroupMemberEntity membership = new SecGroupMemberEntity();
			membership.setUser(secUser);
			membership.setGroup(group);
			membership.createHook(userCreated);
			memberships.add(membership);
		}
		
		groupMemberRepository.save(memberships);
		
		UserCreatedEvent createdEvent = new UserCreatedEvent(secUser);
		
		return createdEvent;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public UserUpdatedEvent updateUser(UpdateUserEvent event) {

		SecUserEntity updatingUser = event.getUser();
		List<SecGroupEntity> userGroups = event.getUserGroupList();
		String userUpdated = event.getUserUpdated();
		
		updatingUser = userRepository.save(updatingUser);
		
		
		groupMemberRepository.delete(updatingUser.getId());
		
		List<SecGroupMemberEntity> memberships = new ArrayList<>();
		for (SecGroupEntity group : userGroups) {
			SecGroupMemberEntity membership = new SecGroupMemberEntity();
			membership.setUser(updatingUser);
			membership.setGroup(group);
			membership.updateHook(userUpdated);
			memberships.add(membership);
		}
		
		groupMemberRepository.save(memberships);
		
		UserUpdatedEvent updatedEvent = new UserUpdatedEvent(updatingUser);

		return updatedEvent;
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public ReadUserGroupsEvent requestGroup(RequestReadUserGroupsEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public ReadGroupsEvent requestGroup(RequestReadGroupsEvent event) {
		
		List<SecGroupEntity> groupList = null;
		if(StringUtils.hasText(event.getUsername())){
			groupList =userRepository.findUserGroups(event.getUsername());
		}else{
			groupList = groupRepository.findAllOrderByName();
		}
		ReadGroupsEvent readEvent = new ReadGroupsEvent(groupList);
		return readEvent;

	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public GroupCreatedEvent createGroup(CreateGroupEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public GroupUpdatedEvent updateGroup(UpdateGroupEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public GroupDeletedEvent deleteGroup(DeleteGroupEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public ReadAuthoritiesEvent requestAuthorities(RequestReadAuthoritiesEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

}
