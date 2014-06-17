package org.abacus.report.web;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.abacus.common.web.JsfDialogHelper;
import org.abacus.common.web.JsfMessageHelper;
import org.abacus.common.web.SessionInfoHelper;
import org.abacus.definition.core.persistance.repository.DefTaskRepository;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.entity.DefItemEntity;
import org.abacus.definition.shared.entity.DefTaskEntity;
import org.abacus.organization.shared.entity.DepartmentEntity;
import org.abacus.transaction.core.handler.TraTransactionHandler;
import org.abacus.transaction.shared.UnableToCreateDetailException;
import org.abacus.transaction.shared.entity.TraDocumentEntity;
import org.abacus.transaction.shared.event.ReadDocumentEvent;
import org.abacus.transaction.shared.event.RequestReadDocumentEvent;
import org.abacus.transaction.shared.holder.TraDocumentSearchCriteria;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class QueryStkStateViewBean implements Serializable {

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

	private boolean hasFiscalYear;
	private List<DefTaskEntity> allTaskList;
	
	@ManagedProperty(value = "#{defTaskRepository}")
	private DefTaskRepository taskRepository;
	
	@PostConstruct
	private void init() {
		documentSearchCriteria = new TraDocumentSearchCriteria();
		this.hasFiscalYear = sessionInfoHelper.currentUser().getSelectedFiscalYear() != null;
		jsfMessageHelper.addWarn("noFiscalYearDefined");
		allTaskList = taskRepository.getTaskList(sessionInfoHelper.currentRootOrganizationId(), EnumList.DefTypeGroupEnum.STK.name());

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

	public boolean isHasFiscalYear() {
		return hasFiscalYear;
	}

	public void setHasFiscalYear(boolean hasFiscalYear) {
		this.hasFiscalYear = hasFiscalYear;
	}

	
	public void createStkTestData() throws UnableToCreateDetailException{
		System.out.println("createStkTestData");
		if (documentSearchCriteria.getDocTask()==null||
				documentSearchCriteria.getDetailCount()==null||
				documentSearchCriteria.getDetailDepartment()==null||
				documentSearchCriteria.getDetailItem()==null){
			jsfMessageHelper.addTest("createStkTestData Eksik Bilgi");
			System.out.println("createStkTestData Eksik Bilgi");
			return;
		}
		jsfMessageHelper.addTest("createStkTestData Islem Tamam");
		System.out.println("createStkTestData Islem Tamam");

		
//		//INPUT
//		TraDocumentEntity inDocument = this.newStkTransaction(EnumList.DefTypeEnum.STK_IO_I).getDocument();
//		inDocument = documentRepository.findWithFetch(inDocument.getId());
//
//		for (int i = 0; i < 3; i++) {
//			CreateDetailEvent createDetailEventIn = transactionFixture.newDetail(inDocument, user, new BigDecimal(3000));
//			DetailCreatedEvent eventIn = stkTransaction.newDetail(createDetailEventIn);
//		}
//		
//		//TRANSFER
//		TraDocumentEntity transferDocument = this.newStkTransaction(EnumList.DefTypeEnum.STK_TT_T).getDocument();
//		transferDocument = documentRepository.findWithFetch(transferDocument.getId());
//		
//		CreateDetailEvent createDetailEventTransfer = transactionFixture.newTransfer(transferDocument, user, new BigDecimal(15000));
//		
//		stkTransaction.newDetail(createDetailEventTransfer);
//		
//		System.out.println("Bitti");
		
	}

	public List<DefTaskEntity> getAllTaskList() {
		return allTaskList;
	}

	public void setAllTaskList(List<DefTaskEntity> allTaskList) {
		this.allTaskList = allTaskList;
	}

	public DefTaskRepository getTaskRepository() {
		return taskRepository;
	}

	public void setTaskRepository(DefTaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}
	
}
