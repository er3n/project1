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
import org.abacus.organization.shared.entity.FiscalPeriodEntity;
import org.abacus.transaction.core.handler.ReqConfirmationHandler;
import org.abacus.transaction.core.handler.TraTransactionHandler;
import org.abacus.transaction.shared.entity.ReqDetailEntity;
import org.abacus.transaction.shared.entity.ReqDocumentEntity;
import org.abacus.transaction.shared.entity.StkDocumentEntity;
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
import org.springframework.util.CollectionUtils;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CrudReqDocumentViewBean implements Serializable {

	@ManagedProperty(value = "#{jsfMessageHelper}")
	private JsfMessageHelper jsfMessageHelper;

	@ManagedProperty(value = "#{sessionInfoHelper}")
	private SessionInfoHelper sessionInfoHelper;

	@ManagedProperty(value = "#{jsfDialogHelper}")
	private JsfDialogHelper jsfDialogHelper;

	@ManagedProperty(value = "#{reqTransactionHandler}")
	private TraTransactionHandler<ReqDocumentEntity, ReqDetailEntity> transactionHandler;

	@ManagedProperty(value = "#{defTaskHandler}")
	private DefTaskHandler taskRepository;

	@ManagedProperty(value = "#{reqConfirmationHandler}")
	private ReqConfirmationHandler reqConfirmationHandler;

	private ReqDocumentEntity document;

	private List<DefTaskEntity> reqTaskList;

	private Boolean showDocument = true;

	private List<ReqDetailEntity> detailList;

	private ReqDetailEntity selectedDetail;

	private EnumList.DefTypeGroupEnum selectedGroupEnum;

	private EnumList.DefTypeEnum selectedTypeEnum;

	private Boolean approval;

	@PostConstruct
	private void init() {

		try {
			String approvalStr = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("approval");
			this.approval = Boolean.valueOf(approvalStr);
		} catch (Exception e) {
			this.approval = false;
		}

		try {
			String grp = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("grp");
			String typ = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("typ");
			selectedGroupEnum = EnumList.DefTypeGroupEnum.valueOf(grp.toUpperCase());
			if (typ != null) {
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
			this.findDocument(Long.valueOf(documentId));
			if (document == null) {
				jsfMessageHelper.addError("noDocumentFind", selectedGroupEnum.getDescription());
				this.showDocument = false;
			} else {
				selectedTypeEnum = document.getTypeEnum();
				this.initSelections();
			}
		}
	}

	public EnumList.OrgDepartmentGroupEnum reqDepartmentGroupEnum(){
		EnumList.OrgDepartmentGroupEnum ret =  selectedTypeEnum.equals(EnumList.DefTypeEnum.REQ_IO_P)?EnumList.OrgDepartmentGroupEnum.SP:EnumList.OrgDepartmentGroupEnum.S;
		return ret;
	}
	
	private void initSelections() {
		reqTaskList = taskRepository.getTaskList(sessionInfoHelper.currentOrganization(), selectedTypeEnum);
	}

	private void initNewDocument() {
		document = new ReqDocumentEntity();
	}

	public void saveDocument() {
		try {
			DocumentCreatedEvent<ReqDocumentEntity> documentCreatedEvent = transactionHandler.newDocument(new CreateDocumentEvent<ReqDocumentEntity>(document, sessionInfoHelper.currentUserName(), sessionInfoHelper.currentOrganization(), sessionInfoHelper.currentFiscalYear()));
			document = (ReqDocumentEntity) documentCreatedEvent.getDocument();
			this.findDocument(document.getId());
			jsfMessageHelper.addInfo("createSuccessful", "Fiş");
		} catch (AbcBusinessException e) {
			jsfMessageHelper.addError(e);
		}

	}

	public void updateDocument() {
		try {
			DocumentUpdatedEvent<ReqDocumentEntity> documentUpdatedEvent = transactionHandler.updateDocument(new UpdateDocumentEvent<ReqDocumentEntity>(document, sessionInfoHelper.currentUserName()));
			this.findDocument(document.getId());
			jsfMessageHelper.addInfo("updateSuccessful", "Fiş");
		} catch (AbcBusinessException e) {
			jsfMessageHelper.addError(e);
		}
	}

	public void saveDetail() {
		try {
			DetailCreatedEvent<ReqDetailEntity> event = transactionHandler.newDetail(new CreateDetailEvent<ReqDetailEntity>(selectedDetail, sessionInfoHelper.currentUserName()));
			this.findDocument(document.getId());
			selectedDetail = null;
			jsfMessageHelper.addInfo("createSuccessful", "Fiş Detay");
		} catch (AbcBusinessException e) {
			jsfMessageHelper.addError(e);
		}
	}

	private void findDocument(Long documentId) {
		TraDocumentSearchCriteria traDocumentSearchCriteria = new TraDocumentSearchCriteria(documentId);

		ReadDocumentEvent<ReqDocumentEntity> readDocumentEvent = transactionHandler.readDocumentList(new RequestReadDocumentEvent<ReqDocumentEntity>(traDocumentSearchCriteria, sessionInfoHelper.currentOrganization().getRootOrganization(), null));
		if (CollectionUtils.isEmpty(readDocumentEvent.getDocumentList())) {
			document = null;
		} else {
			document = readDocumentEvent.getDocumentList().get(0);
			ReadDetailEvent<ReqDetailEntity> readDetailEvent = transactionHandler.readDetailList(new RequestReadDetailEvent<ReqDetailEntity>(document.getId()));
			detailList = readDetailEvent.getDetails();
		}
	}

	public void updateDetailSelected(ReqDetailEntity detail) {
		this.selectedDetail = detail;
	}

	public void updateDetail() {
		try {
			DetailUpdatedEvent<ReqDetailEntity> detailUpdatedEvent = transactionHandler.updateDetail(new UpdateDetailEvent<ReqDetailEntity>(selectedDetail, sessionInfoHelper.currentUserName()));
			this.findDocument(document.getId());
			this.selectedDetail = null;
			jsfMessageHelper.addInfo("updateSuccessful", "Fiş Detay");
		} catch (AbcBusinessException e) {
			jsfMessageHelper.addError(e);
		}
	}

	public void deleteDetail(ReqDetailEntity detail) {
		try {
			DetailDeletedEvent<ReqDetailEntity> deleteDetailEvent = transactionHandler.deleteDetail(new DeleteDetailEvent<ReqDetailEntity>(detail));
			this.findDocument(document.getId());
			jsfMessageHelper.addInfo("deleteSuccessful", "Fiş Detay");
		} catch (AbcBusinessException e) {
			jsfMessageHelper.addError(e);
		}
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
		boolean result = this.document.getTask().getType().getTrStateType().compareTo(trState) == 0;
		return result;
	}

	public void initNewDetail() {
		selectedDetail = new ReqDetailEntity();
		selectedDetail.setDocument(document);
	}

	public void requestDocument() {
		try {
			this.document = reqConfirmationHandler.requestDocument(this.document, sessionInfoHelper.currentUserName());
			this.findDocument(document.getId());
			jsfMessageHelper.addInfo("operationSuccessful", "Sipariş girme");
		} catch (AbcBusinessException e) {
			jsfMessageHelper.addError(e);
		}
	}

	public void confirmDocument() {
		try {
			FiscalPeriodEntity period2 = sessionInfoHelper.getFiscalPeriod(this.document.getDocDate());
			StkDocumentEntity stkDocument = reqConfirmationHandler.confirmDocument(new ConfirmDocumentEvent(this.document, period2));
			this.findDocument(document.getId());
			jsfMessageHelper.addInfo("confirmedWithDocumentNo", stkDocument.getDocNo());
		} catch (AbcBusinessException e) {
			jsfMessageHelper.addError(e);
		}
	}

	public void cancelDocument() {
		try {
			this.document = reqConfirmationHandler.cancelDocument(this.document, sessionInfoHelper.currentUserName());
			this.findDocument(document.getId());
			jsfMessageHelper.addInfo("operationSuccessful", "Reddedme");
		} catch (AbcBusinessException e) {
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

	public TraTransactionHandler<ReqDocumentEntity, ReqDetailEntity> getTransactionHandler() {
		return transactionHandler;
	}

	public void setTransactionHandler(TraTransactionHandler<ReqDocumentEntity, ReqDetailEntity> transactionHandler) {
		this.transactionHandler = transactionHandler;
	}

	public DefTaskHandler getTaskRepository() {
		return taskRepository;
	}

	public void setTaskRepository(DefTaskHandler taskRepository) {
		this.taskRepository = taskRepository;
	}

	public ReqDocumentEntity getDocument() {
		return document;
	}

	public void setDocument(ReqDocumentEntity document) {
		this.document = document;
	}

	public List<DefTaskEntity> getReqTaskList() {
		return reqTaskList;
	}

	public void setReqTaskList(List<DefTaskEntity> reqTaskList) {
		this.reqTaskList = reqTaskList;
	}

	public Boolean getShowDocument() {
		return showDocument;
	}

	public void setShowDocument(Boolean showDocument) {
		this.showDocument = showDocument;
	}

	public List<ReqDetailEntity> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<ReqDetailEntity> detailList) {
		this.detailList = detailList;
	}

	public ReqDetailEntity getSelectedDetail() {
		return selectedDetail;
	}

	public void setSelectedDetail(ReqDetailEntity selectedDetail) {
		this.selectedDetail = selectedDetail;
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

	public Boolean getApproval() {
		return approval;
	}

	public void setApproval(Boolean approval) {
		this.approval = approval;
	}

	public ReqConfirmationHandler getReqConfirmationHandler() {
		return reqConfirmationHandler;
	}

	public void setReqConfirmationHandler(ReqConfirmationHandler reqConfirmationHandler) {
		this.reqConfirmationHandler = reqConfirmationHandler;
	}

}
