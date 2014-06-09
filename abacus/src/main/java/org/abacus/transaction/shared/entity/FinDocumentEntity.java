package org.abacus.transaction.shared.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "fin_document")
@SuppressWarnings("serial")
public class FinDocumentEntity extends TraDocumentEntity {

	//odemeden fatura ya, yada basta turde referans
	@Column(name = "ref_fin_document_id", nullable = true)
	private Long refFinDocumentId;
	
	public FinDocumentEntity() {
	}

	public Long getRefFinDocumentId() {
		return refFinDocumentId;
	}

	public void setRefFinDocumentId(Long refFinDocumentId) {
		this.refFinDocumentId = refFinDocumentId;
	}

}
