package org.abacus.transaction.shared.event;

import org.abacus.common.shared.event.UpdatedEvent;
import org.abacus.transaction.shared.entity.TraDocumentEntity;

public class UpdateDocumentEvent<T extends TraDocumentEntity> extends UpdatedEvent {

	private T document;
	private String user;
	
	public UpdateDocumentEvent(T document, String user) {
		this.document = document;
		this.user = user;
	}

	public UpdateDocumentEvent(T document) {
		this.document = document;
		this.user = document.getUserCreated();
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



}
