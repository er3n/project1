package org.abacus.transaction.shared.event;

import org.abacus.common.shared.event.DeletedEvent;
import org.abacus.transaction.shared.entity.TraDocumentEntity;

public class DeleteDocumentEvent<T extends TraDocumentEntity> extends DeletedEvent {

	private T document;

	public DeleteDocumentEvent(T document) {
		this.document = document;
	}

	public T getDocument() {
		return document;
	}

	public void setDocument(T document) {
		this.document = document;
	}

}
