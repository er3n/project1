package org.abacus.transaction.core.handler;

import org.abacus.definition.shared.entity.DefItemEntity;
import org.abacus.organization.shared.entity.FiscalPeriodEntity;
import org.abacus.transaction.shared.entity.FinDocumentEntity;
import org.abacus.transaction.shared.entity.StkDocumentEntity;

public interface TraIntegrationHandler {
	
	FinDocumentEntity createFinFromStk(Long docId);

	StkDocumentEntity createStkFromReq(Long docId, FiscalPeriodEntity fisPeriod, DefItemEntity vendor);  
	
}
