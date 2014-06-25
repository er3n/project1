package org.abacus.transaction.shared.event;

import org.abacus.common.shared.event.RequestReadEvent;
import org.abacus.transaction.shared.entity.TraDetailEntity;

public class RequestReadDetailEvent<D extends TraDetailEntity<D>> extends RequestReadEvent {

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
