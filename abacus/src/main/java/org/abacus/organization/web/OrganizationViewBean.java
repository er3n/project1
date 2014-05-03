package org.abacus.organization.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.abacus.common.web.JsfMessageHelper;
import org.abacus.common.web.SessionInfoHelper;
import org.abacus.organization.core.persistance.repository.CompanyRepository;
import org.abacus.organization.shared.entity.CompanyEntity;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class OrganizationViewBean implements Serializable {

	private CompanyEntity selCompany;
	private List<CompanyEntity> companyList;

	@ManagedProperty(value = "#{companyRepository}")
	private CompanyRepository companyRepository;

	@ManagedProperty(value = "#{sessionInfoHelper}")
	private SessionInfoHelper sessionInfoHelper;

	@ManagedProperty(value = "#{jsfMessageHelper}")
	private JsfMessageHelper jsfMessageHelper;

	@PostConstruct
	public void init() {
		System.out.println("ViewBean Session User:"+sessionInfoHelper.currentUserName());
		System.out.println("ViewBean Session Comp:"+sessionInfoHelper.currentCompanyId());
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
		selCompany = companyRepository.save(selCompany);
		findCompanyList();
	}

	public void deleteCompany() {
		if (!selCompany.isNew()) {
			companyRepository.delete(selCompany);
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
		companyList = companyRepository.findByCompany(sessionInfoHelper.currentCompanyId());
		System.out.println(companyList);
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

	public CompanyRepository getCompanyRepository() {
		return companyRepository;
	}

	public void setCompanyRepository(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}

}
