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
import org.abacus.transaction.core.handler.TraIntegrationHandler;
import org.abacus.transaction.core.handler.TraTransactionHandler;
import org.abacus.transaction.shared.UnableToDeleteDetailException;
import org.abacus.transaction.shared.UnableToUpdateDocumentExpception;
import org.abacus.transaction.shared.entity.StkDetailEntity;
import org.abacus.transaction.shared.entity.StkDocumentEntity;
import org.abacus.transaction.shared.event.CancelDocumentEvent;
import org.abacus.transaction.shared.event.DeleteDocumentEvent;
import org.abacus.transaction.shared.event.ReadDocumentEvent;
import org.abacus.transaction.shared.event.RequestReadDocumentEvent;
import org.abacus.transaction.shared.holder.TraDocumentSearchCriteria;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class FindStkDocumentViewBean implements Serializable {

	@ManagedProperty(value = "#{jsfMessageHelper}")
	private JsfMessageHelper jsfMessageHelper;

	@ManagedProperty(value = "#{sessionInfoHelper}")
	private SessionInfoHelper sessionInfoHelper;

	@ManagedProperty(value = "#{jsfDialogHelper}")
	private JsfDialogHelper jsfDialogHelper;

	@ManagedProperty(value = "#{defTaskHandler}")
	private DefTaskHandler taskRepository;
	
	@ManagedProperty(value = "#{stkTransactionHandler}")
	private TraTransactionHandler<StkDocumentEntity, StkDetailEntity> transactionHandler;
	
	@ManagedProperty(value = "#{traIntegrationHandler}")
	private TraIntegrationHandler traIntegrationHandler;

	private TraDocumentSearchCriteria documentSearchCriteria;
	private List<DefTaskEntity> stkTaskList;
	
	private List<StkDocumentEntity> documentSearchResultList;
	
	private EnumList.DefTypeGroupEnum selectedGroupEnum;
	private EnumList.DefTypeEnum selectedTypeEnum;
	
	private Boolean showDocument = true;

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
		documentSearchCriteria = new TraDocumentSearchCriteria();
		documentSearchCriteria.setDocType(selectedTypeEnum);
		this.initSelections();
	}
	
	private void initSelections() {
		stkTaskList = taskRepository.getTaskList(sessionInfoHelper.currentOrganization(), selectedTypeEnum);
	}

	public Boolean isTaskSelected(EnumList.DefTypeEnum taskEnum) {
		boolean result = selectedTypeEnum.name().startsWith(taskEnum.name());
		return result;
	}

	public Boolean isTaskSelectedState(Integer trState) {
		boolean result = selectedTypeEnum.getState().compareTo(trState)==0;
		return result;
	}

	public Boolean isFinIntegrated(StkDocumentEntity document) {
		boolean result = (document!=null && document.getRefFinDocumentId()!=null);
		return result;
	}

	public Boolean canFinIntegration(StkDocumentEntity document) {
		String type = document.getTypeEnum().name();
		boolean result = (type.startsWith(EnumList.DefTypeEnum.STK_WB.name()) || type.startsWith(EnumList.DefTypeEnum.STK_IO_O.name()));
		return result;
	}

	public void findStkDocument() {
		ReadDocumentEvent<StkDocumentEntity> readDocumentEvent = transactionHandler.readDocumentList(new RequestReadDocumentEvent<StkDocumentEntity>(documentSearchCriteria, sessionInfoHelper.currentOrganization(), sessionInfoHelper.currentFiscalYear()));
		documentSearchResultList = readDocumentEvent.getDocumentList();
	}

	public void cancelDocument(StkDocumentEntity document) {
		try {
			transactionHandler.cancelDocument(new CancelDocumentEvent<StkDocumentEntity>(document, sessionInfoHelper.currentUserName()));
			this.findStkDocument();
		} catch (UnableToUpdateDocumentExpception e) {
			jsfMessageHelper.addError(e);
		}
	}

	public void deleteDocument(StkDocumentEntity document) {
		try {
			transactionHandler.deleteDocument(new DeleteDocumentEvent<StkDocumentEntity>(document));
			this.findStkDocument();
		} catch (UnableToDeleteDetailException e) {
			jsfMessageHelper.addError(e);
		}
	}
	
	public void createFinFromStk(StkDocumentEntity document) {
		if (document.getRefFinDocumentId()!=null){
			jsfMessageHelper.addInfo("finDocumentAlreadyExists");
			return;
		} 
		try {
			traIntegrationHandler.createFinFromStk(document.getId());
			jsfMessageHelper.addInfo("createFinDocument");
			this.findStkDocument();
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

	public TraDocumentSearchCriteria getDocumentSearchCriteria() {
		return documentSearchCriteria;
	}

	public void setDocumentSearchCriteria(TraDocumentSearchCriteria documentSearchCriteria) {
		this.documentSearchCriteria = documentSearchCriteria;
	}

	public TraTransactionHandler<StkDocumentEntity, StkDetailEntity> getTransactionHandler() {
		return transactionHandler;
	}

	public void setTransactionHandler(TraTransactionHandler<StkDocumentEntity, StkDetailEntity> transactionHandler) {
		this.transactionHandler = transactionHandler;
	}

	public List<StkDocumentEntity> getDocumentSearchResultList() {
		return documentSearchResultList;
	}

	public void setDocumentSearchResultList(List<StkDocumentEntity> documentSearchResultList) {
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

	public TraIntegrationHandler getTraIntegrationHandler() {
		return traIntegrationHandler;
	}

	public void setTraIntegrationHandler(TraIntegrationHandler traIntegrationHandler) {
		this.traIntegrationHandler = traIntegrationHandler;
	}

	public EnumList.DefTypeEnum getSelectedTypeEnum() {
		return selectedTypeEnum;
	}

	public void setSelectedTypeEnum(EnumList.DefTypeEnum selectedTypeEnum) {
		this.selectedTypeEnum = selectedTypeEnum;
	}

	public List<DefTaskEntity> getStkTaskList() {
		return stkTaskList;
	}

	public void setStkTaskList(List<DefTaskEntity> stkTaskList) {
		this.stkTaskList = stkTaskList;
	}

	public DefTaskHandler getTaskRepository() {
		return taskRepository;
	}

	public void setTaskRepository(DefTaskHandler taskRepository) {
		this.taskRepository = taskRepository;
	}

}
