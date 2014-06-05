package org.abacus.transaction.shared.event;

import org.abacus.common.shared.event.UpdatedEvent;
import org.abacus.transaction.shared.entity.TraDocumentEntity;

public class DocumentUpdatedEvent extends UpdatedEvent {

	private TraDocumentEntity document;

	public DocumentUpdatedEvent(TraDocumentEntity document) {
		this.document = document;
	}

	public TraDocumentEntity getDocument() {
		return document;
	}

	public void setDocument(TraDocumentEntity document) {
		this.document = document;
	}
	
	
	
}
