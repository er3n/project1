package org.abacus.transaction.shared.event;

import org.abacus.common.shared.event.RequestReadEvent;
import org.abacus.transaction.shared.entity.TraDetailEntity;

public class RequestReadDetailEvent<D extends TraDetailEntity> extends RequestReadEvent {

	private Long documentId;
	private String vendor;

	public RequestReadDetailEvent(Long documentId) {
		this.documentId = documentId;
	}
	
	public RequestReadDetailEvent(Long documentId,String vendor) {
		this.documentId = documentId;
		this.vendor = vendor;
	}

	public Long getDocumentId() {
		return documentId;
	}

	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

}
