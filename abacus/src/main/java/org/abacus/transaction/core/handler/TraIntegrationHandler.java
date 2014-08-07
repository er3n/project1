package org.abacus.transaction.core.handler;

import java.util.List;

import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.entity.DefItemEntity;
import org.abacus.organization.shared.entity.FiscalPeriodEntity;
import org.abacus.transaction.shared.entity.FinDocumentEntity;
import org.abacus.transaction.shared.entity.StkDocumentEntity;
import org.abacus.transaction.shared.holder.SalesDocumentHolder;

public interface TraIntegrationHandler {

	FinDocumentEntity createFinFromStk(Long docId, EnumList.DefTypeEnum stkDocType);

	StkDocumentEntity createStkFromReq(Long docId, FiscalPeriodEntity fisPeriod, DefItemEntity vendor);  

	StkDocumentEntity createSalesDocument(List<SalesDocumentHolder> holder, DefItemEntity vendor);

}
