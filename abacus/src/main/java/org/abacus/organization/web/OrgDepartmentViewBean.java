package org.abacus.organization.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.abacus.common.web.JsfDialogHelper;
import org.abacus.common.web.JsfMessageHelper;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.organization.core.handler.DepartmentHandler;
import org.abacus.organization.shared.entity.DepartmentEntity;
import org.abacus.organization.shared.entity.OrganizationEntity;
import org.primefaces.event.SelectEvent;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class OrgDepartmentViewBean implements Serializable {

	@ManagedProperty(value = "#{jsfMessageHelper}")
	private JsfMessageHelper jsfMessageHelper;

	@ManagedProperty(value = "#{departmentHandler}")
	private DepartmentHandler departmentService;

	private OrganizationEntity selOrganization;

	private DepartmentEntity selDepartment;
	private List<DepartmentEntity> departmentList;

	@ManagedProperty(value = "#{jsfDialogHelper}")
	private JsfDialogHelper jsfDialogHelper;

	private EnumList.OrgDepartmentGroupEnum selectedGroupEnum;


	@PostConstruct
	public void init() {
		selectedGroupEnum = EnumList.OrgDepartmentGroupEnum.S;
	}

	public void setSelOrganization(OrganizationEntity selType) {
		this.selOrganization = selType;
		findOrganizationDepartment();
	}

	public void departmentRowSelectListener() {
	}

	public void groupChangeListener() {
		findOrganizationDepartment();
	}

	public void saveDepartment() {
		if (selDepartment.isNew()) {
			jsfMessageHelper.addInfo("departmentKayitIslemiBasarili");
		} else {
			jsfMessageHelper.addInfo("departmentGuncellemeIslemiBasarili");
		}
		departmentService.saveDepartmentEntity(selDepartment);
		findOrganizationDepartment();
	}

	public void deleteDepartment() {
		if (!selDepartment.isNew()) {
			departmentService.deleteDepartmentEntity(selDepartment);
			jsfMessageHelper.addInfo("departmentSilmeIslemiBasarili");
		}
		findOrganizationDepartment();
	}

	public void createDepartment() {
		selDepartment = new DepartmentEntity();
		selDepartment.setOrganization(selOrganization);
		selDepartment.setDepartmentGroup(selectedGroupEnum);
	}

	public void findOrganizationDepartment() {
		createDepartment();
		departmentList = null;
		if (selOrganization != null) {
			departmentList = departmentService.findByOrganizationAndGroup(selOrganization.getId(), selectedGroupEnum);
		} else {
			departmentList = new ArrayList<DepartmentEntity>();
		}
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
		if (selDepartment != null) {
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

	public OrganizationEntity getSelOrganization() {
		return selOrganization;
	}

	public void chooseDepartmentUserAuth() {
		if (selDepartment != null && !selDepartment.isNew()) {
			jsfDialogHelper.openDepartmentUserAuthDialog(selDepartment);
		}
	}

	public void onDepartmentUserAuthChosen(SelectEvent event) {
		// DefValueEntity category = (DefValueEntity) event.getObject();
		// selectedItem.setCategory(category);
	}

	public JsfDialogHelper getJsfDialogHelper() {
		return jsfDialogHelper;
	}

	public void setJsfDialogHelper(JsfDialogHelper jsfDialogHelper) {
		this.jsfDialogHelper = jsfDialogHelper;
	}

	public EnumList.OrgDepartmentGroupEnum getSelectedGroupEnum() {
		return selectedGroupEnum;
	}

	public void setSelectedGroupEnum(
			EnumList.OrgDepartmentGroupEnum selectedGroupEnum) {
		this.selectedGroupEnum = selectedGroupEnum;
	}

}
