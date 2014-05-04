package org.abacus.organization.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.abacus.common.web.JsfMessageHelper;
import org.abacus.organization.core.handler.DepartmentHandler;
import org.abacus.organization.shared.entity.CompanyEntity;
import org.abacus.organization.shared.entity.DepartmentEntity;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class OrgDepartmentViewBean implements Serializable {

	@ManagedProperty(value = "#{jsfMessageHelper}")
	private JsfMessageHelper jsfMessageHelper;
	
	@ManagedProperty(value = "#{departmentHandler}")
	private DepartmentHandler departmentService;

	private CompanyEntity selCompany;

	private DepartmentEntity selDepartment;
	private List<DepartmentEntity> departmentList;


	@PostConstruct
	public void init() {
	}
	
	public void setSelCompany(CompanyEntity selType) {
		this.selCompany = selType;
		findCompanyDepartment();
	}

	public void taskRowSelectListener() {
//		System.out.println("taskRowSelectListener");
	}

	
	public void saveDepartment() {
		if (selDepartment.isNew()) {
			jsfMessageHelper.addInfo("taskKayitIslemiBasarili");
		} else {
			jsfMessageHelper.addInfo("taskGuncellemeIslemiBasarili");
		}
		departmentService.saveDepartmentEntity(selDepartment);
		findCompanyDepartment();
	}

	public void deleteDepartment() {
		if (!selDepartment.isNew()) {
			departmentService.deleteDepartmentEntity(selDepartment);
			jsfMessageHelper.addInfo("taskSilmeIslemiBasarili");
		}
		findCompanyDepartment();
	}

	public void createDepartment() {
		selDepartment = new DepartmentEntity();
		selDepartment.setCompany(selCompany);
	}

	public void findCompanyDepartment() {
		createDepartment();
		departmentList = null;
		if (selCompany!=null){
			departmentList = departmentService.findByCompany(selCompany.getId());
		} else {
			departmentList = new ArrayList<DepartmentEntity>();
		}
//		System.out.println(taskList);
	}
	
	public JsfMessageHelper getJsfMessageHelper() {
		return jsfMessageHelper;
	}

	public void setJsfMessageHelper(JsfMessageHelper jsfMessageHelper) {
		this.jsfMessageHelper = jsfMessageHelper;
	}


	public DepartmentEntity getSelDepartment() {
		return selDepartment;
	}

	public void setSelDepartment(DepartmentEntity selDepartment) {
		if (selDepartment!=null){
			this.selDepartment = selDepartment;
		}
	}

	public DepartmentHandler getDefTaskService() {
		return departmentService;
	}

	public void setDefTaskService(DepartmentHandler defTaskService) {
		this.departmentService = defTaskService;
	}

	public List<DepartmentEntity> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<DepartmentEntity> departmentList) {
		this.departmentList = departmentList;
	}


}
