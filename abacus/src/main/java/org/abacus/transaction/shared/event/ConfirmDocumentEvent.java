package org.abacus.transaction.shared.event;

import org.abacus.common.shared.event.UpdatedEvent;
import org.abacus.organization.shared.entity.FiscalYearEntity;
import org.abacus.organization.shared.entity.OrganizationEntity;
import org.abacus.transaction.shared.entity.ReqDocumentEntity;

public class ConfirmDocumentEvent extends UpdatedEvent {

	private ReqDocumentEntity reqDocumentEntity;
	private String user;
	private OrganizationEntity organization;
	private FiscalYearEntity fiscalYear;
	
	public ConfirmDocumentEvent(ReqDocumentEntity reqDocumentEntity, OrganizationEntity organization, FiscalYearEntity fiscalYear, String user){
		this.reqDocumentEntity = reqDocumentEntity;
		this.organization = organization;
		this.fiscalYear = fiscalYear;
		this.user = user;
	}

	public ReqDocumentEntity getReqDocumentEntity() {
		return reqDocumentEntity;
	}

	public void setReqDocumentEntity(ReqDocumentEntity reqDocumentEntity) {
		this.reqDocumentEntity = reqDocumentEntity;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public OrganizationEntity getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationEntity organization) {
		this.organization = organization;
	}

	public FiscalYearEntity getFiscalYear() {
		return fiscalYear;
	}

	public void setFiscalYear(FiscalYearEntity fiscalYear) {
		this.fiscalYear = fiscalYear;
	}

}
