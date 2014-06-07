package org.abacus.transaction.shared.event;

import org.abacus.common.shared.event.CreatedEvent;
import org.abacus.transaction.shared.entity.StkDocumentEntity;

public class CreateDocumentEvent extends CreatedEvent{

	private StkDocumentEntity document;
	private String user;
	private String organization;

	public CreateDocumentEvent(StkDocumentEntity document, String user, String organization) {
		this.document = document;
		this.user = user;
		this.organization = organization;
	}

	public StkDocumentEntity getDocument() {
		return document;
	}

	public void setDocument(StkDocumentEntity document) {
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

}
