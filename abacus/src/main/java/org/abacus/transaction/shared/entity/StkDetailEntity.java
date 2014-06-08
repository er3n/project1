package org.abacus.transaction.shared.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "stk_detail")
@SuppressWarnings("serial")
public class StkDetailEntity extends TraDetailEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "document_id", nullable = false)
	private StkDocumentEntity document;
	
	@Column(name = "stk_note", nullable = true)
	private String stkNote;
	
	public StkDetailEntity() {
	}

	public String getStkNote() {
		return stkNote;
	}

	public void setStkNote(String stkNote) {
		this.stkNote = stkNote;
	}

	@Override
	public TraDocumentEntity getDocument() {
		return document;
	}

	@Override
	public void setDocument(TraDocumentEntity document) {
		this.document = (StkDocumentEntity) document;
	}
	


	

}
