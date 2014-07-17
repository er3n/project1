package org.abacus.transaction.shared.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.abacus.definition.shared.entity.DefTypeEntity;
import org.abacus.organization.shared.entity.FiscalPeriodEntity;
import org.abacus.organization.shared.entity.OrganizationEntity;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name = "tra_detail")
@SuppressWarnings("serial")
public class FinDetailEntity extends TraDetailEntity<FinDetailEntity> {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "document_fin_id", nullable = true)
	private FinDocumentEntity document;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "glc_id", nullable = true)
	private DefTypeEntity glc;

	public FinDetailEntity() {
	}

	public FinDetailEntity(TraDetailEntity<?> traDet) {
		traDet.setDocument(null);
		BeanUtils.copyProperties(traDet, this);
	}
	
	@Override
	public FinDocumentEntity getDocument() {
		return document;
	} 

	@Override
	public void setDocument(TraDocumentEntity document) {
		this.document = (FinDocumentEntity) document;
	}

	public DefTypeEntity getGlc() {
		return glc;
	}

	public void setGlc(DefTypeEntity glc) {
		this.glc = glc;
	}

	public BigDecimal getDebitAmount(){
		return this.getTrStateDetail().equals(+1)?this.getBaseDetailAmount():BigDecimal.ZERO;
	}

	public BigDecimal getCreditAmount(){
		return this.getTrStateDetail().equals(-1)?this.getBaseDetailAmount():BigDecimal.ZERO;
	}

}
