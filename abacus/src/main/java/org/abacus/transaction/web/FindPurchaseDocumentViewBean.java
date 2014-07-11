package org.abacus.transaction.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EnumType;

import org.abacus.common.web.JsfDialogHelper;
import org.abacus.common.web.JsfMessageHelper;
import org.abacus.common.web.SessionInfoHelper;
import org.abacus.definition.core.persistance.repository.DefTaskRepository;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.entity.DefTaskEntity;
import org.abacus.transaction.core.handler.ReqConfirmationHandler;
import org.abacus.transaction.core.handler.TraTransactionHandler;
import org.abacus.transaction.shared.UnableToDeleteDetailException;
import org.abacus.transaction.shared.entity.ReqDetailEntity;
import org.abacus.transaction.shared.entity.ReqDocumentEntity;
import org.abacus.transaction.shared.event.DeleteDocumentEvent;
import org.abacus.transaction.shared.event.ReadDocumentEvent;
import org.abacus.transaction.shared.event.RequestReadDocumentEvent;
import org.abacus.transaction.shared.holder.TraDocumentSearchCriteria;
import org.apache.log4j.Logger;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class FindPurchaseDocumentViewBean implements Serializable {

	@ManagedProperty(value = "#{jsfMessageHelper}")
	private JsfMessageHelper jsfMessageHelper;

	@ManagedProperty(value = "#{sessionInfoHelper}")
	private SessionInfoHelper sessionInfoHelper;

	@ManagedProperty(value = "#{jsfDialogHelper}")
	private JsfDialogHelper jsfDialogHelper;

	@ManagedProperty(value = "#{defTaskRepository}")
	private DefTaskRepository taskRepository;

	@ManagedProperty(value = "#{reqTransactionHandler}")
	private TraTransactionHandler<ReqDocumentEntity, ReqDetailEntity> transactionHandler;

	private TraDocumentSearchCriteria documentSearchCriteria;

	private List<ReqDocumentEntity> documentSearchResultList;

	@PostConstruct
	private void init() {
		DefTaskEntity purchaseTaskList = taskRepository.getTaskList(sessionInfoHelper.currentRootOrganizationId(), EnumList.DefTypeEnum.REQ_IO_P.name()).get(0);
		documentSearchCriteria = new TraDocumentSearchCriteria();
		documentSearchCriteria.setDocType(EnumList.DefTypeEnum.REQ_IO);
		documentSearchCriteria.setDocTask(purchaseTaskList);
	}

	public void findDocument() {
		ReadDocumentEvent<ReqDocumentEntity> readDocumentEvent = transactionHandler.readDocumentList(new RequestReadDocumentEvent<ReqDocumentEntity>(documentSearchCriteria, sessionInfoHelper.currentOrganizationId(), sessionInfoHelper.selectedFiscalYearId()));
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

	public DefTaskRepository getTaskRepository() {
		return taskRepository;
	}

	public void setTaskRepository(DefTaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	public TraTransactionHandler<ReqDocumentEntity, ReqDetailEntity> getTransactionHandler() {
		return transactionHandler;
	}

	public void setTransactionHandler(TraTransactionHandler<ReqDocumentEntity, ReqDetailEntity> transactionHandler) {
		this.transactionHandler = transactionHandler;
	}

	public TraDocumentSearchCriteria getDocumentSearchCriteria() {
		return documentSearchCriteria;
	}

	public void setDocumentSearchCriteria(TraDocumentSearchCriteria documentSearchCriteria) {
		this.documentSearchCriteria = documentSearchCriteria;
	}

	public List<ReqDocumentEntity> getDocumentSearchResultList() {
		return documentSearchResultList;
	}

	public void setDocumentSearchResultList(List<ReqDocumentEntity> documentSearchResultList) {
		this.documentSearchResultList = documentSearchResultList;
	}

}