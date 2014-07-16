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
import org.abacus.organization.core.handler.OrganizationHandler;
import org.abacus.organization.shared.entity.OrganizationEntity;

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

	@ManagedProperty(value = "#{organizationHandler}")
	private OrganizationHandler organizationHandler;

	private OrganizationEntity rootOrganization;
	
	@PostConstruct
	public void init() {
		rootOrganization = sessionInfoHelper.currentOrganization().getRootOrganization();
		findUnitGroupList();
	}
	
	public void groupChangeListener(){
		clearUnitGroup();
	}

	public void unitGroupRowSelectListener() {
		unitCodeViewBean.setSelUnitGroup(selUnitGroup);
	}

	public void saveUnitGroup() {
		if (selUnitGroup.isNew()) {
			jsfMessageHelper.addInfo("createSuccessful","Birim Grubu");
		} else {
			jsfMessageHelper.addInfo("updateSuccessful","Birim Grubu");
		}
		selUnitGroup = defUnitHandler.saveUnitGroupEntity(selUnitGroup);
		findUnitGroupList();
	}

	public void deleteUnitGroup() {
		if (!selUnitGroup.isNew()) {
			defUnitHandler.deleteUnitGroupEntity(selUnitGroup);
			jsfMessageHelper.addInfo("deleteSuccessful","Birim Grubu");
		}
		findUnitGroupList();
	}

	public void clearUnitGroup() {
		selUnitGroup = new DefUnitGroupEntity();
		selUnitGroup.setOrganization(rootOrganization);
	}

	public void findUnitGroupList() {
		clearUnitGroup();
		unitGroupList = null;
		unitGroupList = defUnitHandler.getUnitGroupList(rootOrganization.getId());
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

	public OrganizationEntity getRootOrganization() {
		return rootOrganization;
	}

	public void setRootOrganization(OrganizationEntity rootOrganization) {
		this.rootOrganization = rootOrganization;
	}

	public OrganizationHandler getOrganizationHandler() {
		return organizationHandler;
	}

	public void setOrganizationHandler(OrganizationHandler organizationHandler) {
		this.organizationHandler = organizationHandler;
	}
	
	


}
