package org.abacus.budget.core.handler;

import org.abacus.budget.shared.holder.BudgetHolder;
import org.abacus.organization.shared.entity.FiscalYearEntity;

public interface BudgetHandler {
	
	BudgetHolder summerizeProjectBudget(FiscalYearEntity fiscalYear);

}
