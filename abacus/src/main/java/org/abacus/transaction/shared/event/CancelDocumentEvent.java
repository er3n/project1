package org.abacus.transaction.shared.event;

import org.abacus.common.shared.event.UpdatedEvent;
import org.abacus.transaction.shared.entity.TraDocumentEntity;

public class CancelDocumentEvent<T extends TraDocumentEntity> extends UpdatedEvent {

	private T document;
	private String userCanceled;

	public CancelDocumentEvent(T document, String userCanceled) {
		this.document = document;
		this.userCanceled = userCanceled;
	}

	public T getDocument() {
		return document;
	}

	public void setDocument(T document) {
		this.document = document;
	}

	public String getUserCanceled() {
		return userCanceled;
	}

	public void setUserCanceled(String userCanceled) {
		this.userCanceled = userCanceled;
	}

}
