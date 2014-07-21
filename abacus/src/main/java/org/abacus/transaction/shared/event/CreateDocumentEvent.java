package org.abacus.transaction.shared.event;

import org.abacus.common.shared.event.CreatedEvent;
import org.abacus.organization.shared.entity.FiscalYearEntity;
import org.abacus.organization.shared.entity.OrganizationEntity;
import org.abacus.transaction.shared.entity.TraDocumentEntity;

public class CreateDocumentEvent<T extends TraDocumentEntity> extends CreatedEvent {

	private T document;
	private String user;
	private OrganizationEntity organization;
	private FiscalYearEntity fiscalYear2;

	public CreateDocumentEvent(T document, String user, OrganizationEntity organization, FiscalYearEntity fiscalYear2) {
		this.document = document;
		this.user = user;
		this.organization = organization;
		this.fiscalYear2 = fiscalYear2;
	}

	public CreateDocumentEvent(T document) {
		this.document = document;
		this.user = document.getUserCreated();
		this.organization = document.getOrganization();
		this.fiscalYear2 = document.getFiscalPeriod2()==null?null:document.getFiscalPeriod2().getFiscalYear();
	}

	public T getDocument() {
		return document;
	}

	public void setDocument(T document) {
		this.document = document;
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

	public FiscalYearEntity getFiscalYear2() {
		return fiscalYear2;
	}

	public void setFiscalYear(FiscalYearEntity fiscalYear2) {
		this.fiscalYear2 = fiscalYear2;
	}

}
