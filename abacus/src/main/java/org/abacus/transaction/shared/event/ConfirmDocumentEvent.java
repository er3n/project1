package org.abacus.transaction.shared.event;

import org.abacus.common.shared.event.UpdatedEvent;
import org.abacus.definition.shared.entity.DefItemEntity;
import org.abacus.organization.shared.entity.FiscalPeriodEntity;
import org.abacus.transaction.shared.entity.ReqDocumentEntity;

public class ConfirmDocumentEvent extends UpdatedEvent {

	private ReqDocumentEntity reqDocumentEntity;
	private DefItemEntity vendor;
	private FiscalPeriodEntity fiscalPeriod2;

	public ConfirmDocumentEvent(ReqDocumentEntity reqDocumentEntity, FiscalPeriodEntity fiscalPeriod2) {
		this.reqDocumentEntity = reqDocumentEntity;
		this.fiscalPeriod2 = fiscalPeriod2;
	}

	public ConfirmDocumentEvent(ReqDocumentEntity reqDocumentEntity, FiscalPeriodEntity fiscalPeriod2, DefItemEntity vendor) {
		this.reqDocumentEntity = reqDocumentEntity;
		this.fiscalPeriod2 = fiscalPeriod2;
		this.vendor = vendor;
	}

	public ReqDocumentEntity getReqDocumentEntity() {
		return reqDocumentEntity;
	}

	public void setReqDocumentEntity(ReqDocumentEntity reqDocumentEntity) {
		this.reqDocumentEntity = reqDocumentEntity;
	}

	public DefItemEntity getVendor() {
		return vendor;
	}

	public void setVendor(DefItemEntity vendor) {
		this.vendor = vendor;
	}

	public FiscalPeriodEntity getFiscalPeriod2() {
		return fiscalPeriod2;
	}

	public void setFiscalPeriod2(FiscalPeriodEntity fiscalPeriod2) {
		this.fiscalPeriod2 = fiscalPeriod2;
	}

}
