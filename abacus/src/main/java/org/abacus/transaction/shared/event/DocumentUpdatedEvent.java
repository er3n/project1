package org.abacus.transaction.shared.event;

import org.abacus.common.shared.event.UpdatedEvent;
import org.abacus.transaction.shared.entity.TraDocumentEntity;

public class DocumentUpdatedEvent<T extends TraDocumentEntity> extends UpdatedEvent {

	private T document;

	public DocumentUpdatedEvent(T document) {
		this.document = document;
	}

	public T getDocument() {
		return document;
	}

	public void setDocument(T document) {
		this.document = document;
	}
	
}
