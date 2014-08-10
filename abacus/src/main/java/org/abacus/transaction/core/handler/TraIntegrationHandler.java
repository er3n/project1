package org.abacus.transaction.core.handler;

import java.util.Date;
import java.util.List;

import org.abacus.catering.shared.entity.CatMenuEntity;
import org.abacus.definition.shared.entity.DefItemEntity;
import org.abacus.organization.shared.entity.DepartmentEntity;
import org.abacus.organization.shared.entity.FiscalPeriodEntity;
import org.abacus.transaction.shared.entity.FinDocumentEntity;
import org.abacus.transaction.shared.entity.StkDocumentEntity;

public interface TraIntegrationHandler {

	FinDocumentEntity createFinFromStk(Long docId);

	StkDocumentEntity createStkFromReq(Long docId, FiscalPeriodEntity fisPeriod, DefItemEntity vendor);  

	StkDocumentEntity createSalesDocument(List<CatMenuEntity> holderList, DefItemEntity customer, FiscalPeriodEntity fisPeriod2, DepartmentEntity department, Date transactionDate);

}
