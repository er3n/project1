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
import org.abacus.organization.shared.entity.OrganizationEntity;
import org.abacus.user.core.handler.UserService;
import org.abacus.user.shared.GroupNameInUseException;
import org.abacus.user.shared.UserExistsInGroupException;
import org.abacus.user.shared.entity.SecAuthorityEntity;
import org.abacus.user.shared.entity.SecGroupEntity;
import org.abacus.user.shared.event.CreateGroupEvent;
import org.abacus.user.shared.event.DeleteGroupEvent;
import org.abacus.user.shared.event.GroupCreatedEvent;
import org.abacus.user.shared.event.GroupDeletedEvent;
import org.abacus.user.shared.event.GroupUpdatedEvent;
import org.abacus.user.shared.event.ReadAuthoritiesEvent;
import org.abacus.user.shared.event.ReadGroupsEvent;
import org.abacus.user.shared.event.RequestReadAuthoritiesEvent;
import org.abacus.user.shared.event.RequestReadGroupsEvent;
import org.abacus.user.shared.event.UpdateGroupEvent;
import org.abacus.user.web.model.DynamicEntityDataModel;
import org.primefaces.model.DualListModel;

@ManagedBean
@ViewScoped
public class UserGroupViewBean implements Serializable {

	@ManagedProperty(value = "#{jsfMessageHelper}")
	private JsfMessageHelper jsfMessageHelper;

	@ManagedProperty(value = "#{sessionInfoHelper}")
	private SessionInfoHelper sessionInfoHelper;

	private SecGroupEntity selectedGroup;

	private DualListModel<SecAuthorityEntity> selectedGroupAuthoritiesDL;

	@ManagedProperty(value = "#{userEventHandler}")
	private UserService userService;

	@PostConstruct
	public void init() {
		this.newSelectedGroup();
	}

	public void newGroup() {
		this.newSelectedGroup();
	}

	private void newSelectedGroup() {
		selectedGroupAuthoritiesDL = new DualListModel<SecAuthorityEntity>();

		ReadAuthoritiesEvent authoritiesEvent = userService.requestAuthorities(new RequestReadAuthoritiesEvent());
		List<SecAuthorityEntity> allAuthorities = authoritiesEvent.getGroupAuthorities();
		List<SecAuthorityEntity> targetSelectedGrupAuthorities = new ArrayList<>();
		selectedGroupAuthoritiesDL.setSource(allAuthorities);
		selectedGroupAuthoritiesDL.setTarget(targetSelectedGrupAuthorities);
		selectedGroup = new SecGroupEntity();
	}

	public void saveGroup() {
		try {
			List<SecAuthorityEntity> selectedAuthorities = selectedGroupAuthoritiesDL.getTarget();
			String username = sessionInfoHelper.currentUserName();

			GroupCreatedEvent groupCreatedEvent = userService.createGroup(new CreateGroupEvent(selectedGroup, selectedAuthorities, username));
			jsfMessageHelper.addInfo("grupEklendi");
		} catch (GroupNameInUseException e) {
			jsfMessageHelper.addError("grupIsmiKullanimda");
		}
	}

	public void updateGroup() {
		try {
			List<SecAuthorityEntity> selectedAuthorities = selectedGroupAuthoritiesDL.getTarget();
			String userName = sessionInfoHelper.currentUserName();
			GroupUpdatedEvent groupUpdatedEvent = userService.updateGroup(new UpdateGroupEvent(selectedGroup, selectedAuthorities, userName));
			selectedGroup = groupUpdatedEvent.getGroup();
			jsfMessageHelper.addInfo("grupGuncellendi");
		} catch (GroupNameInUseException e) {
			jsfMessageHelper.addError("groupIsmiKullanimda");
		}
	}

	public void removeGroup() {

		try {
			GroupDeletedEvent event = userService.deleteGroup(new DeleteGroupEvent(selectedGroup.getId()));
			jsfMessageHelper.addInfo("kullaniciGrubuSilindi");
			this.newSelectedGroup();
		} catch (UserExistsInGroupException e) {
			jsfMessageHelper.addError("grubaAitKullanicilarBulundu");
		}

	}

	public void onGroupRowSelected() {
		Long groupId = selectedGroup.getId();
		selectedGroupAuthoritiesDL = new DualListModel<>();
		ReadAuthoritiesEvent groupAuthoritiesEvent = userService.requestAuthorities(new RequestReadAuthoritiesEvent(groupId));
		List<SecAuthorityEntity> target = groupAuthoritiesEvent.getGroupAuthorities();
		ReadAuthoritiesEvent allGroupAuthoritiesEvent = userService.requestAuthorities(new RequestReadAuthoritiesEvent());
		List<SecAuthorityEntity> source = allGroupAuthoritiesEvent.getGroupAuthorities();
		source.removeAll(target);
		selectedGroupAuthoritiesDL.setSource(source);
		selectedGroupAuthoritiesDL.setTarget(target);
	}

	public DynamicEntityDataModel<SecGroupEntity> findAllGroups() {
		ReadGroupsEvent allGroups = userService.requestGroup(new RequestReadGroupsEvent());
		List<SecGroupEntity> groupList = allGroups.getGroupList();
		DynamicEntityDataModel<SecGroupEntity> dataModel = new DynamicEntityDataModel<SecGroupEntity>(groupList);
		return dataModel;
	}

	public List<SecAuthorityEntity> allAuthorities() {
		List<SecAuthorityEntity> all = new ArrayList<>();
		all.addAll(selectedGroupAuthoritiesDL.getSource());
		all.addAll(selectedGroupAuthoritiesDL.getTarget());
		return all;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public SecGroupEntity getSelectedGroup() {
		return selectedGroup;
	}

	public void setSelectedGroup(SecGroupEntity selectedGroup) {
		this.selectedGroup = selectedGroup;
	}

	public DualListModel<SecAuthorityEntity> getSelectedGroupAuthoritiesDL() {
		return selectedGroupAuthoritiesDL;
	}

	public void setSelectedGroupAuthoritiesDL(DualListModel<SecAuthorityEntity> selectedGroupAuthoritiesDL) {
		this.selectedGroupAuthoritiesDL = selectedGroupAuthoritiesDL;
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

}
