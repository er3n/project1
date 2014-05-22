package org.abacus.user.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.abacus.common.web.SessionInfoHelper;
import org.abacus.organization.shared.entity.OrganizationEntity;
import org.abacus.user.core.handler.SecUserHandler;
import org.abacus.user.shared.entity.SecUserEntity;
import org.abacus.user.shared.holder.SearchUserCriteria;
import org.primefaces.context.RequestContext;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class UserTableViewBean implements Serializable {

	@ManagedProperty(value = "#{secUserHandler}")
	private SecUserHandler secUserHandler;

	@ManagedProperty(value = "#{sessionInfoHelper}")
	private SessionInfoHelper sessionInfoHelper;

	private List<SecUserEntity> userList;

	@PostConstruct
	public void init() {
		OrganizationEntity rootOrganization = sessionInfoHelper.currentRootOrganization();
		SearchUserCriteria cri = new SearchUserCriteria();
		cri.setOrganization(rootOrganization);
		this.userList = secUserHandler.findUser(cri);
	}

	public void selectUserFromDialog(SecUserEntity user) {
		RequestContext.getCurrentInstance().closeDialog(user);
	}

	public SessionInfoHelper getSessionInfoHelper() {
		return sessionInfoHelper;
	}

	public void setSessionInfoHelper(SessionInfoHelper sessionInfoHelper) {
		this.sessionInfoHelper = sessionInfoHelper;
	}

	public SecUserHandler getSecUserHandler() {
		return secUserHandler;
	}

	public void setSecUserHandler(SecUserHandler secUserHandler) {
		this.secUserHandler = secUserHandler;
	}

	public List<SecUserEntity> getUserList() {
		return userList;
	}

	public void setUserList(List<SecUserEntity> userList) {
		this.userList = userList;
	}

}
