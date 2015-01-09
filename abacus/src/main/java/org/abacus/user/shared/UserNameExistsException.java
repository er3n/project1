package org.abacus.user.shared;

import org.abacus.common.shared.AbcBusinessException;

public class UserNameExistsException extends AbcBusinessException {

	public UserNameExistsException(String... params){
		super(params);
	}
	
}
