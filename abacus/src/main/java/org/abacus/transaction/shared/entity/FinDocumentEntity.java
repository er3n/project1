package org.abacus.transaction.shared.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

@Entity
@Table(name = "fin_document")
@SuppressWarnings("serial")
public class FinDocumentEntity extends TraDocumentEntity {

	//ref:fin:Payment/Receipt ten Faturaraya
	@Column(name = "ref_fin_document_id", nullable = true)
	private Long refFinDocumentId;
	
	public FinDocumentEntity() {
	}

	public FinDocumentEntity(TraDocumentEntity traDoc) {
		BeanUtils.copyProperties(traDoc, this);
	}

	public Long getRefFinDocumentId() {
		return refFinDocumentId;
	}

	public void setRefFinDocumentId(Long refFinDocumentId) {
		this.refFinDocumentId = refFinDocumentId;
	}

}
