package org.abacus.user.core.handler;

import java.io.Serializable;
import java.util.List;

import org.abacus.user.shared.UserExistsInGroupException;
import org.abacus.user.shared.entity.SecAuthorityEntity;
import org.abacus.user.shared.entity.SecGroupEntity;

public interface SecGroupHandler extends Serializable{
	
	List<SecGroupEntity> allGroups();

	List<SecAuthorityEntity> allAuthorities();
	
	List<SecAuthorityEntity> findGroupAuthorities(Long groupId);

	void removeGroup(Long groupId) throws UserExistsInGroupException;

}
