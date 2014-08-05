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
import org.abacus.definition.core.handler.DefTaskHandler;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.entity.DefTaskEntity;
import org.abacus.transaction.core.handler.TraTransactionHandler;
import org.abacus.transaction.shared.entity.StkDetailEntity;
import org.abacus.transaction.shared.entity.StkDocumentEntity;
import org.abacus.transaction.shared.entity.TraDocumentEntity;
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
import org.springframework.util.CollectionUtils;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CrudStkDocumentViewBean implements Serializable {

	@ManagedProperty(value = "#{jsfMessageHelper}")
	private JsfMessageHelper jsfMessageHelper;

	@ManagedProperty(value = "#{sessionInfoHelper}")
	private SessionInfoHelper sessionInfoHelper;

	@ManagedProperty(value = "#{jsfDialogHelper}")
	private JsfDialogHelper jsfDialogHelper;

	@ManagedProperty(value = "#{stkTransactionHandler}")
	private TraTransactionHandler<StkDocumentEntity, StkDetailEntity> transactionHandler;

	@ManagedProperty(value = "#{defTaskHandler}")
	private DefTaskHandler taskRepository;

	private StkDocumentEntity document;

	private List<DefTaskEntity> stkTaskList;

	private Boolean showDocument = true;

	private List<StkDetailEntity> detailList;

	private StkDetailEntity selectedDetail;

	private EnumList.DefTypeGroupEnum selectedGroupEnum;

	private EnumList.DefTypeEnum selectedDetailServiceType;
	private EnumList.DefTypeEnum selectedTypeEnum;
	
	@PostConstruct
	private void init() {
		try {
			String grp = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("grp");
			String typ = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("typ");
			selectedGroupEnum = EnumList.DefTypeGroupEnum.valueOf(grp.toUpperCase());
			if (typ!=null){
				selectedTypeEnum = EnumList.DefTypeEnum.valueOf(typ.toUpperCase());
			}
		} catch (Exception e) {
			jsfMessageHelper.addWarn("noDocumentGroupDefined");
			this.showDocument = false;
		}
		if (sessionInfoHelper.currentUser().getSelectedFiscalYear() == null) {
			jsfMessageHelper.addWarn("noFiscalYearDefined");
			this.showDocument = false;
		}

		String operation = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("operation");
		if (operation.equals("create")) {
			this.initSelections();
			this.initNewDocument();
		} else if (operation.equals("detail") || operation.equals("update")) {
			String documentId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("document");
			this.findStkDocument(Long.valueOf(documentId));
			if (document == null) {
				jsfMessageHelper.addError("noDocumentFind", selectedGroupEnum.getDescription());
				this.showDocument = false;
			} else {
				selectedTypeEnum = document.getTypeEnum(); 
				this.initSelections();
			}
		}
	}

	private void initSelections() {
		stkTaskList = taskRepository.getTaskList(sessionInfoHelper.currentOrganization(), selectedTypeEnum);
		selectedDetailServiceType = EnumList.DefTypeEnum.ITM_SR_ST;
	}

	private void initNewDocument() {
		document = new StkDocumentEntity();
	}

	public void saveDocument() {
		try {
			DocumentCreatedEvent<StkDocumentEntity> documentCreatedEvent = transactionHandler.newDocument(new CreateDocumentEvent<StkDocumentEntity>(document, sessionInfoHelper.currentUserName(), sessionInfoHelper.currentOrganization(), sessionInfoHelper.currentFiscalYear()));
			document = (StkDocumentEntity) documentCreatedEvent.getDocument();
			this.findStkDocument(document.getId());
			jsfMessageHelper.addInfo("createSuccessful", "Fiş");
		} catch (AbcBusinessException e) {
			jsfMessageHelper.addError(e);
		}

	}

	public void updateDocument() {
		try {
			DocumentUpdatedEvent<StkDocumentEntity> documentUpdatedEvent = transactionHandler.updateDocument(new UpdateDocumentEvent<StkDocumentEntity>(document, sessionInfoHelper.currentUserName()));
			this.findStkDocument(document.getId());
			jsfMessageHelper.addInfo("updateSuccessful", "Fiş");
		} catch (AbcBusinessException e) {
			jsfMessageHelper.addError(e);
		}
	}

	public void saveDetail() {
		try {
			DetailCreatedEvent<StkDetailEntity> event = transactionHandler.newDetail(new CreateDetailEvent<StkDetailEntity>(selectedDetail, sessionInfoHelper.currentUserName()));
			this.findStkDocument(document.getId());
			selectedDetail = null;
			jsfMessageHelper.addInfo("createSuccessful", "Fiş Detay");
		} catch (AbcBusinessException e) {
			jsfMessageHelper.addError(e);
		}
	}

	private void findStkDocument(Long documentId) {
		TraDocumentSearchCriteria traDocumentSearchCriteria = new TraDocumentSearchCriteria(documentId);

		ReadDocumentEvent<StkDocumentEntity> readDocumentEvent = transactionHandler.readDocumentList(new RequestReadDocumentEvent<StkDocumentEntity>(traDocumentSearchCriteria, sessionInfoHelper.currentOrganization(), sessionInfoHelper.currentFiscalYear()));
		if (CollectionUtils.isEmpty(readDocumentEvent.getDocumentList())) {
			document = null;
		} else {
			document = readDocumentEvent.getDocumentList().get(0);
			ReadDetailEvent<StkDetailEntity> readDetailEvent = transactionHandler.readDetailList(new RequestReadDetailEvent<StkDetailEntity>(document.getId()));
			detailList = readDetailEvent.getDetails();
		}
	}
	
	public void updateDetailSelected(StkDetailEntity detail){
		this.selectedDetail = detail;
		this.selectedDetail.setPoint();
		boolean isStkType = EnumList.DefTypeEnum.ITM_SR_ST.name().equals(this.selectedDetail.getItem().getType().getId());
		if(isStkType){
			this.selectedDetailServiceType = EnumList.DefTypeEnum.ITM_SR_ST;
		}else{
			this.selectedDetailServiceType = EnumList.DefTypeEnum.ITM_SR_FN;
		}
	}

	public void updateDetail() {
		try {
			selectedDetail.setDocument(document);
			DetailUpdatedEvent<StkDetailEntity> detailUpdatedEvent = transactionHandler.updateDetail(new UpdateDetailEvent<StkDetailEntity>(selectedDetail, sessionInfoHelper.currentUserName()));
			this.findStkDocument(document.getId());
			this.selectedDetail = null;
			jsfMessageHelper.addInfo("updateSuccessful", "Fiş Detay");
		} catch (AbcBusinessException e) {
			jsfMessageHelper.addError(e);
		}
	}

	public void deleteDetail(StkDetailEntity detail) {
		try {
			DetailDeletedEvent<StkDetailEntity> deleteDetailEvent = transactionHandler.deleteDetail(new DeleteDetailEvent<StkDetailEntity>(detail));
			this.findStkDocument(document.getId());
			jsfMessageHelper.addInfo("deleteSuccessful", "Fiş Detay");
		} catch (AbcBusinessException e) {
			jsfMessageHelper.addError(e);
		}
	}

	public EnumList.DefTypeEnum getDocumentItemType(){
		if (document==null || document.getTask()==null){
			return null;
		}
		return document.getTask().getItemTypeDocument();
	}
	
	public Boolean isTaskSelected(EnumList.DefTypeEnum taskEnum) {
		if (this.document == null || document.getTask() == null) {
			return false;
		}
		boolean result = this.document.getTask().getType().getId().startsWith(taskEnum.name());
		return result;
	}

	public Boolean isTaskSelectedState(Integer trState) {
		if (this.document == null || this.document.getTask() == null) {
			return false;
		}
		boolean result = this.document.getTask().getType().getTrStateType().compareTo(trState)==0;
		return result;
	}
	
	public Boolean isFinIntegrated() {
		if (this.document == null || this.document.getTask() == null) {
			return false;
		}
		boolean result = document.getRefFinDocumentId()!=null;
		return result;
	}

	public void initNewDetail() {
		selectedDetail = new StkDetailEntity();
		selectedDetail.setDocument(document);
		selectedDetailServiceType = EnumList.DefTypeEnum.ITM_SR_ST;
	}

	public void selectedDetailServiceTypeChanged() {
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

	public TraTransactionHandler<StkDocumentEntity, StkDetailEntity> getTransactionHandler() {
		return transactionHandler;
	}

	public void setTransactionHandler(TraTransactionHandler<StkDocumentEntity, StkDetailEntity> transactionHandler) {
		this.transactionHandler = transactionHandler;
	}

	public DefTaskHandler getTaskRepository() {
		return taskRepository;
	}

	public void setTaskRepository(DefTaskHandler taskRepository) {
		this.taskRepository = taskRepository;
	}

	public TraDocumentEntity getDocument() {
		return document;
	}

	public void setDocument(StkDocumentEntity document) {
		this.document = document;
	}

	public List<DefTaskEntity> getStkTaskList() {
		return stkTaskList;
	}

	public void setStkTaskList(List<DefTaskEntity> stkTaskList) {
		this.stkTaskList = stkTaskList;
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

	public StkDetailEntity getSelectedDetail() {
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

	public EnumList.DefTypeEnum getSelectedDetailServiceType() {
		return selectedDetailServiceType;
	}

	public void setSelectedDetailServiceType(EnumList.DefTypeEnum selectedDetailServiceType) {
		this.selectedDetailServiceType = selectedDetailServiceType;
	}

	public EnumList.DefTypeEnum getSelectedTypeEnum() {
		return selectedTypeEnum;
	}

	public void setSelectedTypeEnum(EnumList.DefTypeEnum selectedTypeEnum) {
		this.selectedTypeEnum = selectedTypeEnum;
	}

}
