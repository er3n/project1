package org.abacus.transaction.shared.event;

import org.abacus.common.shared.event.UpdatedEvent;

public class CancelDocumentEvent extends UpdatedEvent {

	private Long documentId;
	private String userCanceled;

	public CancelDocumentEvent(Long documentId, String userCanceled) {
		this.documentId = documentId;
		this.userCanceled = userCanceled;
	}

	public Long getDocumentId() {
		return documentId;
	}

	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}

	public String getUserCanceled() {
		return userCanceled;
	}

	public void setUserCanceled(String userCanceled) {
		this.userCanceled = userCanceled;
	}

}
