package org.abacus.organization.shared;

import org.abacus.common.shared.AbcBusinessException;

public class FiscalYearNotFoundException extends AbcBusinessException {

	public FiscalYearNotFoundException(String... params) {
		super(params);
	}
	
}
