package org.abacus.user.core.handler;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import org.abacus.user.core.persistance.repository.AuthorityRepository;
import org.abacus.user.core.persistance.repository.GroupAuthorityRepository;
import org.abacus.user.core.persistance.repository.GroupMemberRepository;
import org.abacus.user.core.persistance.repository.GroupRepository;
import org.abacus.user.shared.UserExistsInGroupException;
import org.abacus.user.shared.entity.SecAuthorityEntity;
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
	private GroupMemberRepository groupMemberRepository;
	
	@Autowired
	private GroupAuthorityRepository groupAuthorityRepository;
	
	@PersistenceContext
	private EntityManager em;

	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public List<SecGroupEntity> allGroups() {
		List<SecGroupEntity> groupList = groupRepository.findAllOrderByName();
		return groupList;
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public List<SecAuthorityEntity> allAuthorities() {
		List<SecAuthorityEntity> authorityList = authorityRepository.findAllOrderById();
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
		
		Long count = groupMemberRepository.userCount(groupId);
		boolean isGroupHasAnyMember = count > 0;
		
		if(isGroupHasAnyMember){
			throw new UserExistsInGroupException();
		}
		
		groupAuthorityRepository.deleteByGroupId(groupId);
		
		groupRepository.delete(groupId);
		
	}
		
}
