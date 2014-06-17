package org.abacus.report.web;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.abacus.common.shared.AbcBusinessException;
import org.abacus.common.web.JsfDialogHelper;
import org.abacus.common.web.JsfMessageHelper;
import org.abacus.common.web.SessionInfoHelper;
import org.abacus.definition.core.persistance.repository.DefTaskRepository;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.entity.DefTaskEntity;
import org.abacus.organization.shared.entity.FiscalYearEntity;
import org.abacus.organization.shared.entity.OrganizationEntity;
import org.abacus.report.core.handler.ReportHandler;
import org.abacus.transaction.core.handler.TraTransactionHandler;
import org.abacus.transaction.core.persistance.repository.StkDocumentRepository;
import org.abacus.transaction.shared.entity.StkDetailEntity;
import org.abacus.transaction.shared.entity.StkDocumentEntity;
import org.abacus.transaction.shared.entity.TraDocumentEntity;
import org.abacus.transaction.shared.event.CreateDetailEvent;
import org.abacus.transaction.shared.event.CreateDocumentEvent;
import org.abacus.transaction.shared.event.DetailCreatedEvent;
import org.abacus.transaction.shared.event.DocumentCreatedEvent;
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

	@ManagedProperty(value = "#{stkTransactionHandler}")
	private TraTransactionHandler transactionHandler;
	
	@ManagedProperty(value = "#{stkDocumentRepository}")
	private StkDocumentRepository documentRepository;
	
	@ManagedProperty(value = "#{defTaskRepository}")
	private DefTaskRepository taskRepository;

	@ManagedProperty(value = "#{reportHandler}")
	private ReportHandler reportHandler;

	private TraDocumentSearchCriteria documentSearchCriteria;
	private List<StkDetailEntity> searchResultList;
	private boolean hasFiscalYear;
	private List<DefTaskEntity> allTaskList;
	
	@PostConstruct
	private void init() {
		documentSearchCriteria = new TraDocumentSearchCriteria();
		this.hasFiscalYear = sessionInfoHelper.currentUser().getSelectedFiscalYear() != null;
		jsfMessageHelper.addWarn("noFiscalYearDefined");
		allTaskList = taskRepository.getTaskList(sessionInfoHelper.currentRootOrganizationId(), EnumList.DefTypeGroupEnum.STK.name());

	}

	public void searchResult() {
		searchResultList = reportHandler.getStkState(sessionInfoHelper.currentUser().getSelectedFiscalYear().getId());
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

	public boolean isHasFiscalYear() {
		return hasFiscalYear;
	}

	public void setHasFiscalYear(boolean hasFiscalYear) {
		this.hasFiscalYear = hasFiscalYear;
	}

	
	public void testCreateStkData() throws AbcBusinessException {
		System.out.println("createStkTestData");
		if (documentSearchCriteria.getDocTask()==null||
				documentSearchCriteria.getDetailCount()==null||
				documentSearchCriteria.getDetailDepartment()==null||
				documentSearchCriteria.getDetailItem()==null){
			System.out.println("createStkTestData Eksik Bilgi");
			jsfMessageHelper.addTest("createStkTestData Eksik Bilgi");
			return;
		}
		try{
			CreateDocumentEvent createDocumentEvent = testCreateDocumentEvent();
			DocumentCreatedEvent documentCreatedEvent = transactionHandler.newDocument(createDocumentEvent);
			
			TraDocumentEntity newTraDocument = documentCreatedEvent.getDocument();
			StkDocumentEntity newStkDocument = documentRepository.findWithFetch(newTraDocument.getId());
			
			CreateDetailEvent createDetailEvent = testCreateDetailEvent(newStkDocument);
			DetailCreatedEvent detailCreatedEvent = transactionHandler.newDetail(createDetailEvent);
			
			System.out.println("createStkTestData Islem Tamam");
			jsfMessageHelper.addTest("createStkTestData Islem Tamam");
			
		} catch (AbcBusinessException e){
			System.out.println("createStkTestData AbcBusinessException");
			jsfMessageHelper.addError(e);
		} catch (Exception e) {
			System.out.println("createStkTestData Exception");
			jsfMessageHelper.addTest("createStkTestData Exception");
		}

	}

	private CreateDocumentEvent testCreateDocumentEvent() {
		String user = sessionInfoHelper.currentUser().getUsername();
		OrganizationEntity organization = sessionInfoHelper.currentOrganization();
		FiscalYearEntity fiscalYear = sessionInfoHelper.currentUser().getSelectedFiscalYear();
		
		StkDocumentEntity doc = new StkDocumentEntity();

		doc.setDocDate(Calendar.getInstance().getTime());
		doc.setDocNo("test:"+doc.getDocDate().getTime());
		doc.setDocNote("test:"+doc.getDocDate().getTime());
		doc.setTask(documentSearchCriteria.getDocTask());
		doc.setTypeEnum(documentSearchCriteria.getDocTask().getType().getTypeEnum());
		doc.setOrganization(organization);
		
		CreateDocumentEvent event = new CreateDocumentEvent(doc, user, organization.getId(), fiscalYear.getId());		
		return event;
	}
	
	public CreateDetailEvent testCreateDetailEvent(TraDocumentEntity document) {
		String user = sessionInfoHelper.currentUser().getUsername();

		StkDetailEntity dtl = new StkDetailEntity();
		
		dtl.setDocument(document);
		dtl.setBaseDetailAmount(BigDecimal.ONE);
		dtl.setDepartment(documentSearchCriteria.getDetailDepartment());
		dtl.setItem(documentSearchCriteria.getDetailItem());
		dtl.setLotDetailDate(document.getDocDate());
		dtl.setItemDetailCount(documentSearchCriteria.getDetailCount());
		dtl.setItemUnit(documentSearchCriteria.getDetailItem().getItemUnitSet().iterator().next().getUnitCode());
		dtl.setDetNote("test:"+document.getId());
		dtl.setBatchDetailNo("test:"+document.getId());

		CreateDetailEvent event = new CreateDetailEvent(dtl, user);
		return event;
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

	public StkDocumentRepository getDocumentRepository() {
		return documentRepository;
	}

	public void setDocumentRepository(StkDocumentRepository documentRepository) {
		this.documentRepository = documentRepository;
	}

	public ReportHandler getReportHandler() {
		return reportHandler;
	}

	public void setReportHandler(ReportHandler reportHandler) {
		this.reportHandler = reportHandler;
	}

	public List<StkDetailEntity> getSearchResultList() {
		return searchResultList;
	}

	public void setSearchResultList(List<StkDetailEntity> searchResultList) {
		this.searchResultList = searchResultList;
	}

}
