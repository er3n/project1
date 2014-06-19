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
import org.abacus.transaction.core.handler.TraTransactionHandler;
import org.abacus.transaction.shared.entity.StkDetailEntity;
import org.abacus.transaction.shared.entity.StkDocumentEntity;
import org.abacus.transaction.shared.entity.TraDetailEntity;
import org.abacus.transaction.shared.entity.TraDocumentEntity;
import org.abacus.transaction.shared.event.CreateDetailEvent;
import org.abacus.transaction.shared.event.CreateDocumentEvent;
import org.abacus.transaction.shared.event.DetailCreatedEvent;
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

	private TraDocumentEntity document;

	private List<DefTaskEntity> allTaskList;

	private Boolean showDocument = true;

	private List<StkDetailEntity> detailList;

	private TraDetailEntity selectedDetail;

	private EnumList.DefTypeGroupEnum selectedGroupEnum;
	
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

		String operation = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("operation");
		this.initSelections();
		if (operation.equals("create")) {
			this.initNewDocument();
		} else if (operation.equals("detail") || operation.equals("update")) {
			String documentId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("document");
			this.findDocument(Long.valueOf(documentId));
			if (document == null) {
				jsfMessageHelper.addError("noDocumentFind", selectedGroupEnum.getDescription());
			}
		}
	}

	private void initSelections() {
		allTaskList = taskRepository.getTaskList(sessionInfoHelper.currentRootOrganizationId(), EnumList.DefTypeGroupEnum.STK.name());
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

	public void saveDetail() {
		try {
			DetailCreatedEvent event = transactionHandler.newDetail(new CreateDetailEvent(selectedDetail, sessionInfoHelper.currentUserName()));
			this.findDocument(document.getId());
			selectedDetail = null;
			jsfMessageHelper.addInfo("createSuccessfull", "Fiş Detay");
		} catch (AbcBusinessException e) {
			jsfMessageHelper.addError(e);
		}
	}

	private void findDocument(Long documentId) {
		TraDocumentSearchCriteria traDocumentSearchCriteria = new TraDocumentSearchCriteria(documentId);
		traDocumentSearchCriteria.setDocumentGroupEnum(selectedGroupEnum);
		
		ReadDocumentEvent readDocumentEvent = transactionHandler.readDocument(new RequestReadDocumentEvent(traDocumentSearchCriteria, sessionInfoHelper.currentOrganizationId(), sessionInfoHelper.selectedFiscalYearId()));
		if (CollectionUtils.isEmpty(readDocumentEvent.getDocumentList())) {
			document = null;
		} else {
			document = readDocumentEvent.getDocumentList().get(0);
			ReadDetailEvent readDetailEvent = transactionHandler.readDetail(new RequestReadDetailEvent(document.getId()));
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

	public TraDocumentEntity getDocument() {
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

	public Boolean getShowDocument() {
		return showDocument;
	}

	public void setShowDocument(Boolean showDocument) {
		this.showDocument = showDocument;
	}

	public List<StkDetailEntity> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<StkDetailEntity> detailList) {
		this.detailList = detailList;
	}

	public TraDetailEntity getSelectedDetail() {
		return selectedDetail;
	}

	public void setSelectedDetail(StkDetailEntity selectedDetail) {
		this.selectedDetail = selectedDetail;
	}

	public EnumList.DefTypeGroupEnum getSelectedGroupEnum() {
		return selectedGroupEnum;
	}

	public void setSelectedGroupEnum(EnumList.DefTypeGroupEnum selectedGroupEnum) {
		this.selectedGroupEnum = selectedGroupEnum;
	}

}
