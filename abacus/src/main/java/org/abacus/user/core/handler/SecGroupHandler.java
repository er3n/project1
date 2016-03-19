package org.abacus.user.core.handler;

import java.io.Serializable;
import java.util.List;

import org.abacus.user.shared.GroupNameInUseException;
import org.abacus.user.shared.UserExistsInGroupException;
import org.abacus.user.shared.entity.SecAuthorityEntity;
import org.abacus.user.shared.entity.SecGroupEntity;

public interface SecGroupHandler extends Serializable{
	
	List<SecGroupEntity> allGroups(Boolean isRoot);

	List<SecAuthorityEntity> allAuthorities();
	
	List<SecAuthorityEntity> findGroupAuthorities(Long groupId);

	void removeGroup(Long groupId) throws UserExistsInGroupException;

	void saveGroup(SecGroupEntity group, List<SecAuthorityEntity> selectedAuthorities, String userCreated) throws GroupNameInUseException;
	
	void updateGroup(SecGroupEntity group, List<SecAuthorityEntity> selectedAuthorities, String userCreated)  throws GroupNameInUseException;

}
