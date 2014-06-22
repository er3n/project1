package org.abacus.common.web;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.abacus.common.shared.AbcBusinessException;
import org.abacus.definition.core.persistance.repository.DefTaskRepository;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.entity.DefTaskEntity;
import org.abacus.organization.shared.entity.FiscalYearEntity;
import org.abacus.organization.shared.entity.OrganizationEntity;
import org.abacus.report.shared.holder.ReportSearchCriteria;
import org.abacus.transaction.core.handler.TraTransactionHandler;
import org.abacus.transaction.core.persistance.repository.StkDocumentRepository;
import org.abacus.transaction.shared.entity.StkDetailEntity;
import org.abacus.transaction.shared.entity.StkDocumentEntity;
import org.abacus.transaction.shared.entity.TraDocumentEntity;
import org.abacus.transaction.shared.event.CreateDetailEvent;
import org.abacus.transaction.shared.event.CreateDocumentEvent;
import org.abacus.transaction.shared.event.DetailCreatedEvent;
import org.abacus.transaction.shared.event.DocumentCreatedEvent;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class TestViewBean implements Serializable {

	@ManagedProperty(value = "#{jsfMessageHelper}")
	private JsfMessageHelper jsfMessageHelper;

	@ManagedProperty(value = "#{sessionInfoHelper}")
	private SessionInfoHelper sessionInfoHelper;

	@ManagedProperty(value = "#{jsfDialogHelper}")
	private JsfDialogHelper jsfDialogHelper;

	@ManagedProperty(value = "#{stkTransactionHandler}")
	private TraTransactionHandler<StkDocumentEntity,StkDetailEntity> transactionHandler;
	
	@ManagedProperty(value = "#{stkDocumentRepository}")
	private StkDocumentRepository documentRepository;
	
	@ManagedProperty(value = "#{defTaskRepository}")
	private DefTaskRepository taskRepository;

	private ReportSearchCriteria reportSearchCriteria;
	private Boolean showDocument = true; 
	private List<DefTaskEntity> stkTaskList;
	
	@PostConstruct
	private void init() {
		reportSearchCriteria = new ReportSearchCriteria();
		reportSearchCriteria.setOrganization(sessionInfoHelper.currentOrganization());
		reportSearchCriteria.setFiscalYear(sessionInfoHelper.currentUser().getSelectedFiscalYear());
		this.showDocument = sessionInfoHelper.currentUser().getSelectedFiscalYear() != null;
//		jsfMessageHelper.addWarn("noFiscalYearDefined");
		stkTaskList = taskRepository.getTaskList(sessionInfoHelper.currentRootOrganizationId(), EnumList.DefTypeGroupEnum.STK.name());
	}

	public void openTestDocDialog() {
		jsfDialogHelper.openTestDocDialog();
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

	public TraTransactionHandler<StkDocumentEntity,StkDetailEntity> getTransactionHandler() {
		return transactionHandler;
	}

	public void setTransactionHandler(TraTransactionHandler<StkDocumentEntity,StkDetailEntity> transactionHandler) {
		this.transactionHandler = transactionHandler;
	}

	public Boolean getShowDocument() {
		return showDocument;
	}

	public void setShowDocument(Boolean showDocument) {
		this.showDocument = showDocument;
	}

	public void showTestMessage(String msg) {
		jsfMessageHelper.addTest(msg);
    }
	
	public void testCreateStkData() throws AbcBusinessException {
		System.out.println("createStkTestData");
		if (reportSearchCriteria.getDocTask()==null||
				reportSearchCriteria.getDetailCount()==null||
				reportSearchCriteria.getDetailDepartment()==null||
				reportSearchCriteria.getDetailItem()==null){
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
		doc.setDocNo(String.valueOf(doc.getDocDate().getTime()));
		doc.setDocNote("doc note:"+doc.getDocDate().getTime());
		doc.setTask(reportSearchCriteria.getDocTask());
		doc.setTypeEnum(reportSearchCriteria.getDocTask().getType().getTypeEnum());
		doc.setOrganization(organization);
		
		CreateDocumentEvent event = new CreateDocumentEvent(doc, user, organization.getId(), fiscalYear.getId());		
		return event;
	}
	
	public CreateDetailEvent testCreateDetailEvent(TraDocumentEntity document) {
		String user = sessionInfoHelper.currentUser().getUsername();

		StkDetailEntity dtl = new StkDetailEntity();
		
		dtl.setDocument(document);
		dtl.setBaseDetailAmount(BigDecimal.ONE);
		dtl.setDepartment(reportSearchCriteria.getDetailDepartment());
		dtl.setItem(reportSearchCriteria.getDetailItem());
		dtl.setLotDetailDate(document.getDocDate());
		dtl.setItemDetailCount(reportSearchCriteria.getDetailCount());
		dtl.setItemUnit(reportSearchCriteria.getDetailItem().getItemUnitSet().iterator().next().getUnitCode());
		dtl.setDetNote("dtl note:"+document.getId());
		dtl.setBatchDetailNo("batch:"+document.getId());

		CreateDetailEvent event = new CreateDetailEvent(dtl, user);
		return event;
	}
	
	
	public List<DefTaskEntity> getStkTaskList() {
		return stkTaskList;
	}

	public void setStkTaskList(List<DefTaskEntity> stkTaskList) {
		this.stkTaskList = stkTaskList;
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

	public ReportSearchCriteria getReportSearchCriteria() {
		return reportSearchCriteria;
	}

	public void setReportSearchCriteria(ReportSearchCriteria reportSearchCriteria) {
		this.reportSearchCriteria = reportSearchCriteria;
	}

}



