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
import org.abacus.definition.shared.entity.DefUnitCodeEntity;
import org.abacus.definition.shared.entity.DefUnitGroupEntity;
import org.abacus.organization.core.handler.DepartmentHandler;
import org.abacus.organization.shared.entity.OrganizationEntity;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class UnitCodeViewBean implements Serializable {

	@ManagedProperty(value = "#{jsfMessageHelper}")
	private JsfMessageHelper jsfMessageHelper;
	
	@ManagedProperty(value = "#{departmentHandler}")
	private DepartmentHandler departmentService;

	private DefUnitGroupEntity selUnitGruop;

	private DefUnitCodeEntity selUnitCode;
	private List<DefUnitCodeEntity> unitCodeList;

	@PostConstruct
	public void init() {
	}
	
	public void setSelUnitGroup(DefUnitGroupEntity unitGroup) {
		this.selUnitGruop = unitGroup;
		findOrganizationDepartment();
	}

	public void departmentRowSelectListener() {
//		System.out.println("taskRowSelectListener");
	}

	public void groupChangeListener(){
		findOrganizationDepartment();
	}
	
	public void saveDepartment() {
		if (selUnitCode.isNew()) {
			jsfMessageHelper.addInfo("departmentKayitIslemiBasarili");
		} else {
			jsfMessageHelper.addInfo("departmentGuncellemeIslemiBasarili");
		}
//		departmentService.saveDepartmentEntity(selUnitCode);
		findOrganizationDepartment();
	}

	public void deleteDepartment() {
		if (!selUnitCode.isNew()) {
//			departmentService.deleteDepartmentEntity(selUnitCode);
			jsfMessageHelper.addInfo("departmentSilmeIslemiBasarili");
		}
		findOrganizationDepartment();
	}

	public void createDepartment() {
		selUnitCode = new DefUnitCodeEntity();
//		selUnitCode.setOrganization(selUnitGruop);
//		selUnitCode.setTransientGroup(selectedGroupEnum);
	}

	public void findOrganizationDepartment() {
		createDepartment();
		unitCodeList = null;
		if (selUnitGruop!=null){
//			unitCodeList = departmentService.findByOrganizationAndGroup(selUnitGruop.getId(), EnumList.OrgDepartmentGroupEnum.valueOf(selectedGroupEnum.name()));
		} else {
			unitCodeList = new ArrayList<DefUnitCodeEntity>();
		}
//		System.out.println(taskList);
	}
	
	public JsfMessageHelper getJsfMessageHelper() {
		return jsfMessageHelper;
	}

	public void setJsfMessageHelper(JsfMessageHelper jsfMessageHelper) {
		this.jsfMessageHelper = jsfMessageHelper;
	}


	public DefUnitCodeEntity getSelUnitCode() {
		return selUnitCode;
	}

	public void setSelUnitCode(DefUnitCodeEntity selUnitCode) {
		if (selUnitCode!=null){
			this.selUnitCode = selUnitCode;
		}
	}



}
