package org.abacus.transaction.shared;

import org.abacus.common.shared.AbcBusinessException;

@SuppressWarnings("serial")
public class UnableToCreateDocumentException extends AbcBusinessException {

	public UnableToCreateDocumentException(String message, String... params){
		super(message,params);
	}
	
}
