package org.abacus.user.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.abacus.common.web.JsfMessageHelper;
import org.abacus.user.core.handler.SecGroupHandler;
import org.abacus.user.shared.UserExistsInGroupException;
import org.abacus.user.shared.entity.SecAuthorityEntity;
import org.abacus.user.shared.entity.SecGroupEntity;
import org.abacus.user.web.model.DynamicEntityDataModel;
import org.primefaces.model.DualListModel;

@ManagedBean
@ViewScoped
public class UserGroupViewBean implements Serializable{

	@ManagedProperty(value = "#{secGroupHandler}")
	private SecGroupHandler secGroupHandler;

	@ManagedProperty(value = "#{jsfMessageHelper}")
	private JsfMessageHelper jsfMessageHelper;

	private SecGroupEntity selectedGroup;

	private DualListModel<SecAuthorityEntity> selectedGroupAuthoritiesDL;

	@PostConstruct
	public void init() {
		this.newSelectedGroup();
	}

	public void newGroup() {
		this.newSelectedGroup();
	}

	private void newSelectedGroup() {
		selectedGroupAuthoritiesDL = new DualListModel<SecAuthorityEntity>();
		List<SecAuthorityEntity> allAuthorities = secGroupHandler
				.allAuthorities();
		List<SecAuthorityEntity> targetSelectedGrupAuthorities = new ArrayList<>();
		selectedGroupAuthoritiesDL.setSource(allAuthorities);
		selectedGroupAuthoritiesDL.setTarget(targetSelectedGrupAuthorities);
		selectedGroup = null;
	}

	public void removeGroup(){

		try {
			secGroupHandler.removeGroup(selectedGroup.getId());
			jsfMessageHelper.addInfo("kullaniciGrubuSilindi");
			this.newSelectedGroup();
		} catch (UserExistsInGroupException e) {
			jsfMessageHelper.addError("grubaAitKullanicilarBulundu");
		}

	}

	public void onGroupRowSelected() {
		Long groupId = selectedGroup.getId();
		selectedGroupAuthoritiesDL = new DualListModel<>();
		List<SecAuthorityEntity> target = secGroupHandler
				.findGroupAuthorities(groupId);
		List<SecAuthorityEntity> source = secGroupHandler.allAuthorities();
		source.removeAll(target);
		selectedGroupAuthoritiesDL.setSource(source);
		selectedGroupAuthoritiesDL.setTarget(target);
	}

	public DynamicEntityDataModel<SecGroupEntity> findAllGroups() {
		List<SecGroupEntity> groupList = secGroupHandler.allGroups();
		DynamicEntityDataModel<SecGroupEntity> dataModel = new DynamicEntityDataModel<SecGroupEntity>(
				groupList);
		return dataModel;
	}

	public SecGroupHandler getSecGroupHandler() {
		return secGroupHandler;
	}

	public void setSecGroupHandler(SecGroupHandler secGroupHandler) {
		this.secGroupHandler = secGroupHandler;
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

	public void setSelectedGroupAuthoritiesDL(
			DualListModel<SecAuthorityEntity> selectedGroupAuthoritiesDL) {
		this.selectedGroupAuthoritiesDL = selectedGroupAuthoritiesDL;
	}

	public JsfMessageHelper getJsfMessageHelper() {
		return jsfMessageHelper;
	}

	public void setJsfMessageHelper(JsfMessageHelper jsfMessageHelper) {
		this.jsfMessageHelper = jsfMessageHelper;
	}

}
