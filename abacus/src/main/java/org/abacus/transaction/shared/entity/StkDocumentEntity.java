package org.abacus.transaction.shared.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "stk_document")
@SuppressWarnings("serial")
public class StkDocumentEntity extends TraDocumentEntity {

	//irsaliye den faturaya
	@Column(name = "ref_fin_document_id", nullable = true)
	private Long refFinDocumentId;
	
	public StkDocumentEntity() {
	}

	public Long getRefFinDocumentId() {
		return refFinDocumentId;
	}

	public void setRefFinDocumentId(Long refFinDocumentId) {
		this.refFinDocumentId = refFinDocumentId;
	}

}
