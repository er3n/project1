package org.abacus.user.shared;

import org.abacus.common.shared.AbcBusinessException;

public class UserExistsInGroupException extends AbcBusinessException {

	public UserExistsInGroupException(String... params){
		super(params);
	}
	
}
