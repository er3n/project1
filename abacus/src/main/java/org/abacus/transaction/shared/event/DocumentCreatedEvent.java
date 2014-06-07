package org.abacus.transaction.shared.event;

import org.abacus.common.shared.event.CreatedEvent;
import org.abacus.transaction.shared.entity.StkDocumentEntity;

public class DocumentCreatedEvent extends CreatedEvent{

	private StkDocumentEntity document;

	public DocumentCreatedEvent(StkDocumentEntity document) {
		this.document = document;
	}

	public StkDocumentEntity getDocument() {
		return document;
	}

	public void setDocument(StkDocumentEntity document) {
		this.document = document;
	}

}
