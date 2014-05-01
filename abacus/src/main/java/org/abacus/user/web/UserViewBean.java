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
import org.abacus.user.shared.event.ReadCompanisEvent;
import org.abacus.user.shared.event.ReadGroupsEvent;
import org.abacus.user.shared.event.ReadUserEvent;
import org.abacus.user.shared.event.RequestReadCompaniesEvent;
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

	private List<CompanyEntity> allCompanies;

	private DualListModel<SecGroupEntity> selectedGroupMemberDL;

	private DualListModel<CompanyEntity> selectedUserCompaniesDL;

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

		ReadCompanisEvent allCompaniesEvent = userService
				.requestCompany(new RequestReadCompaniesEvent(null,
						sessionInfoHelper.currentCompany()));
		allCompanies = allCompaniesEvent.getCompanyList();

	}

	public void createUser() {
		String currentUser = sessionInfoHelper.currentUserName();
		List<SecGroupEntity> selectedGroups = selectedGroupMemberDL.getTarget();
		List<CompanyEntity> userCompanies = selectedUserCompaniesDL.getTarget();
		try {
			UserCreatedEvent createdEvent = userService
					.createUser(new CreateUserEvent(selectedUser,
							selectedGroups, userCompanies, currentUser));
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
		List<CompanyEntity> userCompanies = selectedUserCompaniesDL.getTarget();
		UserUpdatedEvent updatedEvent = userService
				.updateUser(new UpdateUserEvent(selectedUser, selectedGroups,
						userCompanies, currentUser));
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
		if (searchUserCriteria.getCompany() == null || !StringUtils.hasText(searchUserCriteria.getCompany().getId())) {
			String company = sessionInfoHelper.currentCompany();
			CompanyEntity companyEntity = new CompanyEntity();
			companyEntity.setId(company);
			searchUserCriteria.setCompany(companyEntity);
		}
		ReadUserEvent readUserEvent = userService
				.requestUser(new RequestReadUserEvent(searchUserCriteria));
		userSearchResults = readUserEvent.getUserEntityList();
	}

	public DualListModel<CompanyEntity> selectedUserCompanies() {

		selectedUserCompaniesDL = new DualListModel<CompanyEntity>();

		String selectedUserName = selectedUser.getId();

		List<CompanyEntity> targetUserCompanies = new ArrayList<>();
		List<CompanyEntity> sourceUserCompanies = new ArrayList<>();

		if (StringUtils.hasText(selectedUserName)) {
			ReadCompanisEvent userCompaniesEvent = userService
					.requestCompany(new RequestReadCompaniesEvent(
							selectedUserName, null));
			targetUserCompanies = userCompaniesEvent.getCompanyList();
			for (CompanyEntity companyEntity : allCompanies) {
				if (!targetUserCompanies.contains(companyEntity)) {
					sourceUserCompanies.add(companyEntity);
				}
			}
		} else {
			sourceUserCompanies = allCompanies;
		}

		selectedUserCompaniesDL.setSource(sourceUserCompanies);
		selectedUserCompaniesDL.setTarget(targetUserCompanies);

		return selectedUserCompaniesDL;
	}

	public DualListModel<SecGroupEntity> selectedUserGroups() {

		selectedGroupMemberDL = new DualListModel<SecGroupEntity>();

		String selectedUserName = selectedUser.getId();

		List<SecGroupEntity> targetUserGroups = new ArrayList<>();
		List<SecGroupEntity> sourceUserGroups = new ArrayList<>();

		if (StringUtils.hasText(selectedUserName)) {
			ReadGroupsEvent readUserGroupsEvent = userService
					.requestGroup(new RequestReadGroupsEvent(selectedUserName));
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

	public void setSelectedGroupMemberDL(
			DualListModel<SecGroupEntity> selectedGroupMemberDL) {
		this.selectedGroupMemberDL = selectedGroupMemberDL;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public DualListModel<CompanyEntity> getSelectedUserCompaniesDL() {
		this.selectedUserCompaniesDL = this.selectedUserCompanies();
		return selectedUserCompaniesDL;
	}

	public void setSelectedUserCompaniesDL(
			DualListModel<CompanyEntity> selectedUserCompaniesDL) {
		this.selectedUserCompaniesDL = selectedUserCompaniesDL;
	}

	public List<CompanyEntity> getAllCompanies() {
		return allCompanies;
	}

	public void setAllCompanies(List<CompanyEntity> allCompanies) {
		this.allCompanies = allCompanies;
	}

}
