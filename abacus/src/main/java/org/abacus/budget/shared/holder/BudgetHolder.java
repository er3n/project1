package org.abacus.budget.shared.holder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.abacus.budget.shared.entity.BudDetailEntity;
import org.abacus.budget.shared.entity.BudDocumentEntity;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.organization.shared.entity.FiscalPeriodEntity;
import org.springframework.util.CollectionUtils;

@SuppressWarnings("serial")
public class BudgetHolder implements Serializable {

	private BudDocumentEntity document;

	private List<BudgetPeriodHolder> periodList;

	private BigDecimal sum;
	private BigDecimal revanueSum;
	private BigDecimal expenseSum;

	public BudgetHolder() {
		this.periodList = new ArrayList<BudgetPeriodHolder>();
	}

	public BudgetHolder(BudDocumentEntity document) {
		this();
		if (document == null) {
			this.document = new BudDocumentEntity();
		} else {
			this.document = document;
		}
	}

	public BudDocumentEntity getDocument() {
		return document;
	}

	public void setDocument(BudDocumentEntity document) {
		this.document = document;
	}

	public List<BudgetPeriodHolder> getPeriodList() {
		return periodList;
	}

	public void setPeriodList(List<BudgetPeriodHolder> periodList) {
		this.periodList = periodList;
	}

	public void addNullSafePeriod(List<BudDetailEntity> details, FiscalPeriodEntity fiscalPeriod) {

		BudDetailEntity revanue = null;
		BudDetailEntity expense = null;

		if (!CollectionUtils.isEmpty(details)) {
			for (BudDetailEntity detail : details) {
				if (detail.getBudgetRX().equals(EnumList.BudgetRX.BUD_R)) {
					revanue = detail;
				} else if (detail.getBudgetRX().equals(EnumList.BudgetRX.BUD_X)) {
					expense = detail;
				}
			}
		}

		if (revanue == null) {
			revanue = new BudDetailEntity(EnumList.BudgetRX.BUD_R);
			revanue.setFiscalPeriod(fiscalPeriod);
		}
		if (expense == null) {
			expense = new BudDetailEntity(EnumList.BudgetRX.BUD_X);
			expense.setFiscalPeriod(fiscalPeriod);
		}

		BudgetPeriodHolder holder = new BudgetPeriodHolder();

		holder.setExpense(expense);
		holder.setRevanue(revanue);
		holder.setFiscalPeriod(fiscalPeriod);

		periodList.add(holder);

	}

	public BigDecimal getSum() {
		return sum;
	}

	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}

	public BigDecimal getRevanueSum() {
		return revanueSum;
	}

	public void setRevanueSum(BigDecimal revanueSum) {
		this.revanueSum = revanueSum;
	}

	public BigDecimal getExpenseSum() {
		return expenseSum;
	}

	public void setExpenseSum(BigDecimal expenseSum) {
		this.expenseSum = expenseSum;
	}

}
