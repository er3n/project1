package org.abacus.budget.core.handler;

import org.abacus.budget.shared.entity.BudDocumentEntity;
import org.abacus.budget.shared.event.CreateBudDocumentEvent;
import org.abacus.budget.shared.event.CreateBudgetDetailEvent;
import org.abacus.budget.shared.event.UpdateBudDocumentEvent;
import org.abacus.budget.shared.event.UpdateBudgetDetailEvent;
import org.abacus.budget.shared.holder.BudgetHolder;
import org.abacus.organization.shared.entity.FiscalPeriodEntity;
import org.abacus.organization.shared.entity.FiscalYearEntity;

public interface BudgetHandler {
	
	BudgetHolder summerizeProjectBudget(FiscalYearEntity fiscalYear);

	BudDocumentEntity createDocument(CreateBudDocumentEvent createBudDocumentEvent);

	BudDocumentEntity updateDocument(UpdateBudDocumentEvent updateBudDocumentEvent);

	BudgetHolder createDetail(CreateBudgetDetailEvent createBudgetDetailEvent);

	BudgetHolder updateDetail(UpdateBudgetDetailEvent updateBudgetDetailEvent);

	void convertBudget(FiscalPeriodEntity period);
	
}
