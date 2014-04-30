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
import org.abacus.user.core.handler.SecGroupHandler;
import org.abacus.user.core.handler.SecUserHandler;
import org.abacus.user.shared.UserNameExistsException;
import org.abacus.user.shared.entity.CompanyEntity;
import org.abacus.user.shared.entity.SecGroupEntity;
import org.abacus.user.shared.entity.SecUserEntity;
import org.abacus.user.shared.holder.SearchUserCriteria;
import org.primefaces.model.DualListModel;
import org.springframework.util.StringUtils;

@ManagedBean
@ViewScoped
public class UserViewBean implements Serializable {

	@ManagedProperty(value = "#{secUserHandler}")
	private SecUserHandler secUserHandler;

	@ManagedProperty(value = "#{secGroupHandler}")
	private SecGroupHandler secGroupHandler;

	@ManagedProperty(value = "#{jsfMessageHelper}")
	private JsfMessageHelper jsfMessageHelper;

	@ManagedProperty(value = "#{sessionInfoHelper}")
	private SessionInfoHelper sessionInfoHelper;

	private SearchUserCriteria searchUserCriteria;

	private List<SecUserEntity> userSearchResults;

	private SecUserEntity selectedUser;

	private List<SecGroupEntity> allGroups;

	private DualListModel<SecGroupEntity> selectedGroupMemberDL;

	@PostConstruct
	public void init() {
		searchUserCriteria = new SearchUserCriteria();
		selectedUser = new SecUserEntity();
		userSearchResults = null;
		this.clear();
		allGroups = secGroupHandler.allGroups();
	}
	
	public void createUser(){
		String company = sessionInfoHelper.currentCompany();
		String currentUser = sessionInfoHelper.currentUserName();
		CompanyEntity companyEntity = new CompanyEntity();
		companyEntity.setId(company);
		selectedUser.setCompanyEntity(companyEntity);
		List<SecGroupEntity> selectedGroups = selectedGroupMemberDL.getTarget();
		try {
			secUserHandler.createUser(selectedUser,selectedGroups,currentUser);
			jsfMessageHelper.addInfo("kullaniciEklendi");
		} catch (UserNameExistsException e) {
			jsfMessageHelper.addError("kullaniciAdiKullanimda");
		}
	}
	
	public void updateUser(){
		String currentUser = sessionInfoHelper.currentUserName();
		List<SecGroupEntity> selectedGroups = selectedGroupMemberDL.getTarget();
		secUserHandler.updateUser(selectedUser,selectedGroups,currentUser);
		jsfMessageHelper.addInfo("kullaniciGuncellendi");
	}
	
	public void pacifyUser(){
		String currentUser = sessionInfoHelper.currentUserName();
		secUserHandler.makePassiveUser(selectedUser,currentUser);
		jsfMessageHelper.addInfo("kullaniciPasiflestirildi");
	}

	public void clear() {
		selectedUser = new SecUserEntity();
	}

	public void findUser() {
		String company = sessionInfoHelper.currentCompany();
		searchUserCriteria.setCompany(company);
		userSearchResults = secUserHandler.findUser(searchUserCriteria);
	}

	public DualListModel<SecGroupEntity> selectedUserGroups() {
		
		selectedGroupMemberDL = new DualListModel<SecGroupEntity>();
		
		String selectedUserName = selectedUser.getId();
		
		List<SecGroupEntity> targetUserGroups =new ArrayList<>();
		List<SecGroupEntity> sourceUserGroups = new ArrayList<>();
		
		if(StringUtils.hasText(selectedUserName)){
			targetUserGroups = secUserHandler
					.findUserGroups(selectedUserName);
			
			for (SecGroupEntity groupEntity : allGroups) {
				if (!targetUserGroups.contains(groupEntity)) {
					sourceUserGroups.add(groupEntity);
				}
			}
		}else{
			sourceUserGroups = allGroups;
			
		}
		
		selectedGroupMemberDL.setSource(sourceUserGroups);
		selectedGroupMemberDL.setTarget(targetUserGroups);
		return selectedGroupMemberDL;
	}

	public SecUserHandler getSecUserHandler() {
		return secUserHandler;
	}

	public void setSecUserHandler(SecUserHandler secUserHandler) {
		this.secUserHandler = secUserHandler;
	}

	public SecGroupHandler getSecGroupHandler() {
		return secGroupHandler;
	}

	public void setSecGroupHandler(SecGroupHandler secGroupHandler) {
		this.secGroupHandler = secGroupHandler;
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
		return selectedGroupMemberDL;
	}

	public void setSelectedGroupMemberDL(
			DualListModel<SecGroupEntity> selectedGroupMemberDL) {
		this.selectedGroupMemberDL = selectedGroupMemberDL;
	}

}
