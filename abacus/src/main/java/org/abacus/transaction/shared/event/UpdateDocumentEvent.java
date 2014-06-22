package org.abacus.transaction.shared.event;

import org.abacus.common.shared.event.UpdatedEvent;
import org.abacus.transaction.shared.entity.StkDocumentEntity;
import org.abacus.transaction.shared.entity.TraDocumentEntity;

public class UpdateDocumentEvent<T extends TraDocumentEntity> extends UpdatedEvent {

	private TraDocumentEntity document;
	private String user;
	
	public UpdateDocumentEvent(StkDocumentEntity document, String user) {
		this.document = document;
		this.user = user;
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



}
