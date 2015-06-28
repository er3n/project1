package org.abacus.transaction.shared.event;

import java.util.List;

import org.abacus.transaction.shared.entity.TraDetailEntity;
import org.abacus.transaction.shared.entity.TraDocumentEntity;

public class TraBulkUpdatedEvent<T extends TraDocumentEntity, D extends TraDetailEntity> {

	private T document;
	private List<D> detailList;

	public TraBulkUpdatedEvent(T document, List<D> detailList) {
		this.document = document;
		this.detailList = detailList;
	}

	public T getDocument() {
		return document;
	}

	public void setDocument(T document) {
		this.document = document;
	}

	public List<D> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<D> detailList) {
		this.detailList = detailList;
	}

}
