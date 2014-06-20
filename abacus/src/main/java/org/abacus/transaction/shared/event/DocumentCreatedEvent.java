package org.abacus.transaction.shared.event;

import org.abacus.common.shared.event.CreatedEvent;
import org.abacus.transaction.shared.entity.TraDocumentEntity;

public class DocumentCreatedEvent<T extends TraDocumentEntity> extends CreatedEvent {

	private T document;

	public DocumentCreatedEvent(T document) {
		this.document = document;
	}

	public T getDocument() {
		return document;
	}

	public void setDocument(T document) {
		this.document = document;
	}

}
