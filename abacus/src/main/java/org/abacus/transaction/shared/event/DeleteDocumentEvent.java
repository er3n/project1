package org.abacus.transaction.shared.event;

import org.abacus.common.shared.event.DeletedEvent;
import org.abacus.transaction.shared.entity.TraDocumentEntity;

public class DeleteDocumentEvent<T extends TraDocumentEntity> extends DeletedEvent {

	private Long documentId;

	public DeleteDocumentEvent(Long documentId) {
		this.documentId = documentId;
	}

	public Long getDocumentId() {
		return documentId;
	}

	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}

}
