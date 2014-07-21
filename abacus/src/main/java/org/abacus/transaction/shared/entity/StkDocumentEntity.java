package org.abacus.transaction.shared.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "stk_document")
@SuppressWarnings("serial")
public class StkDocumentEntity extends TraDocumentEntity {

	//ref:stk:irsaliye fin:faturayi bilir //N irsaliye >> 1 fatura
	@Column(name = "ref_fin_document_id", nullable = true)
	private Long refFinDocumentId;

	//ref:stk:transferIn stk:transferIn bilir //1 stkOut >> N stkIn
	@Column(name = "ref_stk_document_id", nullable = true)
	private Long refStkDocumentId;

	public StkDocumentEntity() {
	}

	public Long getRefFinDocumentId() {
		return refFinDocumentId;
	}

	public void setRefFinDocumentId(Long refFinDocumentId) {
		this.refFinDocumentId = refFinDocumentId;
	}

	public Long getRefStkDocumentId() {
		return refStkDocumentId;
	}

	public void setRefStkDocumentId(Long refStkDocumentId) {
		this.refStkDocumentId = refStkDocumentId;
	}

}
