package org.abacus.budget.shared.holder;

import java.io.Serializable;
import java.util.List;

import org.abacus.budget.shared.entity.BudDocumentEntity;

@SuppressWarnings("serial")
public class BudgetHolder implements Serializable {

	private BudDocumentEntity document;

	private List<BudgetPeriodHolder> periodList;

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

}
