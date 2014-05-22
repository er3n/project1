package org.abacus.user.core.handler;

import java.io.Serializable;
import java.util.List;

import org.abacus.user.shared.UserNameExistsException;
import org.abacus.user.shared.entity.SecGroupEntity;
import org.abacus.user.shared.entity.SecUserEntity;
import org.abacus.user.shared.holder.SearchUserCriteria;

public interface SecUserHandler extends Serializable{

	List<SecUserEntity> findUser(
			SearchUserCriteria searchUserCriteria);

	List<SecGroupEntity> findUserGroups(String selectedUserName);
	
	void createUser(SecUserEntity selectedUser, List<SecGroupEntity> userGroups, String creatingUser) throws UserNameExistsException; 
	
	void updateUser(SecUserEntity selectedUser,List<SecGroupEntity> userGroups, String updatingUser);
	
	void makePassiveUser(SecUserEntity selectedUser, String updatingUser);
	
	SecUserEntity getUser(String id);

}
