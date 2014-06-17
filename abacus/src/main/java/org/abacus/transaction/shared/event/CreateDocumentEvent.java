package org.abacus.transaction.shared.event;

import org.abacus.common.shared.event.CreatedEvent;
import org.abacus.transaction.shared.entity.TraDocumentEntity;

public class CreateDocumentEvent extends CreatedEvent {

	private TraDocumentEntity document;
	private String user;
	private String organization;
	private String fiscalYear;

	public CreateDocumentEvent(TraDocumentEntity document, String user, String organization, String fiscalYear) {
		this.document = document;
		this.user = user;
		this.organization = organization;
		this.fiscalYear = fiscalYear;
	}

	public TraDocumentEntity getDocument() {
		return document;
	}

	public void setDocument(TraDocumentEntity document) {
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
