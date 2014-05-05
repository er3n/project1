package org.abacus.definition.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.abacus.common.web.JsfMessageHelper;
import org.abacus.common.web.SessionInfoHelper;
import org.abacus.definition.core.handler.DefTypeHandler;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.constant.SelectionEnum;
import org.abacus.definition.shared.entity.DefTypeEntity;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class DefinitionViewBean implements Serializable {

	private DefTypeEntity selType;
	private List<DefTypeEntity> typeList;
	
	@ManagedProperty(value = "#{sessionInfoHelper}")
	private SessionInfoHelper sessionInfoHelper;

	@ManagedProperty(value = "#{jsfMessageHelper}")
	private JsfMessageHelper jsfMessageHelper;
	
	@ManagedProperty(value = "#{defTypeHandler}")
	private DefTypeHandler defTypeService;

	@ManagedProperty(value = "#{defValueViewBean}")
	private DefValueViewBean defValueViewBean;

	@ManagedProperty(value = "#{defParamViewBean}")
	private DefParamViewBean defParamViewBean;

	@ManagedProperty(value = "#{defStateViewBean}")
	private DefStateViewBean defStateViewBean;

	@ManagedProperty(value = "#{defTaskViewBean}")
	private DefTaskViewBean defTaskViewBean;

	private SelectionEnum[] groupEnums;
	private SelectionEnum selectedGroupEnum;

	@PostConstruct
	public void init() {
		System.out.println("ViewBean Session User:"+sessionInfoHelper.currentUserName());
		System.out.println("ViewBean Session Comp:"+sessionInfoHelper.currentOrganizationId());
		
		createGrupEnumArray();
		try{
			String value = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("type");
			selectedGroupEnum = new SelectionEnum(EnumList.DefTypeGroupEnum.valueOf(value.toUpperCase()));
		}catch(Exception e){
			selectedGroupEnum = groupEnums[0];
		}
		groupChangeListener();
	}
	
	private void createGrupEnumArray(){
		groupEnums = new SelectionEnum[EnumList.DefTypeGroupEnum.values().length];
		for (EnumList.DefTypeGroupEnum enm : EnumList.DefTypeGroupEnum.values()) {
			groupEnums[enm.ordinal()] = new SelectionEnum(enm);
		}
	}
	
	public void groupChangeListener(){
		this.findTypeList(selectedGroupEnum);
		clearType();
	}

	public void typeRowSelectListener() {
		defValueViewBean.setSelType(selType);
		defParamViewBean.setSelType(selType);
		defStateViewBean.setSelType(selType);
		defTaskViewBean.setSelType(selType);
	}

	public void saveOrUpdateType() {
		if (selType.isNew()) {
			jsfMessageHelper.addInfo("typeKayitIslemiBasarili");
		} else {
			jsfMessageHelper.addInfo("typeGuncellemeIslemiBasarili");
		}
		selType = defTypeService.saveTypeEntity(selType);
		findTypeList(selectedGroupEnum);
	}

	public void deleteType() {
		if (!selType.isNew()) {
			defTypeService.deleteTypeEntity(selType);
			jsfMessageHelper.addInfo("typeSilmeIslemiBasarili");
		}
		findTypeList(selectedGroupEnum);
	}

	public void clearType() {
		selType = new DefTypeEntity();
		selType.setTransientGroup(selectedGroupEnum);
		defValueViewBean.setSelType(null);
		defParamViewBean.setSelType(null);
		defStateViewBean.setSelType(null);
		defTaskViewBean.setSelType(null);
	}

	public void findTypeList(SelectionEnum groupEnum) {
		clearType();
		typeList = null;
		typeList = defTypeService.getTypeList(EnumList.DefTypeGroupEnum.valueOf(groupEnum.name()));
	}

	public DefTypeEntity getSelType() {
		return selType;
	}
	
	public void setSelType(DefTypeEntity selType) {
		if (selType!=null){
			this.selType = selType;
		}
	}
	
	public List<DefTypeEntity> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<DefTypeEntity> typeList) {
		this.typeList = typeList;
	}

	public DefTypeHandler getDefTypeService() {
		return defTypeService;
	}

	public void setDefTypeService(DefTypeHandler defTypeService) {
		this.defTypeService = defTypeService;
	}

	public JsfMessageHelper getJsfMessageHelper() {
		return jsfMessageHelper;
	}

	public void setJsfMessageHelper(JsfMessageHelper jsfMessageHelper) {
		this.jsfMessageHelper = jsfMessageHelper;
	}

	public SelectionEnum[] getGroupEnums() {
		return groupEnums;
	}

	public void setGroupEnums(SelectionEnum[] groupEnums) {
		this.groupEnums = groupEnums;
	}

	public SelectionEnum getSelectedGroupEnum() {
		return selectedGroupEnum;
	}

	public void setSelectedGroupEnum(SelectionEnum selectedGroupEnum) {
		this.selectedGroupEnum = selectedGroupEnum;
	}

	public DefParamViewBean getDefParamViewBean() {
		return defParamViewBean;
	}

	public void setDefParamViewBean(DefParamViewBean defParamViewBean) {
		this.defParamViewBean = defParamViewBean;
	}

	public DefValueViewBean getDefValueViewBean() {
		return defValueViewBean;
	}

	public void setDefValueViewBean(DefValueViewBean defValueViewBean) {
		this.defValueViewBean = defValueViewBean;
	}

	public DefStateViewBean getDefStateViewBean() {
		return defStateViewBean;
	}

	public void setDefStateViewBean(DefStateViewBean defStateViewBean) {
		this.defStateViewBean = defStateViewBean;
	}

	public DefTaskViewBean getDefTaskViewBean() {
		return defTaskViewBean;
	}

	public void setDefTaskViewBean(DefTaskViewBean defTaskViewBean) {
		this.defTaskViewBean = defTaskViewBean;
	}

	public SessionInfoHelper getSessionInfoHelper() {
		return sessionInfoHelper;
	}

	public void setSessionInfoHelper(SessionInfoHelper sessionInfoHelper) {
		this.sessionInfoHelper = sessionInfoHelper;
	}

}
