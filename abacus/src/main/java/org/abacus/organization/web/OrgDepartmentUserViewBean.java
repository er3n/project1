package org.abacus.organization.web;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.abacus.common.web.JsfMessageHelper;
import org.abacus.common.web.SessionInfoHelper;
import org.abacus.organization.core.handler.DepartmentHandler;
import org.abacus.organization.shared.entity.DepartmentEntity;
import org.abacus.user.shared.entity.SecUserDepartmentEntity;
import org.primefaces.context.RequestContext;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class OrgDepartmentUserViewBean implements Serializable {

	@ManagedProperty(value = "#{sessionInfoHelper}")
	private SessionInfoHelper sessionInfoHelper;

	@ManagedProperty(value = "#{departmentHandler}")
	private DepartmentHandler departmentHandler;

	private DepartmentEntity selDepartment;
	private SecUserDepartmentEntity selDepartmentUser;

	@ManagedProperty(value = "#{jsfMessageHelper}")
	private JsfMessageHelper jsfMessageHelper;
	
	@PostConstruct
	public void init() {
		String department_id= FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("department_id");
		this.selDepartment = departmentHandler.getDepartmentEntity(Long.valueOf(department_id)); 
		System.out.println("selDepartment:"+selDepartment.getCode()+":"+selDepartment.getName());
	}

	public void departmentUserRowSelectListener() {
	}

	
	public void saveDepartmentUserList() {
		if (selDepartment.isNew()) {
			jsfMessageHelper.addInfo("departmentKayitIslemiBasarili");
		} else {
			jsfMessageHelper.addInfo("departmentGuncellemeIslemiBasarili");
		}
		departmentHandler.saveDepartmentEntity(selDepartment);
//		findOrganizationDepartment();
	}

	public void deleteDepartmentUser() {
		selDepartment.getDepartmentUserList().remove(selDepartmentUser);
//		if (!selDepartment.isNew()) {
//			departmentService.deleteDepartmentEntity(selDepartment);
//			jsfMessageHelper.addInfo("departmentSilmeIslemiBasarili");
//		}
//		findOrganizationDepartment();
	}

	public void createDepartmentUser() {
//		selDepartmentUser = new Sec ();
//		selDepartmentUser.setOrganization(selOrganization);
//		selDepartmentUser.setDepartmentGroup(selectedGroupEnum);
	}
	
	public void selectFromDialog() {
		RequestContext.getCurrentInstance().closeDialog(null);
	}

	public SessionInfoHelper getSessionInfoHelper() {
		return sessionInfoHelper;
	}

	public void setSessionInfoHelper(SessionInfoHelper sessionInfoHelper) {
		this.sessionInfoHelper = sessionInfoHelper;
	}

	public DepartmentHandler getDepartmentHandler() {
		return departmentHandler;
	}

	public void setDepartmentHandler(DepartmentHandler departmentHandler) {
		this.departmentHandler = departmentHandler;
	}

	public DepartmentEntity getSelDepartment() {
		return selDepartment;
	}

	public void setSelDepartment(DepartmentEntity selDepartment) {
		this.selDepartment = selDepartment;
	}

	public SecUserDepartmentEntity getSelDepartmentUser() {
		return selDepartmentUser;
	}

	public void setSelDepartmentUser(SecUserDepartmentEntity selDepartmentUser) {
		this.selDepartmentUser = selDepartmentUser;
	}

	public JsfMessageHelper getJsfMessageHelper() {
		return jsfMessageHelper;
	}

	public void setJsfMessageHelper(JsfMessageHelper jsfMessageHelper) {
		this.jsfMessageHelper = jsfMessageHelper;
	}

}
