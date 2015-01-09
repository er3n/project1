package org.abacus.organization.shared;

import org.abacus.common.shared.AbcBusinessException;

public class FiscalPeriodNotFoundException extends AbcBusinessException {

	public FiscalPeriodNotFoundException(String... params) {
		super(params);
	}
	
}
