package org.abacus.budget.shared.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.abacus.common.shared.entity.DynamicEntity;
import org.abacus.organization.shared.entity.FiscalYearEntity;

@Entity
@Table(name = "bud_document")
@SuppressWarnings("serial")
public class BudDocumentEntity extends DynamicEntity {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fiscal_year_id", nullable = false)
	private FiscalYearEntity fiscalYear;

	@Column(name = "budget_note", nullable = true)
	private String budgetNote;

	public BudDocumentEntity() {
	}

	public FiscalYearEntity getFiscalYear() {
		return fiscalYear;
	}

	public void setFiscalYear(FiscalYearEntity fiscalYear) {
		this.fiscalYear = fiscalYear;
	}

	public String getBudgetNote() {
		return budgetNote;
	}

	public void setBudgetNote(String budgetNote) {
		this.budgetNote = budgetNote;
	}

}
