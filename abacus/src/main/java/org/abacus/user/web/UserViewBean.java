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
		ReadGroupsEvent allGroupsEvent = userService.requestGroupShow(new RequestReadGroupsEvent(sessionInfoHelper.currentUserName(), sessionInfoHelper.isRootUser()));
		allGroups = allGroupsEvent.getGroupList();

		ReadOrganizationsEvent allOrganizationsEvent = userService.requestOrganization(new RequestReadOrganizationsEvent(null, sessionInfoHelper.currentOrganization()));
		allOrganizations = allOrganizationsEvent.getOrganizationList();

	}

	public void createUser() {
		selectedUser.setOrganizationRoot(sessionInfoHelper.currentOrganization().getRootOrganization().getId());
		String logUser = sessionInfoHelper.currentUserName();
		List<SecGroupEntity> selectedGroups = selectedUserGroupDL.getTarget();
		List<OrganizationEntity> userOrganizations = selectedUserOrganizationDL.getTarget();
		try {
			UserCreatedEvent createdEvent = userService.createUser(new CreateUserEvent(selectedUser, selectedGroups, userOrganizations, logUser));
			selectedUser = createdEvent.getSecUser();
			this.reloadSearchCriteria(selectedUser);
			jsfMessageHelper.addInfo("createSuccessful","Kullanıcı");
		} catch (UserNameExistsException e) {
			jsfMessageHelper.addException(e);
		}
	}

	public void updateUser() {
		String logUser = sessionInfoHelper.currentUserName();
		List<SecGroupEntity> selectedGroups = selectedUserGroupDL.getTarget();
		List<OrganizationEntity> userOrganizations = selectedUserOrganizationDL.getTarget();
		UserUpdatedEvent updatedEvent = userService.updateUser(new UpdateUserEvent(selectedUser, selectedGroups, userOrganizations, logUser));
		this.reloadSearchCriteria(updatedEvent.getUser());
		jsfMessageHelper.addInfo("updateSuccessful","Kullanıcı");
	}

	private void reloadSearchCriteria(SecUserEntity user) {
		searchUserCriteria = new SearchUserCriteria();
		searchUserCriteria.setUser(user);
		searchUserCriteria.setIsRootUser(sessionInfoHelper.isRootUser());
		this.findUser();
		searchUserCriteria = new SearchUserCriteria();
	}

	public void clear() {
		selectedUser = new SecUserEntity();
	}

	public void findUser() {
		searchUserCriteria.setHierarchy(EnumList.Hierachy.CHILD);
		searchUserCriteria.setIsRootUser(sessionInfoHelper.isRootUser());
		SearchUserCriteria searchUserCriteriaSession =  new SearchUserCriteria();
		searchUserCriteriaSession.setUser(new SecUserEntity(sessionInfoHelper.currentUserName()));
		ReadUserEvent readUserEvent = userService.requestUser(new RequestReadUserEvent(searchUserCriteria));
		ReadUserEvent readUserEventSession = userService.requestUser(new RequestReadUserEvent(searchUserCriteriaSession));
		userSearchResults = readUserEvent.getUserEntityList();
		SecUserEntity userSession = readUserEventSession.getUserEntityList().get(0);
		if (!userSearchResults.contains(userSession)){
			userSearchResults.add(userSession);	
		}
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
			ReadGroupsEvent readUserGroupsEvent = userService.requestGroupShow(new RequestReadGroupsEvent(selectedUserName, false));
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
