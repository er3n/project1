package org.abacus.transaction.shared.event;

import org.abacus.common.shared.event.DeletedEvent;

public class DeleteDocumentEvent extends DeletedEvent {

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
