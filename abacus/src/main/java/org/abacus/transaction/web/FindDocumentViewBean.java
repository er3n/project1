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
import org.abacus.transaction.shared.entity.TraDocumentEntity;
import org.abacus.transaction.shared.event.ReadDocumentEvent;
import org.abacus.transaction.shared.event.RequestReadDocumentEvent;
import org.abacus.transaction.shared.holder.TraDocumentSearchCriteria;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class FindDocumentViewBean implements Serializable {

	@ManagedProperty(value = "#{jsfMessageHelper}")
	private JsfMessageHelper jsfMessageHelper;

	@ManagedProperty(value = "#{sessionInfoHelper}")
	private SessionInfoHelper sessionInfoHelper;

	@ManagedProperty(value = "#{jsfDialogHelper}")
	private JsfDialogHelper jsfDialogHelper;

	private TraDocumentSearchCriteria documentSearchCriteria;

	@ManagedProperty(value = "#{stkTransactionHandler}")
	private TraTransactionHandler transactionHandler;

	private List<TraDocumentEntity> documentSearchResultList;
	private EnumList.DefTypeGroupEnum selectedGroupEnum;

	private Boolean showDocument = true; 

	@PostConstruct
	private void init() {
		try{
			String grp = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("grp");
			selectedGroupEnum = EnumList.DefTypeGroupEnum.valueOf(grp.toUpperCase());
		}catch(Exception e){
			jsfMessageHelper.addWarn("noDocumentGroupDefined");
			this.showDocument = false;
		}
		if (sessionInfoHelper.currentUser().getSelectedFiscalYear() == null){
			jsfMessageHelper.addWarn("noFiscalYearDefined");
			this.showDocument = false;
		}
		documentSearchCriteria = new TraDocumentSearchCriteria();
		documentSearchCriteria.setDocumentGroupEnum(selectedGroupEnum);
	}

	public void findDocument() {
		ReadDocumentEvent readDocumentEvent = transactionHandler.readDocument(new RequestReadDocumentEvent(documentSearchCriteria, sessionInfoHelper.currentOrganizationId(), sessionInfoHelper.selectedFiscalYearId()));
		documentSearchResultList = readDocumentEvent.getDocumentList();
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

	public TraTransactionHandler getTransactionHandler() {
		return transactionHandler;
	}

	public void setTransactionHandler(TraTransactionHandler transactionHandler) {
		this.transactionHandler = transactionHandler;
	}

	public List<TraDocumentEntity> getDocumentSearchResultList() {
		return documentSearchResultList;
	}

	public void setDocumentSearchResultList(List<TraDocumentEntity> documentSearchResultList) {
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

}
