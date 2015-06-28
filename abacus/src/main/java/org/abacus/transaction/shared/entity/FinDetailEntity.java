package org.abacus.transaction.shared.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.abacus.definition.shared.constant.EnumList;

@Entity
@Table(name = "tra_detail")
@SuppressWarnings("serial")
public class FinDetailEntity extends TraDetailEntity {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "document_fin_id", nullable = true)
	private FinDocumentEntity document;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "bs_document_id", nullable = true)
	private FinDocumentEntity bsDocument;

	@Enumerated(EnumType.STRING)
	@Column(name = "glc_id", nullable = true, length = 30)
	private EnumList.AccountGLC glc;
	
	public FinDetailEntity() {
	}

	@Override
	public FinDocumentEntity getDocument() {
		return document;
	} 

	@Override
	public void setDocument(TraDocumentEntity document) {
		this.document = (FinDocumentEntity) document;
	}

	public EnumList.AccountGLC getGlc() {
		return glc;
	}

	public void setGlc(EnumList.AccountGLC glc) {
		this.glc = glc;
	}

	public BigDecimal getDebitAmount(){
		return this.getTrStateDetail().equals(+1)?this.getBaseDetailAmount():BigDecimal.ZERO;
	}

	public BigDecimal getCreditAmount(){
		return this.getTrStateDetail().equals(-1)?this.getBaseDetailAmount():BigDecimal.ZERO;
	}

	public FinDocumentEntity getBsDocument() {
		return bsDocument;
	}

	public void setBsDocument(FinDocumentEntity bsDocument) {
		this.bsDocument = bsDocument;
	}

}
