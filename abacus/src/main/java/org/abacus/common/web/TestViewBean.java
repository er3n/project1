package org.abacus.common.web;

import java.io.Serializable;
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
import org.abacus.transaction.core.handler.TraTransactionHandler;
import org.abacus.transaction.core.persistance.repository.StkDocumentRepository;
import org.abacus.transaction.shared.entity.FinDocumentEntity;
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

	private TestCriteria testCriteria;
	private Boolean showDocument = true; 
	private List<DefTaskEntity> stkTaskList;

	private FinDocumentEntity documentFinEntity;

	private StkDocumentEntity documentStkEntity;

	@PostConstruct
	private void init() {
		testCriteria = new TestCriteria();
		testCriteria.setOrganization(sessionInfoHelper.currentOrganization());
		testCriteria.setFiscalYear(sessionInfoHelper.currentUser().getSelectedFiscalYear());
		this.showDocument = sessionInfoHelper.currentUser().getSelectedFiscalYear() != null;
//		jsfMessageHelper.addWarn("noFiscalYearDefined");
		stkTaskList = taskRepository.getTaskList(sessionInfoHelper.currentRootOrganizationId(), EnumList.DefTypeEnum.STK_IO.name());
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
		jsfMessageHelper.addInfo(msg);
    }
	
	public void testCreateStkData() throws AbcBusinessException {
		System.out.println("createStkTestData");
		try{
			CreateDocumentEvent<StkDocumentEntity> createDocumentEvent = testCreateDocumentEvent();
			DocumentCreatedEvent<StkDocumentEntity> documentCreatedEvent = transactionHandler.newDocument(createDocumentEvent);
			
			TraDocumentEntity newTraDocument = documentCreatedEvent.getDocument();
			StkDocumentEntity newStkDocument = documentRepository.findWithFetch(newTraDocument.getId());
			
			CreateDetailEvent<StkDetailEntity> createDetailEvent = testCreateDetailEvent(newStkDocument);
			DetailCreatedEvent<StkDetailEntity> detailCreatedEvent = transactionHandler.newDetail(createDetailEvent);
			
			System.out.println("createStkTestData Islem Tamam");
			jsfMessageHelper.addInfo("createStkTestData Islem Tamam");
			
		} catch (AbcBusinessException e){
			System.out.println("createStkTestData AbcBusinessException");
			jsfMessageHelper.addError(e);
		} catch (Exception e) {
			System.out.println("createStkTestData Exception");
			jsfMessageHelper.addInfo("createStkTestData Exception");
		}

	}

	private CreateDocumentEvent<StkDocumentEntity> testCreateDocumentEvent() {
		String user = sessionInfoHelper.currentUser().getUsername();
		OrganizationEntity organization = sessionInfoHelper.currentOrganization();
		FiscalYearEntity fiscalYear = sessionInfoHelper.currentUser().getSelectedFiscalYear();
		
		StkDocumentEntity doc = new StkDocumentEntity();

		doc.setDocDate(Calendar.getInstance().getTime());
		doc.setDocNo(String.valueOf(doc.getDocDate().getTime()));
		doc.setDocNote("doc note:"+doc.getDocDate().getTime());
		doc.setTask(testCriteria.getDocTask());
		doc.setTypeEnum(testCriteria.getDocTask().getType().getTypeEnum());
		doc.setOrganization(organization);
		
		CreateDocumentEvent<StkDocumentEntity> event = new CreateDocumentEvent<StkDocumentEntity>(doc, user, organization.getId(), fiscalYear.getId());		
		return event;
	}
	
	public CreateDetailEvent<StkDetailEntity> testCreateDetailEvent(TraDocumentEntity document) {
		String user = sessionInfoHelper.currentUser().getUsername();

		StkDetailEntity dtl = new StkDetailEntity();
		
		dtl.setDocument(document);
		dtl.setDepartment(testCriteria.getDetailDepartment());
		dtl.setItem(testCriteria.getDetailItem());
		dtl.setDueDate(document.getDocDate());
		dtl.setItemDetailCount(testCriteria.getDetailCount());
		dtl.setBaseDetailAmount(testCriteria.getDetailCount());
		dtl.setItemUnit(testCriteria.getUnitCode());
		dtl.setDetNote("dtl note:"+document.getId());
		dtl.setBatchDetailNo("batch:"+document.getId());
		dtl.setDepartmentOpp(testCriteria.getDetailOppDepartment());
		
		CreateDetailEvent<StkDetailEntity> event = new CreateDetailEvent<StkDetailEntity>(dtl, user);
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

	public TestCriteria getTestCriteria() {
		return testCriteria;
	}

	public void setTestCriteria(TestCriteria testCriteria) {
		this.testCriteria = testCriteria;
	}

	public FinDocumentEntity getDocumentFinEntity() {
		return documentFinEntity;
	}

	public void setDocumentFinEntity(FinDocumentEntity documentFinEntity) {
		this.documentFinEntity = documentFinEntity;
	}

	public StkDocumentEntity getDocumentStkEntity() {
		return documentStkEntity;
	}

	public void setDocumentStkEntity(StkDocumentEntity documentStkEntity) {
		this.documentStkEntity = documentStkEntity;
	}


}



