package org.abacus.transaction.shared.event;

import java.util.List;

import org.abacus.common.shared.event.ReadEvent;
import org.abacus.transaction.shared.entity.StkDocumentEntity;

public class ReadDocumentEvent extends ReadEvent {

	List<StkDocumentEntity> document;

	public ReadDocumentEvent(List<StkDocumentEntity> document) {
		this.document = document;
	}

	public List<StkDocumentEntity> getDocument() {
		return document;
	}

	public void setDocument(List<StkDocumentEntity> document) {
		this.document = document;
	}

}
