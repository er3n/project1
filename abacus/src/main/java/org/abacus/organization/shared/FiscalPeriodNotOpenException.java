package org.abacus.organization.shared;

import org.abacus.common.shared.AbcBusinessException;

public class FiscalPeriodNotOpenException extends AbcBusinessException {

	public FiscalPeriodNotOpenException(String... params) {
		super(params);
	}
	
}
