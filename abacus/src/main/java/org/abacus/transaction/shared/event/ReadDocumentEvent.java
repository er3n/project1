package org.abacus.transaction.shared.event;

import java.util.List;

import org.abacus.common.shared.event.ReadEvent;
import org.abacus.transaction.shared.entity.TraDocumentEntity;

public class ReadDocumentEvent<T extends TraDocumentEntity> extends ReadEvent {

	private List<T> documentList;

	public ReadDocumentEvent(List<T> documentList) {
		this.documentList = documentList;
	}

	public List<T> getDocumentList() {
		return documentList;
	}

	public void setDocumentList(List<T> documentList) {
		this.documentList = documentList;
	}

}
