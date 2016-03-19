package org.abacus.organization.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.abacus.common.web.JsfMessageHelper;
import org.abacus.common.web.SessionInfoHelper;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.organization.core.handler.FiscalHandler;
import org.abacus.organization.shared.entity.FiscalPeriodEntity;
import org.abacus.organization.shared.entity.FiscalYearEntity;
import org.abacus.organization.shared.entity.OrganizationEntity;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class OrgFiscalViewBean implements Serializable {

	@ManagedProperty(value = "#{fiscalHandler}")
	private FiscalHandler fiscalService;

	private OrganizationEntity selOrganization;

	private FiscalYearEntity selFiscalYear;
	private List<FiscalYearEntity> fiscalYearList;

	private FiscalPeriodEntity selFiscalPeriod;
	private List<FiscalPeriodEntity> fiscalPeriodList;

	@ManagedProperty(value = "#{jsfMessageHelper}")
	private JsfMessageHelper jsfMessageHelper;

	@ManagedProperty(value = "#{sessionInfoHelper}")
	private SessionInfoHelper sessionInfoHelper;

	
	@PostConstruct
	public void init() {
		createFiscalYear();
		createFiscalPeriod();
	}

	public void setSelOrganization(OrganizationEntity org) {
		this.selOrganization = org;
		init();
		fiscalYearList = fiscalService.findFiscalYearList(org);
		fiscalPeriodList = new ArrayList<FiscalPeriodEntity>();
	}

	
	public void saveFiscalYear() {
		//if (selOrganization.getLevel().equals(EnumList.OrgOrganizationLevelEnum.L0)) {
		//	jsfMessageHelper.addInfo("levelL0NotAvailable");
		//	return;
		//}
		
		if (selFiscalYear.isNew()) {
			jsfMessageHelper.addInfo("createSuccessful","FiscalYear");
		} else {
			jsfMessageHelper.addInfo("updateSuccessful","FiscalYear");
		}
		selFiscalYear = fiscalService. saveFiscalYearEntity(selFiscalYear);
		setSelOrganization(selOrganization);
	}

	public void deleteFiscalYear() {
		if (!selFiscalYear.isNew()) {
			fiscalService.deleteFiscalYearEntity(selFiscalYear);
			jsfMessageHelper.addInfo("deleteSuccessful","FiscalYear");
		}
		setSelOrganization(selOrganization);
	}
	
	public void createFiscalYear() {
		selFiscalYear = new FiscalYearEntity();
		selFiscalYear.setOrganization(selOrganization);
	}

	public void saveFiscalPeriod() {
		//if (selOrganization.getLevel().equals(EnumList.OrgOrganizationLevelEnum.L0)) {
		//	jsfMessageHelper.addInfo("levelL0NotAvailable");
		//	return;
		//}
		
		if (selFiscalPeriod.isNew()) {
			jsfMessageHelper.addInfo("createSuccessful","FiscalPeriod");
			selFiscalPeriod.setFiscalYear(selFiscalYear);
		} else {
			jsfMessageHelper.addInfo("updateSuccessful","FiscalPeriod");
		}
		selFiscalPeriod = fiscalService. saveFiscalPeriodEntity(selFiscalPeriod);
		setSelFiscalYear(selFiscalYear);
	}

	public void deleteFiscalPeriod() {
		if (!selFiscalPeriod.isNew()) {
			fiscalService.deleteFiscalPeriodEntity(selFiscalPeriod);
			jsfMessageHelper.addInfo("deleteSuccessful","FiscalPeriod");
		}
		setSelFiscalYear(selFiscalYear);
	}
	
	public void createFiscalPeriod() {
		selFiscalPeriod = new FiscalPeriodEntity();
		selFiscalPeriod.setFiscalYear(selFiscalYear);
		selFiscalPeriod.setIsAccActive(true);
		selFiscalPeriod.setIsFinActive(true);
		selFiscalPeriod.setIsStkActive(true);
	}

	
	public FiscalHandler getFiscalService() {
		return fiscalService;
	}

	public void setFiscalService(FiscalHandler fiscalService) {
		this.fiscalService = fiscalService;
	}

	public FiscalYearEntity getSelFiscalYear() {
		return selFiscalYear;
	}

	public void setSelFiscalYear(FiscalYearEntity selFiscalYear) {
		if (selFiscalYear!=null){
			this.selFiscalYear = selFiscalYear;
			fiscalPeriodList = fiscalService.findFiscalPeriodList(this.selFiscalYear);
		} else {
			fiscalPeriodList = new ArrayList<FiscalPeriodEntity>();
		}
		createFiscalPeriod();
	}

	public List<FiscalYearEntity> getFiscalYearList() {
		return fiscalYearList;
	}

	public void setFiscalYearList(List<FiscalYearEntity> fiscalYearList) {
		this.fiscalYearList = fiscalYearList;
	}

	public OrganizationEntity getSelOrganization() {
		return selOrganization;
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

	public FiscalPeriodEntity getSelFiscalPeriod() {
		return selFiscalPeriod;
	}

	public void setSelFiscalPeriod(FiscalPeriodEntity selFiscalPeriod) {
		if (selFiscalPeriod!=null){
			this.selFiscalPeriod = selFiscalPeriod;
		}
	}

	public List<FiscalPeriodEntity> getFiscalPeriodList() {
		return fiscalPeriodList;
	}

	public void setFiscalPeriodList(List<FiscalPeriodEntity> fiscalPeriodList) {
		this.fiscalPeriodList = fiscalPeriodList;
	}

}
