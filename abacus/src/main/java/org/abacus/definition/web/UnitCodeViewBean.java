package org.abacus.definition.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.abacus.common.web.JsfMessageHelper;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.constant.SelectionEnum;
import org.abacus.organization.core.handler.DepartmentHandler;
import org.abacus.organization.shared.entity.CompanyEntity;
import org.abacus.organization.shared.entity.DepartmentEntity;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class UnitCodeViewBean implements Serializable {

	@ManagedProperty(value = "#{jsfMessageHelper}")
	private JsfMessageHelper jsfMessageHelper;
	
	@ManagedProperty(value = "#{departmentHandler}")
	private DepartmentHandler departmentService;

	private CompanyEntity selCompany;

	private DepartmentEntity selDepartment;
	private List<DepartmentEntity> departmentList;

	private SelectionEnum[] groupEnums;
	private SelectionEnum selectedGroupEnum;
	
	@PostConstruct
	public void init() {
		createGroupEnumArray();
		selectedGroupEnum = groupEnums[0];
	}
	
	private void createGroupEnumArray(){
		groupEnums = new SelectionEnum[EnumList.OrgDepartmentGroupEnum.values().length];
		for (EnumList.OrgDepartmentGroupEnum enm : EnumList.OrgDepartmentGroupEnum.values()) {
			groupEnums[enm.ordinal()] = new SelectionEnum(enm);
		}
	}
	public void setSelCompany(CompanyEntity selType) {
		this.selCompany = selType;
		findCompanyDepartment();
	}

	public void departmentRowSelectListener() {
//		System.out.println("taskRowSelectListener");
	}

	public void groupChangeListener(){
		findCompanyDepartment();
	}
	
	public void saveDepartment() {
		if (selDepartment.isNew()) {
			jsfMessageHelper.addInfo("departmentKayitIslemiBasarili");
		} else {
			jsfMessageHelper.addInfo("departmentGuncellemeIslemiBasarili");
		}
		departmentService.saveDepartmentEntity(selDepartment);
		findCompanyDepartment();
	}

	public void deleteDepartment() {
		if (!selDepartment.isNew()) {
			departmentService.deleteDepartmentEntity(selDepartment);
			jsfMessageHelper.addInfo("departmentSilmeIslemiBasarili");
		}
		findCompanyDepartment();
	}

	public void createDepartment() {
		selDepartment = new DepartmentEntity();
		selDepartment.setCompany(selCompany);
		selDepartment.setTransientGroup(selectedGroupEnum);
	}

	public void findCompanyDepartment() {
		createDepartment();
		departmentList = null;
		if (selCompany!=null){
			departmentList = departmentService.findByCompanyAndGroup(selCompany.getId(), EnumList.OrgDepartmentGroupEnum.valueOf(selectedGroupEnum.name()));
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

	public DepartmentHandler getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(DepartmentHandler departmentService) {
		this.departmentService = departmentService;
	}

	public List<DepartmentEntity> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<DepartmentEntity> departmentList) {
		this.departmentList = departmentList;
	}

	public SelectionEnum getSelectedGroupEnum() {
		return selectedGroupEnum;
	}

	public void setSelectedGroupEnum(SelectionEnum selectedGroupEnum) {
		this.selectedGroupEnum = selectedGroupEnum;
	}

	public SelectionEnum[] getGroupEnums() {
		return groupEnums;
	}

	public void setGroupEnums(SelectionEnum[] groupEnums) {
		this.groupEnums = groupEnums;
	}

	public CompanyEntity getSelCompany() {
		return selCompany;
	}


}
