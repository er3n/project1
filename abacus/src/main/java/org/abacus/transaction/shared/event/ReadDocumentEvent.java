package org.abacus.transaction.shared.event;

import java.util.List;

import org.abacus.common.shared.event.ReadEvent;
import org.abacus.transaction.shared.entity.TraDocumentEntity;

public class ReadDocumentEvent extends ReadEvent {

	private List<TraDocumentEntity> documentList;

	public ReadDocumentEvent(List<TraDocumentEntity> documentList) {
		this.documentList = documentList;
	}

	public List<TraDocumentEntity> getDocumentList() {
		return documentList;
	}

	public void setDocumentList(List<TraDocumentEntity> documentList) {
		this.documentList = documentList;
	}

}
