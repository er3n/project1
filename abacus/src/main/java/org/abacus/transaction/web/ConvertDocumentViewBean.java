package org.abacus.transaction.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.persistence.EnumType;

import org.abacus.budget.core.handler.BudgetHandler;
import org.abacus.catering.core.handler.CatMenuHandler;
import org.abacus.catering.shared.entity.CatMenuEntity;
import org.abacus.common.web.JsfMessageHelper;
import org.abacus.common.web.SessionInfoHelper;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.entity.DefItemEntity;
import org.abacus.organization.core.handler.DepartmentHandler;
import org.abacus.organization.core.handler.FiscalHandler;
import org.abacus.organization.shared.entity.DepartmentEntity;
import org.abacus.organization.shared.entity.FiscalPeriodEntity;
import org.abacus.organization.shared.entity.FiscalYearEntity;
import org.abacus.organization.shared.entity.OrganizationEntity;
import org.abacus.transaction.core.handler.TraIntegrationHandler;
import org.abacus.transaction.core.handler.TraTransactionHandler;
import org.abacus.transaction.shared.entity.FinDocumentEntity;
import org.abacus.transaction.shared.entity.StkDetailEntity;
import org.abacus.transaction.shared.entity.StkDocumentEntity;
import org.abacus.transaction.shared.event.ReadDocumentEvent;
import org.abacus.transaction.shared.event.RequestReadDocumentEvent;
import org.abacus.transaction.shared.holder.TraDocumentSearchCriteria;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class ConvertDocumentViewBean implements Serializable {


	@ManagedProperty(value = "#{catMenuHandler}")
	private CatMenuHandler catMealHandler;

	@ManagedProperty(value = "#{sessionInfoHelper}")
	private SessionInfoHelper sessionInfoHelper;

	@ManagedProperty(value = "#{jsfMessageHelper}")
	private JsfMessageHelper jsfMessageHelper;

	@ManagedProperty(value = "#{traIntegrationHandler}")
	private TraIntegrationHandler traIntegrationHandler;
	
	@ManagedProperty(value = "#{departmentHandler}")
	private DepartmentHandler departmentService;

	@ManagedProperty(value = "#{budgetHandler}")
	private BudgetHandler budgetHandler;

	@ManagedProperty(value = "#{stkTransactionHandler}")
	private TraTransactionHandler<StkDocumentEntity, StkDetailEntity> transactionHandler;
	
	@ManagedProperty(value = "#{fiscalHandler}")
	private FiscalHandler fiscalService;
	
	private FiscalYearEntity fiscalYear;
	private Date transactionDate = new Date();
	
	@PostConstruct
	public void init() {
		fiscalYear = sessionInfoHelper.currentFiscalYear();
	}

	public void createFinSale(){
		OrganizationEntity organization = sessionInfoHelper.currentOrganization();
		DefItemEntity customer = organization.getCustomer();
		if (customer == null){
			jsfMessageHelper.addError(organization.getName()+" için müşteri tanımı yapılmamıştır.");
			return;
		}
		
		List<CatMenuEntity> catMenuList = catMealHandler.getMenuListForFinace(fiscalYear.getId(), transactionDate);
		if (catMenuList.size()==0){
			jsfMessageHelper.addError("Hakedişi Oluşturulacak Menü bulunamadı");
			return;
		}
		
		Map<Date, List<CatMenuEntity>> menuMap = new HashMap<Date, List<CatMenuEntity>>();
		for (CatMenuEntity menu : catMenuList) {
			if (menuMap.containsKey(menu.getMenuDate())){
				List<CatMenuEntity> menuList = menuMap.get(menu.getMenuDate());
				menuList.add(menu);
			} else {
				List<CatMenuEntity> menuList = new ArrayList<CatMenuEntity>();
				menuList.add(menu);
				menuMap.put(menu.getMenuDate(), menuList);
			}
		}
		
		String username = sessionInfoHelper.currentUserName();
		EnumList.OrgDepartmentGroupEnum depGroup = EnumList.OrgDepartmentGroupEnum.F;  
		DepartmentEntity department = departmentService.findUserDepartmentListOrgOnly(username, depGroup, fiscalYear.getOrganization()).get(0);

		for (Date menuDate : menuMap.keySet()) {
			List<CatMenuEntity> menuList = menuMap.get(menuDate);
			FiscalPeriodEntity period = sessionInfoHelper.getFiscalPeriod(menuDate);
			StkDocumentEntity salesStkDoc = traIntegrationHandler.createSalesDocument(menuList, customer, period, department, menuDate);
		}
		jsfMessageHelper.addInfo("createSuccessful", "Satış Fatura");
	}

	public void convertStkOutToFinCost(){
		//TODO :Projeler arasi WB_T ler ne olacak ? Satis Gibi degerlendirilmeli: Faturası ve Maliyeti olusur- Rev==Exp
		
		//Maliyeti Entegre Olmamis Tum Cikis Fisleri 
		TraDocumentSearchCriteria documentSearchCriteria = new TraDocumentSearchCriteria();
		documentSearchCriteria.setDocType(EnumList.DefTypeEnum.STK_IO_O);
		documentSearchCriteria.setIsIntegrated("0");
		documentSearchCriteria.setDocEndDate(transactionDate);
		ReadDocumentEvent<StkDocumentEntity> readDocumentEvent = transactionHandler.readDocumentList(new RequestReadDocumentEvent<StkDocumentEntity>(documentSearchCriteria, sessionInfoHelper.currentOrganization(), sessionInfoHelper.currentFiscalYear()));
		List<StkDocumentEntity> documentSearchResultList_Out = readDocumentEvent.getDocumentList();

		
		//TODO :Maliyeti Entegre Olmamis Stok Satis Fisleri
		//setRefFinDocumentId fatura entegrasyonu icin kullaniliyor, yeni entegrasyon alani gerekir!
//		documentSearchCriteria = new TraDocumentSearchCriteria();
//		documentSearchCriteria.setDocType(EnumList.DefTypeEnum.STK_WB_O);
//		documentSearchCriteria.setIsIntegrated("0");
//		documentSearchCriteria.setDocEndDate(transactionDate);
//		readDocumentEvent = transactionHandler.readDocumentList(new RequestReadDocumentEvent<StkDocumentEntity>(documentSearchCriteria, sessionInfoHelper.currentOrganization(), sessionInfoHelper.currentFiscalYear()));
//		List<StkDocumentEntity> documentSearchResultList_Sls = readDocumentEvent.getDocumentList();

		List<StkDocumentEntity> fullSearchResultList = new ArrayList<StkDocumentEntity>();
		fullSearchResultList.addAll(documentSearchResultList_Out);
//		fullSearchResultList.addAll(documentSearchResultList_Sls); 
		
		if (fullSearchResultList.size()==0){
			jsfMessageHelper.addError("Maliyeti Oluşturulacak Stok Çıkışı bulunamadı");
			return;
		}
		for (StkDocumentEntity stkDoc : fullSearchResultList) {
			FinDocumentEntity costFinDoc = traIntegrationHandler.createFinFromStk(stkDoc.getId());
		}
		jsfMessageHelper.addInfo("createSuccessful", "Stok Maliyet Fişleri");
	}

	public void convertStkWBToFinBS(){
		//TODO :Projeler arasi WB_T lerin Stok Satis Fisleri farkli degerlendirilmeli
		
		//Tarih Bos Ise Entegre Olmamis Tum Cikis Fisleri 
		TraDocumentSearchCriteria documentSearchCriteria = new TraDocumentSearchCriteria();
		documentSearchCriteria.setDocType(EnumList.DefTypeEnum.STK_WB);
		documentSearchCriteria.setIsIntegrated("0");
		documentSearchCriteria.setDocEndDate(transactionDate);
		ReadDocumentEvent<StkDocumentEntity> readDocumentEvent = transactionHandler.readDocumentList(new RequestReadDocumentEvent<StkDocumentEntity>(documentSearchCriteria, sessionInfoHelper.currentOrganization(), sessionInfoHelper.currentFiscalYear()));
		List<StkDocumentEntity> documentSearchResultList = readDocumentEvent.getDocumentList();
		if (documentSearchResultList.size()==0){
			jsfMessageHelper.addError("Faturası Oluşturulacak İrsaliye bulunamadı");
			return;
		}		for (StkDocumentEntity stkDoc : documentSearchResultList) {
			FinDocumentEntity costFinDoc = traIntegrationHandler.createFinFromStk(stkDoc.getId());
		}
		jsfMessageHelper.addInfo("createSuccessful", "Stok Maliyet Fişleri");
	}

	public void convertBudget(){
		List<FiscalPeriodEntity> periodList = fiscalService.findFiscalPeriodList(fiscalYear);
		for (FiscalPeriodEntity per : periodList) {
			budgetHandler.convertBudget(per);
		}
		jsfMessageHelper.addInfo("createSuccessful", "Bütçe");
	}
		
	public SessionInfoHelper getSessionInfoHelper() {
		return sessionInfoHelper;
	}

	public void setSessionInfoHelper(SessionInfoHelper sessionInfoHelper) {
		this.sessionInfoHelper = sessionInfoHelper;
	}

	public JsfMessageHelper getJsfMessageHelper() {
		return jsfMessageHelper;
	}

	public void setJsfMessageHelper(JsfMessageHelper jsfMessageHelper) {
		this.jsfMessageHelper = jsfMessageHelper;
	}

	public CatMenuHandler getCatMealHandler() {
		return catMealHandler;
	}

	public void setCatMealHandler(CatMenuHandler defUnitHandler) {
		this.catMealHandler = defUnitHandler;
	}

	public TraIntegrationHandler getTraIntegrationHandler() {
		return traIntegrationHandler;
	}

	public void setTraIntegrationHandler(TraIntegrationHandler traIntegrationHandler) {
		this.traIntegrationHandler = traIntegrationHandler;
	}

	public DepartmentHandler getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(DepartmentHandler departmentService) {
		this.departmentService = departmentService;
	}

	public FiscalYearEntity getFiscalYear() {
		return fiscalYear;
	}

	public void setFiscalYear(FiscalYearEntity fiscalYear) {
		this.fiscalYear = fiscalYear;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public TraTransactionHandler<StkDocumentEntity, StkDetailEntity> getTransactionHandler() {
		return transactionHandler;
	}

	public void setTransactionHandler(
			TraTransactionHandler<StkDocumentEntity, StkDetailEntity> transactionHandler) {
		this.transactionHandler = transactionHandler;
	}

	/**
	 * @return the budgetHandler
	 */
	public BudgetHandler getBudgetHandler() {
		return budgetHandler;
	}

	/**
	 * @param budgetHandler the budgetHandler to set
	 */
	public void setBudgetHandler(BudgetHandler budgetHandler) {
		this.budgetHandler = budgetHandler;
	}

	/**
	 * @return the fiscalService
	 */
	public FiscalHandler getFiscalService() {
		return fiscalService;
	}

	/**
	 * @param fiscalService the fiscalService to set
	 */
	public void setFiscalService(FiscalHandler fiscalService) {
		this.fiscalService = fiscalService;
	}
	
}
