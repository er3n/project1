package org.abacus.budget.shared.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.abacus.common.shared.entity.DynamicEntity;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.constant.EnumList.BudgetRX;
import org.abacus.organization.shared.entity.FiscalPeriodEntity;

@Entity
@Table(name = "bud_detail")
@SuppressWarnings("serial")
public class BudDetailEntity extends DynamicEntity {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "document_id", nullable = false)
	private BudDocumentEntity document;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fiscal_period_id", nullable = false)
	private FiscalPeriodEntity fiscalPeriod;

	@Enumerated(EnumType.STRING)
	@Column(name = "budget_rx", nullable = false, length = 30)
	private EnumList.BudgetRX budgetRX;

	@Enumerated(EnumType.STRING)
	@Column(name = "budget_type", nullable = false, length = 30)
	private EnumList.BudgetType budgetType; // Giris : Estimate

	@Column(name = "budget_amount", nullable = false, precision = 12, scale = 2)
	private BigDecimal budgetAmount;

	@Temporal(TemporalType.DATE)
	@Column(name = "accrue_date", nullable = true)
	private Date accrueDate;

	public BudDetailEntity() {
	}

	public BudDetailEntity(BudgetRX budRX) {
		this.budgetRX = budRX;
	}

	public BudDocumentEntity getDocument() {
		return document;
	}

	public void setDocument(BudDocumentEntity document) {
		this.document = document;
	}

	public FiscalPeriodEntity getFiscalPeriod() {
		return fiscalPeriod;
	}

	public void setFiscalPeriod(FiscalPeriodEntity fiscalPeriod) {
		this.fiscalPeriod = fiscalPeriod;
	}

	public EnumList.BudgetRX getBudgetRX() {
		return budgetRX;
	}

	public void setBudgetRX(EnumList.BudgetRX budgetRX) {
		this.budgetRX = budgetRX;
	}

	public EnumList.BudgetType getBudgetType() {
		return budgetType;
	}

	public void setBudgetType(EnumList.BudgetType budgetType) {
		this.budgetType = budgetType;
	}

	public BigDecimal getBudgetAmount() {
		return budgetAmount;
	}

	public void setBudgetAmount(BigDecimal budgetAmount) {
		this.budgetAmount = budgetAmount;
	}

	public Date getAccrueDate() {
		return accrueDate;
	}

	public void setAccrueDate(Date accrueDate) {
		this.accrueDate = accrueDate;
	}

}
