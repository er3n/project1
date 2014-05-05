package org.abacus.definition.web;

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
import org.abacus.organization.shared.entity.CompanyEntity;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class UnitViewBean implements Serializable {

	private CompanyEntity selCompany;
	private List<CompanyEntity> companyList;

	@ManagedProperty(value = "#{companyHandler}")
	private CompanyHandler companyHandler;

	@ManagedProperty(value = "#{sessionInfoHelper}")
	private SessionInfoHelper sessionInfoHelper;

	@ManagedProperty(value = "#{jsfMessageHelper}")
	private JsfMessageHelper jsfMessageHelper;
	
	@ManagedProperty(value = "#{orgDepartmentViewBean}")
	private UnitCodeViewBean orgDepartmentViewBean;

	private SelectionEnum[] levelEnums;
	
	@PostConstruct
	public void init() {
		createLevelEnumArray();
		System.out.println("ViewBean Session User:"+sessionInfoHelper.currentUserName());
		System.out.println("ViewBean Session Comp:"+sessionInfoHelper.currentCompanyId());
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
		selCompany = new CompanyEntity();
		orgDepartmentViewBean.setSelCompany(null);
	}

	public void findCompanyList() {
		clearCompany();
		companyList = null;
		companyList = companyHandler.findByCompany(sessionInfoHelper.currentCompanyId());
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

	public UnitCodeViewBean getOrgDepartmentViewBean() {
		return orgDepartmentViewBean;
	}

	public void setOrgDepartmentViewBean(UnitCodeViewBean orgDepartmentViewBean) {
		this.orgDepartmentViewBean = orgDepartmentViewBean;
	}

}
