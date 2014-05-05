package org.abacus.definition.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.abacus.common.web.JsfMessageHelper;
import org.abacus.common.web.SessionInfoHelper;
import org.abacus.definition.core.handler.DefUnitHandler;
import org.abacus.definition.shared.entity.DefUnitGroupEntity;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class UnitViewBean implements Serializable {

	private DefUnitGroupEntity selUnitGroup;
	private List<DefUnitGroupEntity> unitGroupList;

	@ManagedProperty(value = "#{defUnitHandler}")
	private DefUnitHandler defUnitHandler;

	@ManagedProperty(value = "#{sessionInfoHelper}")
	private SessionInfoHelper sessionInfoHelper;

	@ManagedProperty(value = "#{jsfMessageHelper}")
	private JsfMessageHelper jsfMessageHelper;
	
	@ManagedProperty(value = "#{unitCodeViewBean}")
	private UnitCodeViewBean unitCodeViewBean;

	private DefUnitGroupEntity rootUnitGroup;
	
	@PostConstruct
	public void init() {
//		rootUnitGroup = organizationHandler.findUnitGroupWithLevel(sessionInfoHelper.currentUser().getSelectedOrganization(), EnumList.OrgOrganizationLevelEnum.L0);
	}
	
	public void groupChangeListener(){
		clearUnitGroup();
	}

	public void unitGroupRowSelectListener() {
	}

	public void saveOrUpdateUnitGroup() {
		if (selUnitGroup.isNew()) {
			jsfMessageHelper.addInfo("organizationKayitIslemiBasarili");
		} else {
			jsfMessageHelper.addInfo("organizationGuncellemeIslemiBasarili");
		}
//		selUnitGroup = organizationHandler.saveUnitGroupEntity(selUnitGroup);
		findUnitGroupList();
	}

	public void deleteUnitGroup() {
		if (!selUnitGroup.isNew()) {
//			organizationHandler.deleteUnitGroupEntity(selUnitGroup);
			jsfMessageHelper.addInfo("organizationSilmeIslemiBasarili");
		}
		findUnitGroupList();
	}

	public void clearUnitGroup() {
		selUnitGroup = new DefUnitGroupEntity();
//		orgDepartmentViewBean.setSelUnitGroup(null);
	}

	public void findUnitGroupList() {
		clearUnitGroup();
		unitGroupList = null;
//		unitGroupList = organizationHandler.findByUnitGroup(sessionInfoHelper.currentUnitGroupId());
		System.out.println(unitGroupList);
	}

	public SessionInfoHelper getSessionInfoHelper() {
		return sessionInfoHelper;
	}

	public void setSessionInfoHelper(SessionInfoHelper sessionInfoHelper) {
		this.sessionInfoHelper = sessionInfoHelper;
	}

	public JsfMessageHelper getJsfMessageHelper() {
		return jsfMessageHelper;
	}

	public void setJsfMessageHelper(JsfMessageHelper jsfMessageHelper) {
		this.jsfMessageHelper = jsfMessageHelper;
	}

	public DefUnitGroupEntity getSelUnitGroup() {
		return selUnitGroup;
	}

	public void setSelUnitGroup(DefUnitGroupEntity selUnitGroup) {
		if (selUnitGroup!=null){
			this.selUnitGroup = selUnitGroup;
		}
	}

	public List<DefUnitGroupEntity> getUnitGroupList() {
		return unitGroupList;
	}

	public void setUnitGroupList(List<DefUnitGroupEntity> organizationList) {
		this.unitGroupList = organizationList;
	}

	public UnitCodeViewBean getUnitCodeViewBean() {
		return unitCodeViewBean;
	}

	public void setUnitCodeViewBean(UnitCodeViewBean unitCodeViewBean) {
		this.unitCodeViewBean = unitCodeViewBean;
	}

	public DefUnitHandler getDefUnitHandler() {
		return defUnitHandler;
	}

	public void setDefUnitHandler(DefUnitHandler defUnitHandler) {
		this.defUnitHandler = defUnitHandler;
	}

	public DefUnitGroupEntity getRootUnitGroup() {
		return rootUnitGroup;
	}

	public void setRootUnitGroup(DefUnitGroupEntity rootUnitGroup) {
		this.rootUnitGroup = rootUnitGroup;
	}


}
