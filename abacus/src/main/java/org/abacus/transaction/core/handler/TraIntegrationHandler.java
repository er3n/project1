package org.abacus.transaction.core.handler;

import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.entity.DefItemEntity;
import org.abacus.organization.shared.entity.FiscalPeriodEntity;
import org.abacus.transaction.shared.entity.FinDocumentEntity;
import org.abacus.transaction.shared.entity.StkDocumentEntity;

public interface TraIntegrationHandler {
	
	FinDocumentEntity createFinFromStk(Long docId, EnumList.DefTypeEnum stkDocType);

	StkDocumentEntity createStkFromReq(Long docId, FiscalPeriodEntity fisPeriod, DefItemEntity vendor);  
	
}
