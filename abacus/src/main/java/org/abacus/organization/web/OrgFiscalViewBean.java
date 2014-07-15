package org.abacus.organization.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.abacus.common.web.AbcUtility;
import org.abacus.common.web.JsfMessageHelper;
import org.abacus.common.web.SessionInfoHelper;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.organization.core.handler.FiscalHandler;
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

	@ManagedProperty(value = "#{jsfMessageHelper}")
	private JsfMessageHelper jsfMessageHelper;

	@ManagedProperty(value = "#{sessionInfoHelper}")
	private SessionInfoHelper sessionInfoHelper;

	
	@PostConstruct
	public void init() {
		createFiscal();
	}

	public void setSelOrganization(OrganizationEntity selType) {
		this.selOrganization = selType;
		createFiscal();
		fiscalYearList = fiscalService.findFiscalYearList(selType.getId());
	}

	
	public void saveFiscal() {
		if (selOrganization.getLevel().equals(EnumList.OrgOrganizationLevelEnum.L0)) {
			jsfMessageHelper.addInfo("levelL1NotAvailable");
			return;
		}
		
		if (selFiscalYear.isNew()) {
			jsfMessageHelper.addInfo("createSuccessful","Fiscal");
			selFiscalYear.setId(selOrganization.getId()+"."+AbcUtility.LPad(sessionInfoHelper.getNewId().toString(), 8, '0'));
		} else {
			jsfMessageHelper.addInfo("updateSuccessful","Fiscal");
		}
		fiscalService. saveFiscalYearEntity(selFiscalYear);
		setSelOrganization(selOrganization);
	}

	public void deleteFiscal() {
		if (!selFiscalYear.isNew()) {
			fiscalService.deleteFiscalYearEntity(selFiscalYear);
			jsfMessageHelper.addInfo("deleteSuccessful","Fiscal");
		}
		setSelOrganization(selOrganization);
	}
	
	public void createFiscal() {
		selFiscalYear = new FiscalYearEntity();
		selFiscalYear.setOrganization(selOrganization);
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
		} else {
			createFiscal();
		}
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

}
