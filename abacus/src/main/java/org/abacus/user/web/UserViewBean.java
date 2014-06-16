package org.abacus.user.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.persistence.EnumType;

import org.abacus.common.web.JsfMessageHelper;
import org.abacus.common.web.SessionInfoHelper;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.organization.shared.entity.OrganizationEntity;
import org.abacus.user.core.handler.UserService;
import org.abacus.user.shared.UserNameExistsException;
import org.abacus.user.shared.entity.SecGroupEntity;
import org.abacus.user.shared.entity.SecUserEntity;
import org.abacus.user.shared.event.CreateUserEvent;
import org.abacus.user.shared.event.ReadGroupsEvent;
import org.abacus.user.shared.event.ReadOrganizationsEvent;
import org.abacus.user.shared.event.ReadUserEvent;
import org.abacus.user.shared.event.RequestReadGroupsEvent;
import org.abacus.user.shared.event.RequestReadOrganizationsEvent;
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

	private List<OrganizationEntity> allOrganizations;

	private DualListModel<SecGroupEntity> selectedUserGroupDL;

	private DualListModel<OrganizationEntity> selectedUserOrganizationDL;

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

		ReadOrganizationsEvent allOrganizationsEvent = userService.requestOrganization(new RequestReadOrganizationsEvent(null, sessionInfoHelper.currentOrganizationId()));
		allOrganizations = allOrganizationsEvent.getOrganizationList();

	}

	public void createUser() {
		String currentUser = sessionInfoHelper.currentUserName();
		List<SecGroupEntity> selectedGroups = selectedUserGroupDL.getTarget();
		List<OrganizationEntity> userOrganizations = selectedUserOrganizationDL.getTarget();
		try {
			UserCreatedEvent createdEvent = userService.createUser(new CreateUserEvent(selectedUser, selectedGroups, userOrganizations, currentUser));
			selectedUser = createdEvent.getSecUser();
			this.reloadSearchCriteria(selectedUser.getId());
			jsfMessageHelper.addInfo("kullaniciEklendi");
		} catch (UserNameExistsException e) {
			jsfMessageHelper.addError("kullaniciAdiKullanimda");
		}
	}

	public void updateUser() {
		String currentUser = sessionInfoHelper.currentUserName();
		List<SecGroupEntity> selectedGroups = selectedUserGroupDL.getTarget();
		List<OrganizationEntity> userOrganizations = selectedUserOrganizationDL.getTarget();
		UserUpdatedEvent updatedEvent = userService.updateUser(new UpdateUserEvent(selectedUser, selectedGroups, userOrganizations, currentUser));
		this.reloadSearchCriteria(updatedEvent.getUser().getId());
		jsfMessageHelper.addInfo("kullaniciGuncellendi");
	}

	private void reloadSearchCriteria(String username) {
		searchUserCriteria = new SearchUserCriteria();
		searchUserCriteria.setUsername(username);
		this.findUser();
		searchUserCriteria = new SearchUserCriteria();
	}

	public void clear() {
		selectedUser = new SecUserEntity();
	}

	public void findUser() {
		if (searchUserCriteria.getOrganization() == null || !StringUtils.hasText(searchUserCriteria.getOrganization().getId())) {
			String organizationId = sessionInfoHelper.currentOrganizationId();
			OrganizationEntity organizationEntity = new OrganizationEntity();
			organizationEntity.setId(organizationId);
			searchUserCriteria.setOrganization(organizationEntity);
			searchUserCriteria.setHierarchy(EnumList.Hierachy.CHILD);
		}
		ReadUserEvent readUserEvent = userService.requestUser(new RequestReadUserEvent(searchUserCriteria));
		userSearchResults = readUserEvent.getUserEntityList();
	}

	public DualListModel<OrganizationEntity> selectedUserOrganization() {

		selectedUserOrganizationDL = new DualListModel<OrganizationEntity>();

		String selectedUserName = selectedUser.getId();

		List<OrganizationEntity> targetUserOrganizations = new ArrayList<>();
		List<OrganizationEntity> sourceUserOrganizations = new ArrayList<>();

		if (StringUtils.hasText(selectedUserName)) {
			ReadOrganizationsEvent userOrganizationsEvent = userService.requestOrganization(new RequestReadOrganizationsEvent(selectedUserName, null));
			targetUserOrganizations = userOrganizationsEvent.getOrganizationList();
			for (OrganizationEntity organizationEntity : allOrganizations) {
				if (!targetUserOrganizations.contains(organizationEntity)) {
					sourceUserOrganizations.add(organizationEntity);
				}
			}
		} else {
			sourceUserOrganizations = allOrganizations;
		}

		selectedUserOrganizationDL.setSource(sourceUserOrganizations);
		selectedUserOrganizationDL.setTarget(targetUserOrganizations);

		return selectedUserOrganizationDL;
	}

	public DualListModel<SecGroupEntity> selectedUserGroups() {

		selectedUserGroupDL = new DualListModel<SecGroupEntity>();

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

		selectedUserGroupDL.setSource(sourceUserGroups);
		selectedUserGroupDL.setTarget(targetUserGroups);
		return selectedUserGroupDL;
	}
	
	public List<SecGroupEntity> allUserGroup(){
		List<SecGroupEntity> all = new ArrayList<>();
		all.addAll(selectedUserGroupDL.getSource());
		all.addAll(selectedUserGroupDL.getTarget());
		return all;
	}
	
	public List<OrganizationEntity> allUserOrganization(){
		List<OrganizationEntity> all = new ArrayList<>();
		all.addAll(selectedUserOrganizationDL.getSource());
		all.addAll(selectedUserOrganizationDL.getTarget());
		return all;
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

	public DualListModel<SecGroupEntity> getSelectedUserGroupDL() {
		this.selectedUserGroupDL = selectedUserGroups();
		return selectedUserGroupDL;
	}

	public void setSelectedUserGroupDL(DualListModel<SecGroupEntity> selectedUserGroupDL) {
		this.selectedUserGroupDL = selectedUserGroupDL;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public DualListModel<OrganizationEntity> getSelectedUserOrganizationDL() {
		this.selectedUserOrganizationDL = this.selectedUserOrganization();
		return selectedUserOrganizationDL;
	}

	public void setSelectedUserOrganizationDL(DualListModel<OrganizationEntity> selectedUserOrganizationDL) {
		this.selectedUserOrganizationDL = selectedUserOrganizationDL;
	}

	public List<OrganizationEntity> getAllOrganizations() {
		return allOrganizations;
	}

	public void setAllOrganizations(List<OrganizationEntity> allOrganizations) {
		this.allOrganizations = allOrganizations;
	}


}
