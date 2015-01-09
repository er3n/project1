package org.abacus.transaction.shared;

import org.abacus.common.shared.AbcBusinessException;

@SuppressWarnings("serial")
public class UnableToCreateDocumentException extends AbcBusinessException {

	public UnableToCreateDocumentException(String... params){
		super(params);
	}
	
}
