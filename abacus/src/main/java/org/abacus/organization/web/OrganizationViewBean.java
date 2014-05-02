package org.abacus.organization.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.abacus.common.web.JsfMessageHelper;
import org.abacus.common.web.SessionInfoHelper;
import org.abacus.organization.core.handler.CompanyHandler;
import org.abacus.organization.shared.entity.CompanyEntity;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class OrganizationViewBean implements Serializable {

	private CompanyEntity selCompany;
	private List<CompanyEntity> companyList;

	@ManagedProperty(value = "#{companyHandler}")
	private CompanyHandler companyHandler;

	@ManagedProperty(value = "#{sessionInfoHelper}")
	private SessionInfoHelper sessionInfoHelper;

	@ManagedProperty(value = "#{jsfMessageHelper}")
	private JsfMessageHelper jsfMessageHelper;

	@PostConstruct
	public void init() {
		System.out.println("ViewBean Session User:"+sessionInfoHelper.currentUserName());
		System.out.println("ViewBean Session Comp:"+sessionInfoHelper.currentCompany());
		findCompanyList();
	}
	
	public void groupChangeListener(){
		clearCompany();
	}

	public void companyRowSelectListener() {
	}

	public void saveOrUpdateCompany() {
		if (selCompany.isNew()) {
			jsfMessageHelper.addInfo("companyKayitIslemiBasarili");
		} else {
			jsfMessageHelper.addInfo("companyGuncellemeIslemiBasarili");
		}
		selCompany = companyHandler.saveCompanyEntity(selCompany);
		findCompanyList();
	}

	public void deleteCompany() {
		if (!selCompany.isNew()) {
			companyHandler.deleteCompanyEntity(selCompany);
			jsfMessageHelper.addInfo("companySilmeIslemiBasarili");
		}
		findCompanyList();
	}

	public void clearCompany() {
		selCompany = new CompanyEntity();
	}

	public void findCompanyList() {
		clearCompany();
		companyList = null;
		companyList = companyHandler.findAllOrderById();
	}

	public SessionInfoHelper getSessionInfoHelper() {
		return sessionInfoHelper;
	}

	public void setSessionInfoHelper(SessionInfoHelper sessionInfoHelper) {
		this.sessionInfoHelper = sessionInfoHelper;
	}

	public CompanyHandler getCompanyHandler() {
		return companyHandler;
	}

	public void setCompanyHandler(CompanyHandler companyHandler) {
		this.companyHandler = companyHandler;
	}

	public JsfMessageHelper getJsfMessageHelper() {
		return jsfMessageHelper;
	}

	public void setJsfMessageHelper(JsfMessageHelper jsfMessageHelper) {
		this.jsfMessageHelper = jsfMessageHelper;
	}

	public CompanyEntity getSelCompany() {
		return selCompany;
	}

	public void setSelCompany(CompanyEntity selCompany) {
		if (selCompany!=null){
			this.selCompany = selCompany;
		}
	}

	public List<CompanyEntity> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(List<CompanyEntity> companyList) {
		this.companyList = companyList;
	}

}
