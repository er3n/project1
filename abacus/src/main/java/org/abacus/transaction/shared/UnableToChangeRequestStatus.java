package org.abacus.transaction.shared;

import org.abacus.common.shared.AbcBusinessException;

@SuppressWarnings("serial")
public class UnableToChangeRequestStatus extends AbcBusinessException {
	
	public UnableToChangeRequestStatus(String message, String... params){
		super(message,params);
	}

}
