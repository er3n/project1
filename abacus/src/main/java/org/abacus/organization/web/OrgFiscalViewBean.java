package org.abacus.organization.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

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

	@PostConstruct
	public void init() {
	}

	public void setSelOrganization(OrganizationEntity selType) {
		this.selOrganization = selType;
		this.selFiscalYear = null;
		fiscalYearList = fiscalService.findFiscalYearList(selType.getId());
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

}
