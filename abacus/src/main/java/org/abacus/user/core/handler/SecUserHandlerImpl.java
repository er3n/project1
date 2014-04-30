package org.abacus.user.core.handler;

import java.util.ArrayList;
import java.util.List;

import org.abacus.user.core.persistance.UserDao;
import org.abacus.user.core.persistance.repository.GroupMemberRepository;
import org.abacus.user.core.persistance.repository.UserRepository;
import org.abacus.user.shared.UserNameExistsException;
import org.abacus.user.shared.entity.SecGroupEntity;
import org.abacus.user.shared.entity.SecGroupMemberEntity;
import org.abacus.user.shared.entity.SecUserEntity;
import org.abacus.user.shared.holder.SearchUserCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "secUserHandler")
public class SecUserHandlerImpl implements SecUserHandler {

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private GroupMemberRepository groupMemberRepository;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SecUserEntity> findUser(SearchUserCriteria searchUserCriteria) {

		List<SecUserEntity> searchreResults = userDao
				.findUser(searchUserCriteria);

		return searchreResults;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SecGroupEntity> findUserGroups(String username) {
		List<SecGroupEntity> userGroups = userRepository
				.findUserGroups(username);
		return userGroups;
	}

	@Override
	public void createUser(SecUserEntity selectedUser,
			List<SecGroupEntity> userGroups, String creatingUser)
			throws UserNameExistsException {
		String username = selectedUser.getId();
		boolean isUserExists = userRepository.exists(username);
		if (isUserExists) {
			throw new UserNameExistsException();
		}

		selectedUser.setActive(true);
		userRepository.save(selectedUser);

		List<SecGroupMemberEntity> memberships = new ArrayList<>();
		for (SecGroupEntity group : userGroups) {
			SecGroupMemberEntity membership = new SecGroupMemberEntity();
			membership.setUser(selectedUser);
			membership.setGroup(group);
			membership.createHook(creatingUser);
		}
		
		groupMemberRepository.save(memberships);

	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public void updateUser(SecUserEntity selectedUser,
			List<SecGroupEntity> userGroups, String updatingUser) {
		userRepository.save(selectedUser);
		
		List<SecGroupMemberEntity> memberships = new ArrayList<>();
		for (SecGroupEntity group : userGroups) {
			SecGroupMemberEntity membership = new SecGroupMemberEntity();
			membership.setUser(selectedUser);
			membership.setGroup(group);
			membership.updateHook(updatingUser);
		}
		
		groupMemberRepository.save(memberships);

	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public void makePassiveUser(SecUserEntity selectedUser, String updatingUser) {
		selectedUser.setActive(false);
		userRepository.save(selectedUser);
	}

}