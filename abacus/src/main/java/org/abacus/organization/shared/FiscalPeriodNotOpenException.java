package org.abacus.organization.shared;

import org.abacus.common.shared.AbcBusinessException;
import org.abacus.definition.shared.constant.EnumList;

public class FiscalPeriodNotOpenException extends AbcBusinessException {

	public FiscalPeriodNotOpenException(EnumList.DefTypeGroupEnum module) {
		super("fiscalPeriodNotOpenException", module.name());
	}
	
}
