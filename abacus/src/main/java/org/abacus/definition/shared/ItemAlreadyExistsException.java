package org.abacus.definition.shared;

import org.abacus.common.shared.AbcBusinessException;

@SuppressWarnings("serial")
public class ItemAlreadyExistsException extends AbcBusinessException {

	public ItemAlreadyExistsException(String... params) {
		super(params);
	}
	
}
