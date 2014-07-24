package org.abacus.budget.shared.holder;

import java.io.Serializable;

import org.abacus.budget.shared.entity.BudDetailEntity;
import org.abacus.organization.shared.entity.FiscalPeriodEntity;

@SuppressWarnings("serial")
public class BudgetPeriodHolder implements Serializable {

	private BudDetailEntity revanue;
	private BudDetailEntity expense;
	private FiscalPeriodEntity fiscalPeriod;

	public BudDetailEntity getRevanue() {
		return revanue;
	}

	public void setRevanue(BudDetailEntity revanue) {
		this.revanue = revanue;
	}

	public BudDetailEntity getExpense() {
		return expense;
	}

	public void setExpense(BudDetailEntity expense) {
		this.expense = expense;
	}

	public FiscalPeriodEntity getFiscalPeriod() {
		return fiscalPeriod;
	}

	public void setFiscalPeriod(FiscalPeriodEntity fiscalPeriod) {
		this.fiscalPeriod = fiscalPeriod;
	}
	
	

}
