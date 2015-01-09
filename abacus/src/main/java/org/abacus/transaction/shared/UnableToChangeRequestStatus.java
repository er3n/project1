package org.abacus.transaction.shared;

import org.abacus.common.shared.AbcBusinessException;

@SuppressWarnings("serial")
public class UnableToChangeRequestStatus extends AbcBusinessException {
	
	public UnableToChangeRequestStatus(String... params){
		super(params);
	}

}
