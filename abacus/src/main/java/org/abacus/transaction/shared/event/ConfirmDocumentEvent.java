package org.abacus.transaction.shared.event;

import org.abacus.common.shared.event.UpdatedEvent;
import org.abacus.transaction.shared.entity.ReqDocumentEntity;

public class ConfirmDocumentEvent extends UpdatedEvent {

	private ReqDocumentEntity reqDocumentEntity;
	private String user;
	private String organization;
	private String fiscalYear;
	private String rootOrganization;
	
	public ConfirmDocumentEvent(ReqDocumentEntity reqDocumentEntity,String organization,String rootOrganization,String fiscalYear,String user){
		this.reqDocumentEntity = reqDocumentEntity;
		this.organization = organization;
		this.rootOrganization = rootOrganization;
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

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getFiscalYear() {
		return fiscalYear;
	}

	public void setFiscalYear(String fiscalYear) {
		this.fiscalYear = fiscalYear;
	}

	public String getRootOrganization() {
		return rootOrganization;
	}

	public void setRootOrganization(String rootOrganization) {
		this.rootOrganization = rootOrganization;
	}

}
