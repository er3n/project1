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

	@ManagedProperty(value = "#{defTaskViewBean}")
	private DefTaskViewBean defTaskViewBean;

	private EnumList.DefTypeGroupEnum selectedGroupEnum;

	@PostConstruct
	public void init() {
		try{
			String grp = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("grp");
			selectedGroupEnum = EnumList.DefTypeGroupEnum.valueOf(grp.toUpperCase());
		}catch(Exception e){
			selectedGroupEnum = EnumList.DefTypeGroupEnum.ITM;
		}
		groupChangeListener();
	}
	
	public void groupChangeListener(){
		this.findTypeList(selectedGroupEnum);
		clearType();
	}

	public void typeRowSelectListener() {
		if (selectedGroupEnum.equals(EnumList.DefTypeGroupEnum.VAL)){
			defValueViewBean.setSelType(selType);
		}
		if (selectedGroupEnum.equals(EnumList.DefTypeGroupEnum.PRM)){
			defParamViewBean.setSelType(selType);
		}
		if (selectedGroupEnum.equals(EnumList.DefTypeGroupEnum.STK) || 
				selectedGroupEnum.equals(EnumList.DefTypeGroupEnum.FIN)){
			defTaskViewBean.setSelType(selType);
		}
	}
	
	public void clearType() {
		selType = new DefTypeEntity();
		if (selectedGroupEnum.equals(EnumList.DefTypeGroupEnum.VAL)){
			defValueViewBean.setSelType(null);
		}
		if (selectedGroupEnum.equals(EnumList.DefTypeGroupEnum.PRM)){
			defParamViewBean.setSelType(null);
		}
		if (selectedGroupEnum.equals(EnumList.DefTypeGroupEnum.STK) || 
				selectedGroupEnum.equals(EnumList.DefTypeGroupEnum.FIN)){
			defTaskViewBean.setSelType(null);
		}
	}

	public void saveType() {
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

	public void findTypeList(EnumList.DefTypeGroupEnum groupEnum) {
		clearType();
		typeList = null;
		typeList = defTypeService.getTypeList(groupEnum);
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

	public EnumList.DefTypeGroupEnum getSelectedGroupEnum() {
		return selectedGroupEnum;
	}

	public void setSelectedGroupEnum(EnumList.DefTypeGroupEnum selectedGroupEnum) {
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
