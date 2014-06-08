package org.abacus.transaction.shared.event;

import org.abacus.common.shared.event.CreatedEvent;
import org.abacus.transaction.shared.entity.StkDocumentEntity;
import org.abacus.transaction.shared.entity.TraDocumentEntity;

public class DocumentCreatedEvent extends CreatedEvent {

	private TraDocumentEntity document;

	public DocumentCreatedEvent(TraDocumentEntity document) {
		this.document = document;
	}

	public TraDocumentEntity getDocument() {
		return document;
	}

	public void setDocument(TraDocumentEntity document) {
		this.document = document;
	}

}
