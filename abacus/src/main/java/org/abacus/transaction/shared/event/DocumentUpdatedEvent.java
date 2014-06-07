package org.abacus.transaction.shared.event;

import org.abacus.common.shared.event.UpdatedEvent;
import org.abacus.transaction.shared.entity.StkDocumentEntity;

public class DocumentUpdatedEvent extends UpdatedEvent {

	private StkDocumentEntity document;

	public DocumentUpdatedEvent(StkDocumentEntity document) {
		this.document = document;
	}

	public StkDocumentEntity getDocument() {
		return document;
	}

	public void setDocument(StkDocumentEntity document) {
		this.document = document;
	}
	
	
	
}
