package org.abacus.budget.shared.event;

import org.abacus.budget.shared.entity.BudDetailEntity;
import org.abacus.common.security.SecUser;
import org.abacus.common.shared.event.UpdatedEvent;
import org.abacus.organization.shared.entity.FiscalYearEntity;

public class UpdateBudgetDetailEvent extends UpdatedEvent {

	private SecUser user;
	private BudDetailEntity detail;
	private FiscalYearEntity fiscalYear;

	public UpdateBudgetDetailEvent(BudDetailEntity detail, FiscalYearEntity fiscalYear, SecUser currentUser) {
		this.detail = detail;
		this.user = currentUser;
		this.fiscalYear = fiscalYear;
	}

	public SecUser getUser() {
		return user;
	}

	public void setUser(SecUser user) {
		this.user = user;
	}

	public BudDetailEntity getDetail() {
		return detail;
	}

	public void setDetail(BudDetailEntity detail) {
		this.detail = detail;
	}

	public FiscalYearEntity getFiscalYear() {
		return fiscalYear;
	}

	public void setFiscalYear(FiscalYearEntity fiscalYear) {
		this.fiscalYear = fiscalYear;
	}

}
