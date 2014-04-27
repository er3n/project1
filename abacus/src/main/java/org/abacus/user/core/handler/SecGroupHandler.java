package org.abacus.user.core.handler;

import java.util.List;

import org.abacus.user.shared.UserExistsInGroupException;
import org.abacus.user.shared.entity.SecAuthorityEntity;
import org.abacus.user.shared.entity.SecGroupEntity;

public interface SecGroupHandler {
	
	List<SecGroupEntity> allGroups();

	List<SecAuthorityEntity> allAuthorities();
	
	List<SecAuthorityEntity> findGroupAuthorities(Long groupId);

	void removeGroup(Long groupId) throws UserExistsInGroupException;

}
