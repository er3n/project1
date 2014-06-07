package org.abacus.transaction.shared.event;

import org.abacus.common.shared.event.UpdatedEvent;
import org.abacus.transaction.shared.entity.StkDocumentEntity;

public class UpdateDocumentEvent extends UpdatedEvent {

	private StkDocumentEntity document;
	private String user;
	private String organization;

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
