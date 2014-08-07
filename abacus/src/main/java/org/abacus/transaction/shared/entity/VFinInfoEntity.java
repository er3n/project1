package org.abacus.transaction.shared.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.abacus.common.shared.entity.ViewEntity;
import org.hibernate.annotations.Subselect;

@SuppressWarnings("serial")
@Entity
@Subselect("select id, info_document_id, bs_amount, pr_amount from v_fin_info")
public class VFinInfoEntity extends ViewEntity {
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "info_document_id", nullable = false)
	private FinDocumentEntity finInfo;
	
	@Column(name = "bs_amount", precision = 10, scale = 3)
	private BigDecimal bsAmount;
	
	@Column(name = "pr_amount", precision = 10, scale = 3)
	private BigDecimal prAmount;

	public VFinInfoEntity(){
	}

	public VFinInfoEntity(Long id){
		this.setId(id);
	}

	public BigDecimal getBsAmount() {
		return bsAmount;
	}

	public void setBsAmount(BigDecimal bsAmount) {
		this.bsAmount = bsAmount;
	}

	public BigDecimal getPrAmount() {
		return prAmount;
	}

	public void setPrAmount(BigDecimal prAmount) {
		this.prAmount = prAmount;
	}

	public FinDocumentEntity getFinInfo() {
		return finInfo;
	}

	public void setFinInfo(FinDocumentEntity finInfo) {
		this.finInfo = finInfo;
	}

}
