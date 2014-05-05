package org.abacus.organization.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.abacus.common.web.JsfMessageHelper;
import org.abacus.common.web.SessionInfoHelper;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.constant.SelectionEnum;
import org.abacus.organization.core.handler.CompanyHandler;
import org.abacus.organization.shared.entity.OrganizationEntity;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class OrganizationViewBean implements Serializable {

	private OrganizationEntity selCompany;
	private List<OrganizationEntity> companyList;

	@ManagedProperty(value = "#{companyHandler}")
	private CompanyHandler companyHandler;

	@ManagedProperty(value = "#{sessionInfoHelper}")
	private SessionInfoHelper sessionInfoHelper;

	@ManagedProperty(value = "#{jsfMessageHelper}")
	private JsfMessageHelper jsfMessageHelper;
	
	@ManagedProperty(value = "#{orgDepartmentViewBean}")
	private OrgDepartmentViewBean orgDepartmentViewBean;

	private SelectionEnum[] levelEnums;
	
	@PostConstruct
	public void init() {
		createLevelEnumArray();
		System.out.println("ViewBean Session User:"+sessionInfoHelper.currentUserName());
		System.out.println("ViewBean Session Comp:"+sessionInfoHelper.currentOrganizationId());
		findCompanyList();
	}
	
	private void createLevelEnumArray(){
		levelEnums = new SelectionEnum[EnumList.OrgCompanyLevelEnum.values().length];
		for (EnumList.OrgCompanyLevelEnum enm : EnumList.OrgCompanyLevelEnum.values()) {
			levelEnums[enm.ordinal()] = new SelectionEnum(enm);
		}
	}
	
	public void groupChangeListener(){
		clearCompany();
	}

	public void companyRowSelectListener() {
		orgDepartmentViewBean.setSelCompany(selCompany);
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
		selCompany = new OrganizationEntity();
		orgDepartmentViewBean.setSelCompany(null);
	}

	public void findCompanyList() {
		clearCompany();
		companyList = null;
		companyList = companyHandler.findByCompany(sessionInfoHelper.currentOrganizationId());
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

	public OrganizationEntity getSelCompany() {
		return selCompany;
	}

	public void setSelCompany(OrganizationEntity selCompany) {
		if (selCompany!=null){
			this.selCompany = selCompany;
		}
	}

	public List<OrganizationEntity> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(List<OrganizationEntity> companyList) {
		this.companyList = companyList;
	}

	public CompanyHandler getCompanyHandler() {
		return companyHandler;
	}

	public void setCompanyHandler(CompanyHandler companyHandler) {
		this.companyHandler = companyHandler;
	}

	public SelectionEnum[] getLevelEnums() {
		return levelEnums;
	}

	public void setLevelEnums(SelectionEnum[] levelEnums) {
		this.levelEnums = levelEnums;
	}

	public OrgDepartmentViewBean getOrgDepartmentViewBean() {
		return orgDepartmentViewBean;
	}

	public void setOrgDepartmentViewBean(OrgDepartmentViewBean orgDepartmentViewBean) {
		this.orgDepartmentViewBean = orgDepartmentViewBean;
	}

}
