package org.abacus.transaction.shared.event;

import java.util.List;

import org.abacus.common.shared.event.ReadEvent;
import org.abacus.transaction.shared.entity.TraDocumentEntity;

public class ReadDocumentEvent extends ReadEvent {

	List<TraDocumentEntity> document;

	public ReadDocumentEvent(List<TraDocumentEntity> document) {
		this.document = document;
	}

	public List<TraDocumentEntity> getDocument() {
		return document;
	}

	public void setDocument(List<TraDocumentEntity> document) {
		this.document = document;
	}

}
