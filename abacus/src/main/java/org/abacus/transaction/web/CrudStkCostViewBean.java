package org.abacus.transaction.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.abacus.catering.core.handler.CatMealHandler;
import org.abacus.catering.shared.entity.CatMealFilterEntity;
import org.abacus.common.web.JsfMessageHelper;
import org.abacus.common.web.SessionInfoHelper;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.entity.DefItemEntity;
import org.abacus.organization.core.handler.DepartmentHandler;
import org.abacus.organization.shared.entity.DepartmentEntity;
import org.abacus.organization.shared.entity.FiscalPeriodEntity;
import org.abacus.organization.shared.entity.FiscalYearEntity;
import org.abacus.organization.shared.entity.OrganizationEntity;
import org.abacus.transaction.core.handler.TraIntegrationHandler;
import org.abacus.transaction.shared.entity.StkDocumentEntity;
import org.abacus.transaction.shared.holder.SalesDocumentHolder;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class CrudStkCostViewBean implements Serializable {


	@ManagedProperty(value = "#{catMealHandler}")
	private CatMealHandler catMealHandler;

	@ManagedProperty(value = "#{sessionInfoHelper}")
	private SessionInfoHelper sessionInfoHelper;

	@ManagedProperty(value = "#{jsfMessageHelper}")
	private JsfMessageHelper jsfMessageHelper;

	@ManagedProperty(value = "#{traIntegrationHandler}")
	private TraIntegrationHandler traIntegrationHandler;
	
	@ManagedProperty(value = "#{departmentHandler}")
	private DepartmentHandler departmentService;
	
	private FiscalYearEntity fiscalYear;
	private Date transactionDate;
	
	
	@PostConstruct
	public void init() {
		fiscalYear = sessionInfoHelper.currentFiscalYear();
	}

	public void createWB(){
		if (transactionDate==null){
			jsfMessageHelper.addError("Tarih Giriniz");
			return;
		}
		List<CatMealFilterEntity> catMealList = catMealHandler.getCatMealList(fiscalYear);
		if (catMealList.size()==0){
			jsfMessageHelper.addError("Oluşturulacak menü bulunamadı");
			return;
		}
		
		List<SalesDocumentHolder> holderList = new ArrayList<SalesDocumentHolder>();
		for (CatMealFilterEntity meal : catMealList) {
			holderList.add(new SalesDocumentHolder(meal.getMeal(), meal.getCountPrepare(), meal.getUnitPrice()));
		}
		String username = sessionInfoHelper.currentUserName();
		OrganizationEntity organization = sessionInfoHelper.currentOrganization();
		DefItemEntity customer = organization.getCustomer();
		FiscalPeriodEntity period = sessionInfoHelper.getFiscalPeriod(transactionDate);
		EnumList.OrgDepartmentGroupEnum depGroup = EnumList.OrgDepartmentGroupEnum.F;  
		DepartmentEntity department = departmentService.findUserDepartmentListOrgOnly(username, depGroup, period.getFiscalYear().getOrganization()).get(0);

		StkDocumentEntity salesStkDoc = traIntegrationHandler.createSalesDocument(holderList, customer, period, department, transactionDate);
		jsfMessageHelper.addInfo("createSuccessful", "Satış Fatura");
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

	public CatMealHandler getCatMealHandler() {
		return catMealHandler;
	}

	public void setCatMealHandler(CatMealHandler defUnitHandler) {
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
	
}
