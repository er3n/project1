package org.abacus.user.core.handler;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import org.abacus.user.core.persistance.repository.AuthorityRepository;
import org.abacus.user.core.persistance.repository.GroupAuthorityRepository;
import org.abacus.user.core.persistance.repository.GroupRepository;
import org.abacus.user.core.persistance.repository.UserGroupRepository;
import org.abacus.user.shared.GroupNameInUseException;
import org.abacus.user.shared.UserExistsInGroupException;
import org.abacus.user.shared.entity.SecAuthorityEntity;
import org.abacus.user.shared.entity.SecGroupAuthorityEntity;
import org.abacus.user.shared.entity.SecGroupEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service(value="secGroupHandler")
public class SecGroupHandlerImpl implements SecGroupHandler {

	@Autowired
	private GroupRepository groupRepository;
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Autowired
	private UserGroupRepository userGroupRepository;
	
	@Autowired
	private GroupAuthorityRepository groupAuthorityRepository;
	
	@PersistenceContext
	private EntityManager em;

	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public List<SecGroupEntity> allGroups(Boolean isRoot) {
		List<SecGroupEntity> groupList;
		if (isRoot) {
			groupList = groupRepository.findAllRoot();				
		} else {
			groupList = groupRepository.findAllNormal();
		}
		return groupList;
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public List<SecAuthorityEntity> allAuthorities() {
		List<SecAuthorityEntity> authorityList = authorityRepository.findAllOrderByCode();
		return authorityList;
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public List<SecAuthorityEntity> findGroupAuthorities(Long groupId) {
		List<SecAuthorityEntity> groupAuthorities = groupRepository.findGroupAuthorities(groupId);
		return groupAuthorities;
	}

	@Autowired
	private EntityManagerFactory fatory;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void removeGroup(Long groupId) throws UserExistsInGroupException {
		
		Long count = userGroupRepository.userCount(groupId);
		boolean isGroupHasAnyMember = count > 0;
		
		if(isGroupHasAnyMember){
			throw new UserExistsInGroupException("");
		}
		
		groupAuthorityRepository.deleteByGroupId(groupId);
		
		groupRepository.delete(groupId);
		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void saveGroup(SecGroupEntity group, List<SecAuthorityEntity> selectedAuthorities, String userCreated) throws GroupNameInUseException {
		
		SecGroupEntity existingGroupEntitiy = groupRepository.findByName(group.getName());
		if(existingGroupEntitiy != null){
			throw new GroupNameInUseException(group.getName());
		}
		
		groupRepository.save(group);
		
		List<SecGroupAuthorityEntity> groupAuthorities = new ArrayList<>();
		for(SecAuthorityEntity authority : selectedAuthorities){
			SecGroupAuthorityEntity groupAuthority = new SecGroupAuthorityEntity();
			groupAuthority.setAuthority(authority);
			groupAuthority.setGroup(group);
			groupAuthority.createHook(userCreated);
			groupAuthorities.add(groupAuthority);
		}
		
		groupAuthorityRepository.save(groupAuthorities);
		
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void updateGroup(SecGroupEntity group, List<SecAuthorityEntity> selectedAuthorities, String userCreated)
			throws GroupNameInUseException {
		
		SecGroupEntity existingGroupEntitiy = groupRepository.findByName(group.getName());
		if(existingGroupEntitiy != null && !existingGroupEntitiy.getId().equals(group.getId()) ){
			throw new GroupNameInUseException();
		}
		
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
		
	}
		
}
