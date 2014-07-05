package org.abacus.transaction.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.abacus.common.web.JsfDialogHelper;
import org.abacus.common.web.JsfMessageHelper;
import org.abacus.common.web.SessionInfoHelper;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.transaction.core.handler.TraTransactionHandler;
import org.abacus.transaction.shared.UnableToDeleteDetailException;
import org.abacus.transaction.shared.UnableToUpdateDocumentExpception;
import org.abacus.transaction.shared.entity.FinDetailEntity;
import org.abacus.transaction.shared.entity.FinDocumentEntity;
import org.abacus.transaction.shared.event.CancelDocumentEvent;
import org.abacus.transaction.shared.event.DeleteDocumentEvent;
import org.abacus.transaction.shared.event.ReadDocumentEvent;
import org.abacus.transaction.shared.event.RequestReadDocumentEvent;
import org.abacus.transaction.shared.holder.TraDocumentSearchCriteria;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class FindFinDocumentViewBean implements Serializable {

	@ManagedProperty(value = "#{jsfMessageHelper}")
	private JsfMessageHelper jsfMessageHelper;

	@ManagedProperty(value = "#{sessionInfoHelper}")
	private SessionInfoHelper sessionInfoHelper;

	@ManagedProperty(value = "#{jsfDialogHelper}")
	private JsfDialogHelper jsfDialogHelper;

	private TraDocumentSearchCriteria documentSearchCriteria;

	@ManagedProperty(value = "#{finTransactionHandler}")
	private TraTransactionHandler<FinDocumentEntity, FinDetailEntity> transactionHandler;

	private List<FinDocumentEntity> documentSearchResultList;

	private EnumList.DefTypeGroupEnum selectedGroupEnum;
	private EnumList.DefTypeEnum selectedTypeEnum;

	private Boolean showDocument = true;

	@PostConstruct
	private void init() {
		try {
			String grp = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("grp");
			String typ = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("typ");
			selectedGroupEnum = EnumList.DefTypeGroupEnum.valueOf(grp.toUpperCase());
			selectedTypeEnum = EnumList.DefTypeEnum.valueOf(typ.toUpperCase());
		} catch (Exception e) {
			jsfMessageHelper.addWarn("noDocumentGroupDestked");
			this.showDocument = false;
		}
		if (sessionInfoHelper.currentUser().getSelectedFiscalYear() == null) {
			jsfMessageHelper.addWarn("noFiscalYearDestked");
			this.showDocument = false;
		}
		documentSearchCriteria = new TraDocumentSearchCriteria();
		documentSearchCriteria.setDocType(selectedTypeEnum);
	}

	public Boolean isTaskSelected(FinDocumentEntity document, EnumList.DefTypeEnum taskEnum) {
		if (document == null || document.getTask() == null) {
			return false;
		}
		boolean result = document.getTask().getType().getId().startsWith(taskEnum.name());
		return result;
	}

	public Boolean isTaskSelectedState(FinDocumentEntity document, Integer trState) {
		if (document == null || document.getTask() == null) {
			return false;
		}
		boolean result = document.getTask().getType().getTrStateType().compareTo(trState)==0;
		return result;
	}

	public void findFinDocument() {
		ReadDocumentEvent<FinDocumentEntity> readDocumentEvent = transactionHandler.readDocumentList(new RequestReadDocumentEvent<FinDocumentEntity>(documentSearchCriteria, sessionInfoHelper.currentOrganizationId(), sessionInfoHelper.selectedFiscalYearId()));
		documentSearchResultList = readDocumentEvent.getDocumentList();
	}

	public void cancelDocument(FinDocumentEntity document) {
		try {
			transactionHandler.cancelDocument(new CancelDocumentEvent<FinDocumentEntity>(document, sessionInfoHelper.currentUserName()));
			this.findFinDocument();
		} catch (UnableToUpdateDocumentExpception e) {
			jsfMessageHelper.addError(e);
		}
	}

	public void deleteDocument(FinDocumentEntity document) {
		try {
			transactionHandler.deleteDocument(new DeleteDocumentEvent<FinDocumentEntity>(document));
			this.findFinDocument();
		} catch (UnableToDeleteDetailException e) {
			jsfMessageHelper.addError(e);
		}
	}

	public JsfMessageHelper getJsfMessageHelper() {
		return jsfMessageHelper;
	}

	public void setJsfMessageHelper(JsfMessageHelper jsfMessageHelper) {
		this.jsfMessageHelper = jsfMessageHelper;
	}

	public SessionInfoHelper getSessionInfoHelper() {
		return sessionInfoHelper;
	}

	public void setSessionInfoHelper(SessionInfoHelper sessionInfoHelper) {
		this.sessionInfoHelper = sessionInfoHelper;
	}

	public JsfDialogHelper getJsfDialogHelper() {
		return jsfDialogHelper;
	}

	public void setJsfDialogHelper(JsfDialogHelper jsfDialogHelper) {
		this.jsfDialogHelper = jsfDialogHelper;
	}

	public TraDocumentSearchCriteria getDocumentSearchCriteria() {
		return documentSearchCriteria;
	}

	public void setDocumentSearchCriteria(TraDocumentSearchCriteria documentSearchCriteria) {
		this.documentSearchCriteria = documentSearchCriteria;
	}

	public TraTransactionHandler<FinDocumentEntity, FinDetailEntity> getTransactionHandler() {
		return transactionHandler;
	}

	public void setTransactionHandler(TraTransactionHandler<FinDocumentEntity, FinDetailEntity> transactionHandler) {
		this.transactionHandler = transactionHandler;
	}

	public List<FinDocumentEntity> getDocumentSearchResultList() {
		return documentSearchResultList;
	}

	public void setDocumentSearchResultList(List<FinDocumentEntity> documentSearchResultList) {
		this.documentSearchResultList = documentSearchResultList;
	}

	public Boolean getShowDocument() {
		return showDocument;
	}

	public void setShowDocument(Boolean showDocument) {
		this.showDocument = showDocument;
	}

	public EnumList.DefTypeGroupEnum getSelectedGroupEnum() {
		return selectedGroupEnum;
	}

	public void setSelectedGroupEnum(EnumList.DefTypeGroupEnum selectedGroupEnum) {
		this.selectedGroupEnum = selectedGroupEnum;
	}

	public EnumList.DefTypeEnum getSelectedTypeEnum() {
		return selectedTypeEnum;
	}

	public void setSelectedTypeEnum(EnumList.DefTypeEnum selectedTypeEnum) {
		this.selectedTypeEnum = selectedTypeEnum;
	}

}
