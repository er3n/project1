package org.abacus.organization.shared;

import org.abacus.common.shared.AbcBusinessException;

public class FiscalYearDocumentDateNotMatchedException extends AbcBusinessException {

	public FiscalYearDocumentDateNotMatchedException(String... params) {
		super(params);
	}
	
}
