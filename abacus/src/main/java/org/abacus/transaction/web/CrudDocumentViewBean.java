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
import org.abacus.definition.shared.entity.DefTaskEntity;
import org.abacus.definition.shared.holder.ItemSearchCriteria;
import org.abacus.definition.web.model.ItemDataModel;
import org.abacus.transaction.core.handler.TraTransactionHandler;
import org.abacus.transaction.shared.entity.StkDetailEntity;
import org.abacus.transaction.shared.entity.StkDocumentEntity;
import org.abacus.transaction.shared.event.CreateDocumentEvent;
import org.abacus.transaction.shared.event.DocumentCreatedEvent;
import org.abacus.transaction.shared.event.ReadDetailEvent;
import org.abacus.transaction.shared.event.ReadDocumentEvent;
import org.abacus.transaction.shared.event.RequestReadDetailEvent;
import org.abacus.transaction.shared.event.RequestReadDocumentEvent;
import org.abacus.transaction.shared.holder.TraDocumentSearchCriteria;
import org.springframework.util.CollectionUtils;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CrudDocumentViewBean implements Serializable {

	@ManagedProperty(value = "#{jsfMessageHelper}")
	private JsfMessageHelper jsfMessageHelper;

	@ManagedProperty(value = "#{sessionInfoHelper}")
	private SessionInfoHelper sessionInfoHelper;

	@ManagedProperty(value = "#{jsfDialogHelper}")
	private JsfDialogHelper jsfDialogHelper;

	@ManagedProperty(value = "#{stkTransactionHandler}")
	private TraTransactionHandler transactionHandler;

	@ManagedProperty(value = "#{defTaskRepository}")
	private DefTaskRepository taskRepository;

	private StkDocumentEntity document;

	private List<DefTaskEntity> allTaskList;

	private boolean hasFiscalYear;;

	private List<StkDetailEntity> detailList;

	private StkDetailEntity selectedDetail;

	private ItemDataModel itemDataModel;

	@PostConstruct
	private void init() {

		this.checkFiscalYear();
		if (!hasFiscalYear){
			jsfMessageHelper.addWarn("noFiscalYearDefined");
			return;
		}
			

		String operation = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("operation");
		this.initSelections();
		if (operation.equals("create")) {
			this.initNewDocument();
		} else if (operation.equals("detail") || operation.equals("update")) {
			String documentId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("document");
			this.findDocument(Long.valueOf(documentId));
			if (document == null) {
				jsfMessageHelper.addError("noDocumentFind");
			}
		}
	}

	private void checkFiscalYear() {
		this.hasFiscalYear = sessionInfoHelper.currentUser().getSelectedFiscalYear() != null;
	}

	private void initSelections() {
		allTaskList = taskRepository.getTaskList(sessionInfoHelper.currentRootOrganizationId(), EnumList.DefTypeGroupEnum.STK.name());
		itemDataModel = new ItemDataModel(new ItemSearchCriteria(sessionInfoHelper.currentRootOrganization(), EnumList.DefTypeEnum.ITM_SR_ST, null));

	}

	private void initNewDocument() {
		document = new StkDocumentEntity();
	}

	public void saveDocument() {
		try {
			DocumentCreatedEvent documentCreatedEvent = transactionHandler.newDocument(new CreateDocumentEvent(document, sessionInfoHelper.currentUserName(), sessionInfoHelper.currentOrganizationId(), sessionInfoHelper.selectedFiscalYearId()));
			document = (StkDocumentEntity) documentCreatedEvent.getDocument();
			this.findDocument(document.getId());
			jsfMessageHelper.addInfo("createSuccessfull", "Fiş");
		} catch (AbcBusinessException e) {
			jsfMessageHelper.addError(e);
		}

	}

	private void findDocument(Long documentId) {
		ReadDocumentEvent readDocumentEvent = transactionHandler.readDocument(new RequestReadDocumentEvent(new TraDocumentSearchCriteria(documentId), sessionInfoHelper.currentOrganizationId(), sessionInfoHelper.selectedFiscalYearId()));
		if (CollectionUtils.isEmpty(readDocumentEvent.getDocumentList())) {
			document = null;
		} else {
			document = (StkDocumentEntity) readDocumentEvent.getDocumentList().get(0);
			ReadDetailEvent readDetailEvent =  transactionHandler.readDetail(new RequestReadDetailEvent(document.getId()));
			detailList = readDetailEvent.getDetails();
		}
	}

	public void initNewDetail() {
		selectedDetail = new StkDetailEntity();
		selectedDetail.setDocument(document);
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

	public TraTransactionHandler getTransactionHandler() {
		return transactionHandler;
	}

	public void setTransactionHandler(TraTransactionHandler transactionHandler) {
		this.transactionHandler = transactionHandler;
	}

	public DefTaskRepository getTaskRepository() {
		return taskRepository;
	}

	public void setTaskRepository(DefTaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	public StkDocumentEntity getDocument() {
		return document;
	}

	public void setDocument(StkDocumentEntity document) {
		this.document = document;
	}

	public List<DefTaskEntity> getAllTaskList() {
		return allTaskList;
	}

	public void setAllTaskList(List<DefTaskEntity> allTaskList) {
		this.allTaskList = allTaskList;
	}

	public boolean isHasFiscalYear() {
		return hasFiscalYear;
	}

	public void setHasFiscalYear(boolean hasFiscalYear) {
		this.hasFiscalYear = hasFiscalYear;
	}

	public List<StkDetailEntity> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<StkDetailEntity> detailList) {
		this.detailList = detailList;
	}

	public StkDetailEntity getSelectedDetail() {
		return selectedDetail;
	}

	public void setSelectedDetail(StkDetailEntity selectedDetail) {
		this.selectedDetail = selectedDetail;
	}

	public ItemDataModel getItemDataModel() {
		return itemDataModel;
	}

	public void setItemDataModel(ItemDataModel itemDataModel) {
		this.itemDataModel = itemDataModel;
	}

}
