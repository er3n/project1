package org.abacus.organization.web;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.abacus.common.web.JsfDialogHelper;
import org.abacus.common.web.JsfMessageHelper;
import org.abacus.common.web.SessionInfoHelper;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.entity.DefItemEntity;
import org.abacus.organization.core.handler.DepartmentHandler;
import org.abacus.organization.shared.entity.DepartmentEntity;
import org.abacus.user.core.handler.SecUserHandler;
import org.abacus.user.shared.entity.SecUserDepartmentEntity;
import org.abacus.user.shared.entity.SecUserEntity;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class OrgDepartmentUserViewBean implements Serializable {

	@ManagedProperty(value = "#{sessionInfoHelper}")
	private SessionInfoHelper sessionInfoHelper;

	@ManagedProperty(value = "#{departmentHandler}")
	private DepartmentHandler departmentHandler;

	@ManagedProperty(value = "#{secUserHandler}")
	private SecUserHandler secUserHandler;

	private DepartmentEntity selDepartment;
	private SecUserDepartmentEntity selDepartmentUser;

	@ManagedProperty(value = "#{jsfMessageHelper}")
	private JsfMessageHelper jsfMessageHelper;

	@ManagedProperty(value = "#{jsfDialogHelper}")
	private JsfDialogHelper jsfDialogHelper;
	
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
		if (selDepartmentUser!=null){
			this.selDepartmentUser = selDepartmentUser;
		}
	}

	public JsfMessageHelper getJsfMessageHelper() {
		return jsfMessageHelper;
	}

	public void setJsfMessageHelper(JsfMessageHelper jsfMessageHelper) {
		this.jsfMessageHelper = jsfMessageHelper;
	}

	public SecUserHandler getSecUserHandler() {
		return secUserHandler;
	}

	public void setSecUserHandler(SecUserHandler secUserHandler) {
		this.secUserHandler = secUserHandler;
	}
	
	public void chooseUser() {
		if (selDepartmentUser!=null) {
			jsfDialogHelper.openUserSelectionDialog();
		}
	}

	public void onUserChosen(SelectEvent event) {
		if (selDepartmentUser!=null) {
			SecUserEntity user = (SecUserEntity) event.getObject();
			selDepartmentUser.setUser(user);
		}
	}

	public void clearUser() {
		if (selDepartmentUser!=null) {
			selDepartmentUser.setUser(new SecUserEntity());
		}
	}

	public JsfDialogHelper getJsfDialogHelper() {
		return jsfDialogHelper;
	}

	public void setJsfDialogHelper(JsfDialogHelper jsfDialogHelper) {
		this.jsfDialogHelper = jsfDialogHelper;
	}

}
