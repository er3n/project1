package org.abacus.user.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.abacus.common.web.JsfMessageHelper;
import org.abacus.common.web.SessionInfoHelper;
import org.abacus.user.core.handler.UserService;
import org.abacus.user.shared.UserNameExistsException;
import org.abacus.user.shared.entity.CompanyEntity;
import org.abacus.user.shared.entity.SecGroupEntity;
import org.abacus.user.shared.entity.SecUserEntity;
import org.abacus.user.shared.event.CreateUserEvent;
import org.abacus.user.shared.event.ReadGroupsEvent;
import org.abacus.user.shared.event.ReadUserEvent;
import org.abacus.user.shared.event.RequestReadGroupsEvent;
import org.abacus.user.shared.event.RequestReadUserEvent;
import org.abacus.user.shared.event.UpdateUserEvent;
import org.abacus.user.shared.event.UserCreatedEvent;
import org.abacus.user.shared.event.UserUpdatedEvent;
import org.abacus.user.shared.holder.SearchUserCriteria;
import org.primefaces.model.DualListModel;
import org.springframework.util.StringUtils;

@ManagedBean
@ViewScoped
public class UserViewBean implements Serializable {

	@ManagedProperty(value = "#{jsfMessageHelper}")
	private JsfMessageHelper jsfMessageHelper;

	@ManagedProperty(value = "#{sessionInfoHelper}")
	private SessionInfoHelper sessionInfoHelper;

	private SearchUserCriteria searchUserCriteria;

	private List<SecUserEntity> userSearchResults;

	private SecUserEntity selectedUser;

	private List<SecGroupEntity> allGroups;

	private DualListModel<SecGroupEntity> selectedGroupMemberDL;

	@ManagedProperty(value = "#{userEventHandler}")
	private UserService userService;

	@PostConstruct
	public void init() {
		searchUserCriteria = new SearchUserCriteria();
		selectedUser = new SecUserEntity();
		userSearchResults = null;
		this.clear();
		RequestReadGroupsEvent event = new RequestReadGroupsEvent();
		ReadGroupsEvent allGroupsEvent = userService.requestGroup(event);
		allGroups = allGroupsEvent.getGroupList();
	}

	public void createUser() {
		String company = sessionInfoHelper.currentCompany();
		String currentUser = sessionInfoHelper.currentUserName();
		CompanyEntity companyEntity = new CompanyEntity();
		companyEntity.setId(company);
		selectedUser.setCompanyEntity(companyEntity);
		List<SecGroupEntity> selectedGroups = selectedGroupMemberDL.getTarget();
		try {
			UserCreatedEvent createdEvent = userService.createUser(new CreateUserEvent(selectedUser, selectedGroups, currentUser));
			selectedUser = createdEvent.getSecUser();
			this.reloadSearchCriteria(selectedUser.getId());
			jsfMessageHelper.addInfo("kullaniciEklendi");
		} catch (UserNameExistsException e) {
			jsfMessageHelper.addError("kullaniciAdiKullanimda");
		}
	}

	public void updateUser() {
		String currentUser = sessionInfoHelper.currentUserName();
		List<SecGroupEntity> selectedGroups = selectedGroupMemberDL.getTarget();
		UserUpdatedEvent updatedEvent = userService.updateUser(new UpdateUserEvent(selectedUser, selectedGroups, currentUser));
		this.reloadSearchCriteria(updatedEvent.getUser().getId());
		jsfMessageHelper.addInfo("kullaniciGuncellendi");
	}
	
	private void reloadSearchCriteria(String username){
		searchUserCriteria = new SearchUserCriteria();
		searchUserCriteria.setUsername(username);
		this.findUser();
	}

	public void clear() {
		selectedUser = new SecUserEntity();
	}

	public void findUser() {
		String company = sessionInfoHelper.currentCompany();
		searchUserCriteria.setCompany(company);
		ReadUserEvent readUserEvent = userService.requestUser(new RequestReadUserEvent(searchUserCriteria));
		userSearchResults = readUserEvent.getUserEntityList();
	}

	public DualListModel<SecGroupEntity> selectedUserGroups() {

		selectedGroupMemberDL = new DualListModel<SecGroupEntity>();

		String selectedUserName = selectedUser.getId();

		List<SecGroupEntity> targetUserGroups = new ArrayList<>();
		List<SecGroupEntity> sourceUserGroups = new ArrayList<>();

		if (StringUtils.hasText(selectedUserName)) {
			ReadGroupsEvent readUserGroupsEvent = userService.requestGroup(new RequestReadGroupsEvent(selectedUserName));
			targetUserGroups = readUserGroupsEvent.getGroupList();

			for (SecGroupEntity groupEntity : allGroups) {
				if (!targetUserGroups.contains(groupEntity)) {
					sourceUserGroups.add(groupEntity);
				}
			}
		} else {
			sourceUserGroups = allGroups;

		}

		selectedGroupMemberDL.setSource(sourceUserGroups);
		selectedGroupMemberDL.setTarget(targetUserGroups);
		return selectedGroupMemberDL;
	}

	public JsfMessageHelper getJsfMessageHelper() {
		return jsfMessageHelper;
	}

	public void setJsfMessageHelper(JsfMessageHelper jsfMessageHelper) {
		this.jsfMessageHelper = jsfMessageHelper;
	}

	public SessionInfoHelper getSessionInfoHelper() {
		return sessionInfoHelper;
	}

	public void setSessionInfoHelper(SessionInfoHelper sessionInfoHelper) {
		this.sessionInfoHelper = sessionInfoHelper;
	}

	public SearchUserCriteria getSearchUserCriteria() {
		return searchUserCriteria;
	}

	public void setSearchUserCriteria(SearchUserCriteria searchUserCriteria) {
		this.searchUserCriteria = searchUserCriteria;
	}

	public List<SecUserEntity> getUserSearchResults() {
		return userSearchResults;
	}

	public void setUserSearchResults(List<SecUserEntity> userSearchResults) {
		this.userSearchResults = userSearchResults;
	}

	public SecUserEntity getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(SecUserEntity selectedUser) {
		this.selectedUser = selectedUser;
	}

	public List<SecGroupEntity> getAllGroups() {
		return allGroups;
	}

	public void setAllGroups(List<SecGroupEntity> allGroups) {
		this.allGroups = allGroups;
	}

	public DualListModel<SecGroupEntity> getSelectedGroupMemberDL() {
		this.selectedGroupMemberDL = selectedUserGroups();
		return selectedGroupMemberDL;
	}

	public void setSelectedGroupMemberDL(DualListModel<SecGroupEntity> selectedGroupMemberDL) {
		this.selectedGroupMemberDL = selectedGroupMemberDL;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
