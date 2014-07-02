package org.abacus.transaction.shared.event;

import org.abacus.common.shared.event.CreatedEvent;
import org.abacus.transaction.shared.entity.TraDocumentEntity;

public class CreateDocumentEvent<T extends TraDocumentEntity> extends CreatedEvent {

	private T document;
	private String user;
	private String organization;
	private String fiscalYear;

	public CreateDocumentEvent(T document, String user, String organization, String fiscalYear) {
		this.document = document;
		this.user = user;
		this.organization = organization;
		this.fiscalYear = fiscalYear;
	}

	public CreateDocumentEvent(T document) {
		this.document = document;
		this.user = document.getUserCreated();
		this.organization = document.getOrganization().getId();
		this.fiscalYear = document.getFiscalPeriod().getFiscalYear().getId();
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

}
