package org.abacus.definition.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.abacus.common.web.JsfMessageHelper;
import org.abacus.definition.core.handler.DefTypeHandler;
import org.abacus.definition.core.handler.DefValueHandler;
import org.abacus.definition.shared.constant.DefConstant;
import org.abacus.definition.shared.entity.DefTypeEntity;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class DefinitionViewBean implements Serializable {

	private DefTypeEntity selType;
	private List<DefTypeEntity> typeList;

	@ManagedProperty(value = "#{defTypeHandler}")
	private DefTypeHandler defTypeService;

	@ManagedProperty(value = "#{defValueHandler}")
	private DefValueHandler defValService;

	@ManagedProperty(value = "#{jsfMessageHelper}")
	private JsfMessageHelper jsfMessageHelper;

	@ManagedProperty(value = "#{defValueViewBean}")
	private DefValueViewBean defValueViewBean;

	@ManagedProperty(value = "#{defParamViewBean}")
	private DefParamViewBean defParamViewBean;

	private DefConstant.GroupEnum[] groupEnums;
	private DefConstant.GroupEnum selectedGroupEnum;
	
	public boolean renderGroupP;
	public boolean renderGroupS;
	public boolean renderGroupT;
	public boolean renderGroupV;

	@PostConstruct
	public void init() {
		groupEnums = DefConstant.GroupEnum.values();
		try{
			String value = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("type");
			selectedGroupEnum =  DefConstant.GroupEnum.valueOf(value.toUpperCase());
		}catch(Exception e){
			selectedGroupEnum = groupEnums[0];
		}
		groupChangeListener();
	}
	
	public void groupChangeListener(){
		this.findTypeList(selectedGroupEnum);
		selType=null;
		renderGroupP = selectedGroupEnum.equals(DefConstant.GroupEnum.P);//Param:--Static: working
		renderGroupS = selectedGroupEnum.equals(DefConstant.GroupEnum.S);//State:--Static: --
		renderGroupT = selectedGroupEnum.equals(DefConstant.GroupEnum.T);//Task :--Dynamc: --
		renderGroupV = selectedGroupEnum.equals(DefConstant.GroupEnum.V);//Value:--Dynamc: OK
		
		defValueViewBean.setSelType(null);
		defParamViewBean.setSelType(null);
	}

	public void typeRowSelectListener() {
		defValueViewBean.setSelType(selType);
		defParamViewBean.setSelType(selType);
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

	public void addNewType() {
		boolean found = false;
		for (DefTypeEntity def : typeList) {
			if (def.isNew()) {
				selType = def;
				found = true;
				break;
			}
		}
		if (found) {
			typeRowSelectListener();
		} else {
			clearType();
		}

	}

	public void clearType() {
		selType = new DefTypeEntity();
		if (typeList != null) {
			typeList.add(0, selType);
		}
		defValueViewBean.setSelType(null);
		defParamViewBean.setSelType(null);
	}

	public void findTypeList(DefConstant.GroupEnum groupEnum) {
		selType = null;
		typeList = null;
		typeList = defTypeService.getTypeList(groupEnum.name());
	}

	public DefTypeEntity getSelType() {
		return selType;
	}
	
	public void setSelType(DefTypeEntity selType) {
		this.selType = selType;
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

	public DefValueHandler getDefValService() {
		return defValService;
	}

	public void setDefValService(DefValueHandler defValService) {
		this.defValService = defValService;
	}

	public JsfMessageHelper getJsfMessageHelper() {
		return jsfMessageHelper;
	}

	public void setJsfMessageHelper(JsfMessageHelper jsfMessageHelper) {
		this.jsfMessageHelper = jsfMessageHelper;
	}

	public DefConstant.GroupEnum[] getGroupEnums() {
		return groupEnums;
	}

	public void setGroupEnums(DefConstant.GroupEnum[] groupEnums) {
		this.groupEnums = groupEnums;
	}

	public DefConstant.GroupEnum getSelectedGroupEnum() {
		return selectedGroupEnum;
	}

	public void setSelectedGroupEnum(DefConstant.GroupEnum selectedGroupEnum) {
		this.selectedGroupEnum = selectedGroupEnum;
	}

	public boolean isRenderGroupP() {
		return renderGroupP;
	}

	public boolean isRenderGroupS() {
		return renderGroupS;
	}

	public boolean isRenderGroupT() {
		return renderGroupT;
	}

	public boolean isRenderGroupV() {
		return renderGroupV;
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

}
