package org.abacus.transaction.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.abacus.common.shared.AbcBusinessException;
import org.abacus.common.web.JsfDialogHelper;
import org.abacus.common.web.JsfMessageHelper;
import org.abacus.common.web.SessionInfoHelper;
import org.abacus.definition.core.persistance.repository.DefTaskRepository;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.entity.DefItemEntity;
import org.abacus.definition.shared.entity.DefTaskEntity;
import org.abacus.transaction.core.handler.ReqConfirmationHandler;
import org.abacus.transaction.core.handler.TraTransactionHandler;
import org.abacus.transaction.shared.entity.ReqDetailEntity;
import org.abacus.transaction.shared.entity.ReqDetailOfferEntity;
import org.abacus.transaction.shared.entity.ReqDocumentEntity;
import org.abacus.transaction.shared.event.ConfirmDocumentEvent;
import org.abacus.transaction.shared.event.CreateDetailEvent;
import org.abacus.transaction.shared.event.CreateDocumentEvent;
import org.abacus.transaction.shared.event.DeleteDetailEvent;
import org.abacus.transaction.shared.event.DetailCreatedEvent;
import org.abacus.transaction.shared.event.DetailDeletedEvent;
import org.abacus.transaction.shared.event.DetailUpdatedEvent;
import org.abacus.transaction.shared.event.DocumentCreatedEvent;
import org.abacus.transaction.shared.event.DocumentUpdatedEvent;
import org.abacus.transaction.shared.event.ReadDetailEvent;
import org.abacus.transaction.shared.event.ReadDocumentEvent;
import org.abacus.transaction.shared.event.RequestReadDetailEvent;
import org.abacus.transaction.shared.event.RequestReadDocumentEvent;
import org.abacus.transaction.shared.event.UpdateDetailEvent;
import org.abacus.transaction.shared.event.UpdateDocumentEvent;
import org.abacus.transaction.shared.holder.TraDocumentSearchCriteria;
import org.abacus.user.core.persistance.repository.UserOrganizationRepository;
import org.abacus.user.shared.entity.SecUserOrganizationEntity;
import org.springframework.util.CollectionUtils;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CrudPurchaseDocumentViewBean implements Serializable {

	@ManagedProperty(value = "#{jsfMessageHelper}")
	private JsfMessageHelper jsfMessageHelper;

	@ManagedProperty(value = "#{sessionInfoHelper}")
	private SessionInfoHelper sessionInfoHelper;

	@ManagedProperty(value = "#{jsfDialogHelper}")
	private JsfDialogHelper jsfDialogHelper;

	@ManagedProperty(value = "#{reqTransactionHandler}")
	private TraTransactionHandler<ReqDocumentEntity, ReqDetailEntity> transactionHandler;

	@ManagedProperty(value = "#{defTaskRepository}")
	private DefTaskRepository taskRepository;

	@ManagedProperty(value = "#{reqConfirmationHandler}")
	private ReqConfirmationHandler reqConfirmationHandler;
	
	@ManagedProperty(value = "#{userOrganizationRepository}")
	private UserOrganizationRepository userOrgRepo;

	private ReqDocumentEntity document;

	private List<ReqDetailEntity> detailList;
	
	private DefItemEntity vendor;
	


	@PostConstruct
	private void init() {

		String documentId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("document");
		
		String organizationId = sessionInfoHelper.currentOrganizationId();
		String username = sessionInfoHelper.currentUserName();
		this.vendor = userOrgRepo.findVendorByUserAndOrganization(username,organizationId);
		
		
		this.findDocument(Long.valueOf(documentId));

	}
	
	public void offerDetailSelected(){
		
	}

	private void findDocument(Long documentId) {
		TraDocumentSearchCriteria traDocumentSearchCriteria = new TraDocumentSearchCriteria(documentId);

		DefTaskEntity purchaseTaskList = taskRepository.getTaskList(sessionInfoHelper.currentRootOrganizationId(), EnumList.DefTypeEnum.REQ_IO_P.name()).get(0);
		traDocumentSearchCriteria.setDocTask(purchaseTaskList);

		ReadDocumentEvent<ReqDocumentEntity> readDocumentEvent = transactionHandler.readDocumentList(new RequestReadDocumentEvent<ReqDocumentEntity>(traDocumentSearchCriteria, sessionInfoHelper.currentOrganizationId(), sessionInfoHelper.selectedFiscalYearId()));
		if (CollectionUtils.isEmpty(readDocumentEvent.getDocumentList())) {
			document = null;
		} else {
			document = readDocumentEvent.getDocumentList().get(0);
			ReadDetailEvent<ReqDetailEntity> readDetailEvent = transactionHandler.readDetailList(new RequestReadDetailEvent<ReqDetailEntity>(document.getId()));
			detailList = readDetailEvent.getDetails();
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

	public TraTransactionHandler<ReqDocumentEntity, ReqDetailEntity> getTransactionHandler() {
		return transactionHandler;
	}

	public void setTransactionHandler(TraTransactionHandler<ReqDocumentEntity, ReqDetailEntity> transactionHandler) {
		this.transactionHandler = transactionHandler;
	}

	public DefTaskRepository getTaskRepository() {
		return taskRepository;
	}

	public void setTaskRepository(DefTaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	public ReqConfirmationHandler getReqConfirmationHandler() {
		return reqConfirmationHandler;
	}

	public void setReqConfirmationHandler(ReqConfirmationHandler reqConfirmationHandler) {
		this.reqConfirmationHandler = reqConfirmationHandler;
	}

	public ReqDocumentEntity getDocument() {
		return document;
	}

	public void setDocument(ReqDocumentEntity document) {
		this.document = document;
	}

	public List<ReqDetailEntity> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<ReqDetailEntity> detailList) {
		this.detailList = detailList;
	}

}
