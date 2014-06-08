package org.abacus.transaction.shared.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "fin_detail")
@SuppressWarnings("serial")
public class FinDetailEntity extends TraDetailEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "document_id", nullable = false)
	private FinDocumentEntity document;

	@Column(name = "fin_note", nullable = true)
	private String finNote;

	public FinDetailEntity() {
	}

	public String getFinNote() {
		return finNote;
	}

	public void setFinNote(String finNote) {
		this.finNote = finNote;
	}

	public FinDocumentEntity getDocument() {
		return document;
	} 

	public void setDocument(TraDocumentEntity document) {
		this.document = (FinDocumentEntity) document;
	}

}
