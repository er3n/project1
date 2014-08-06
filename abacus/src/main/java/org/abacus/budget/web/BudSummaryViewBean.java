package org.abacus.budget.web;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.abacus.budget.core.handler.BudgetHandler;
import org.abacus.budget.shared.entity.BudDetailEntity;
import org.abacus.budget.shared.entity.BudDocumentEntity;
import org.abacus.budget.shared.event.CreateBudDocumentEvent;
import org.abacus.budget.shared.event.CreateBudgetDetailEvent;
import org.abacus.budget.shared.event.UpdateBudDocumentEvent;
import org.abacus.budget.shared.event.UpdateBudgetDetailEvent;
import org.abacus.budget.shared.holder.BudgetHolder;
import org.abacus.budget.shared.holder.BudgetPeriodHolder;
import org.abacus.common.web.JsfMessageHelper;
import org.abacus.common.web.SessionInfoHelper;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.organization.shared.entity.FiscalYearEntity;
import org.primefaces.event.CellEditEvent;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class BudSummaryViewBean implements Serializable {

	@ManagedProperty(value = "#{sessionInfoHelper}")
	private SessionInfoHelper sessionInfoHelper;

	@ManagedProperty(value = "#{jsfMessageHelper}")
	private JsfMessageHelper jsfMessageHelper;

	@ManagedProperty(value = "#{budgetHandler}")
	private BudgetHandler budgetHandler;

	private boolean showPage = true;

	private BudgetHolder budget;

	@PostConstruct
	public void init() {

		FiscalYearEntity fiscalYear = sessionInfoHelper.currentFiscalYear();

		if (fiscalYear == null) {
			showPage = false;
			jsfMessageHelper.addWarn("noFiscalYearDefined");
			return;
		}

		this.budget = budgetHandler.summerizeProjectBudget(fiscalYear);

	}

	public void saveDocument() {
		BudDocumentEntity document = budget.getDocument();
		document.setFiscalYear(sessionInfoHelper.currentFiscalYear());
		document = budgetHandler.createDocument(new CreateBudDocumentEvent(document, sessionInfoHelper.currentUserName()));
		budget.setDocument(document);
		jsfMessageHelper.addInfo("createSuccessful", "Fiş");
	}

	public void updateDocument() {
		BudDocumentEntity document = budgetHandler.updateDocument(new UpdateBudDocumentEvent(budget.getDocument(), sessionInfoHelper.currentUserName()));
		budget.setDocument(document);
		jsfMessageHelper.addInfo("updateSuccessful", "Fiş");
	}

	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();

		boolean isValueChanged = newValue != null && !newValue.equals(oldValue);
		if (!isValueChanged) {
			return;
		}

		String clientId = event.getColumn().getClientId();
		BudDetailEntity detail = this.findSelectedCell(clientId);
		
		if(detail.getId() == null){
			budget = budgetHandler.createDetail(new CreateBudgetDetailEvent(budget.getDocument(),detail,sessionInfoHelper.currentUser()));
			jsfMessageHelper.addInfo("createSuccessful", "Bütçe");
		}else{
			budget = budgetHandler.updateDetail(new UpdateBudgetDetailEvent(detail,sessionInfoHelper.currentFiscalYear(),sessionInfoHelper.currentUser()));
			jsfMessageHelper.addInfo("updateSuccessful", "Bütçe");
		}

	}

	private BudDetailEntity findSelectedCell(String clientId) {
		String[] chars = clientId.split(":");
		
		Integer index = Integer.valueOf(chars[2]);
		String budgetRXString = chars[3];
		
		BudgetPeriodHolder periodHolder = budget.getPeriodList().get(index);
		
		if(EnumList.BudgetRX.BUD_R.name().equals(budgetRXString)){
			return periodHolder.getRevanue();
		}else{
			return periodHolder.getExpense();
		}
		
	}

	public static void main(String[] args) {
		String clientId = "periodForm:periodTbl:0:revanue";

		String[] chars = clientId.split(":");
		System.out.println(chars[2]);
		System.out.println(chars[3]);

	}

	public SessionInfoHelper getSessionInfoHelper() {
		return sessionInfoHelper;
	}

	public void setSessionInfoHelper(SessionInfoHelper sessionInfoHelper) {
		this.sessionInfoHelper = sessionInfoHelper;
	}

	public JsfMessageHelper getJsfMessageHelper() {
		return jsfMessageHelper;
	}

	public void setJsfMessageHelper(JsfMessageHelper jsfMessageHelper) {
		this.jsfMessageHelper = jsfMessageHelper;
	}

	public BudgetHandler getBudgetHandler() {
		return budgetHandler;
	}

	public void setBudgetHandler(BudgetHandler budgetHandler) {
		this.budgetHandler = budgetHandler;
	}

	public boolean isShowPage() {
		return showPage;
	}

	public void setShowPage(boolean showPage) {
		this.showPage = showPage;
	}

	public BudgetHolder getBudget() {
		return budget;
	}

	public void setBudget(BudgetHolder budget) {
		this.budget = budget;
	}

}
