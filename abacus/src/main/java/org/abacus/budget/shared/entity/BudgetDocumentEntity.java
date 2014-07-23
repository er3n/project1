package org.abacus.budget.shared.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.abacus.common.shared.entity.DynamicEntity;
import org.abacus.organization.shared.entity.FiscalYearEntity;

@Entity
@Table(name = "budget_document")
@SuppressWarnings("serial")
public class BudgetDocumentEntity extends DynamicEntity {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fiscal_year_id", nullable = false)
	private FiscalYearEntity fiscalYear;

	@Temporal(TemporalType.DATE)
	@Column(name = "estimate_date", nullable = false)
	private Date estimateDate;

	@Column(name = "budget_note", nullable = true)
	private String budgetNote;

	public BudgetDocumentEntity() {
	}

	public FiscalYearEntity getFiscalYear() {
		return fiscalYear;
	}

	public void setFiscalYear(FiscalYearEntity fiscalYear) {
		this.fiscalYear = fiscalYear;
	}

	public Date getEstimateDate() {
		return estimateDate;
	}

	public void setEstimateDate(Date estimateDate) {
		this.estimateDate = estimateDate;
	}

	public String getBudgetNote() {
		return budgetNote;
	}

	public void setBudgetNote(String budgetNote) {
		this.budgetNote = budgetNote;
	}


}
