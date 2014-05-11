package org.abacus.definition.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.abacus.common.web.JsfMessageHelper;
import org.abacus.definition.core.handler.DefParamHandler;
import org.abacus.definition.shared.entity.DefParamEntity;
import org.abacus.definition.shared.entity.DefTypeEntity;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class DefParamViewBean implements Serializable {

	@ManagedProperty(value = "#{jsfMessageHelper}")
	private JsfMessageHelper jsfMessageHelper;
	
	@ManagedProperty(value = "#{defParamHandler}")
	private DefParamHandler defParamService;

	private DefTypeEntity selType;

	private DefParamEntity selParam;
	private List<DefParamEntity> paramList;

	@ManagedProperty(value = "#{defParamAnswerViewBean}")
	private DefParamAnswerViewBean defParamAnswerViewBean;

	@PostConstruct
	public void init() {
	}
	
	public void setSelType(DefTypeEntity selType) {
		this.selType = selType;
		findTypeParam();
	}

	public void paramRowSelectListener() {
		defParamAnswerViewBean.setSelParam(selParam);
	}

	
	public void saveParam() {
		if (selParam.isNew()) {
			jsfMessageHelper.addInfo("paramKayitIslemiBasarili");
		} else {
			jsfMessageHelper.addInfo("paramGuncellemeIslemiBasarili");
		}
		defParamService.saveParamEntity(selParam);
		findTypeParam();
	}

	public void deleteParam() {
		if (!selParam.isNew()) {
			defParamService.deleteParamEntity(selParam);
			jsfMessageHelper.addInfo("paramSilmeIslemiBasarili");
		}
		findTypeParam();
	}

	public void createParam() {
		selParam = new DefParamEntity();
		selParam.setType(selType);
		
		defParamAnswerViewBean.setSelParam(null);
	}

	public void findTypeParam() {
		createParam();
		paramList = null;
		if (selType!=null){
			paramList = defParamService.getParamList(selType.getTypeEnum());
		} else {
			paramList = new ArrayList<DefParamEntity>();
		}
	}
	
	public JsfMessageHelper getJsfMessageHelper() {
		return jsfMessageHelper;
	}

	public void setJsfMessageHelper(JsfMessageHelper jsfMessageHelper) {
		this.jsfMessageHelper = jsfMessageHelper;
	}

	public DefParamHandler getDefParamService() {
		return defParamService;
	}

	public void setDefParamService(DefParamHandler defParamService) {
		this.defParamService = defParamService;
	}

	public DefParamEntity getSelParam() {
		return selParam;
	}

	public void setSelParam(DefParamEntity selParam) {
		if (selParam!=null){
			this.selParam = selParam;
		}
	}

	public List<DefParamEntity> getParamList() {
		return paramList;
	}

	public void setParamList(List<DefParamEntity> paramList) {
		this.paramList = paramList;
	}

	public DefParamAnswerViewBean getDefParamAnswerViewBean() {
		return defParamAnswerViewBean;
	}

	public void setDefParamAnswerViewBean(
			DefParamAnswerViewBean defParamAnswerViewBean) {
		this.defParamAnswerViewBean = defParamAnswerViewBean;
	}

}
