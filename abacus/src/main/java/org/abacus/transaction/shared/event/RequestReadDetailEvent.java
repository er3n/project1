package org.abacus.transaction.shared.event;

import org.abacus.common.shared.event.RequestReadEvent;

public class RequestReadDetailEvent extends RequestReadEvent {

	private Long documentId;

	public RequestReadDetailEvent(Long documentId) {
		this.documentId = documentId;
	}

	public Long getDocumentId() {
		return documentId;
	}

	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}

}
