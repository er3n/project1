package org.abacus.transaction.shared.event;

import org.abacus.common.shared.event.UpdatedEvent;
import org.abacus.definition.shared.entity.DefItemEntity;
import org.abacus.organization.shared.entity.FiscalYearEntity;
import org.abacus.organization.shared.entity.OrganizationEntity;
import org.abacus.transaction.shared.entity.ReqDocumentEntity;

public class ConfirmDocumentEvent extends UpdatedEvent {

	private ReqDocumentEntity reqDocumentEntity;
	private DefItemEntity vendor;
	private OrganizationEntity organization;
	private FiscalYearEntity fiscalYear2;

	public ConfirmDocumentEvent(ReqDocumentEntity reqDocumentEntity, OrganizationEntity organization, FiscalYearEntity fiscalYear2) {
		this.reqDocumentEntity = reqDocumentEntity;
		this.organization = organization;
		this.fiscalYear2 = fiscalYear2;
	}

	public ConfirmDocumentEvent(ReqDocumentEntity reqDocumentEntity, DefItemEntity vendor) {
		this.reqDocumentEntity = reqDocumentEntity;
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

	public OrganizationEntity getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationEntity organization) {
		this.organization = organization;
	}

	public FiscalYearEntity getFiscalYear2() {
		return fiscalYear2;
	}

	public void setFiscalYear2(FiscalYearEntity fiscalYear2) {
		this.fiscalYear2 = fiscalYear2;
	}

}
