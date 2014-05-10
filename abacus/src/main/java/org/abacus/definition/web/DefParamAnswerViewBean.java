package org.abacus.definition.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.abacus.common.web.JsfMessageHelper;
import org.abacus.common.web.SessionInfoHelper;
import org.abacus.definition.core.handler.DefParamAnswerHandler;
import org.abacus.definition.shared.entity.DefParamAnswerEntity;
import org.abacus.definition.shared.entity.DefParamEntity;
import org.abacus.organization.shared.entity.OrganizationEntity;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class DefParamAnswerViewBean implements Serializable {

	@ManagedProperty(value = "#{jsfMessageHelper}")
	private JsfMessageHelper jsfMessageHelper;
	
	@ManagedProperty(value = "#{defParamAnswerHandler}")
	private DefParamAnswerHandler defParamAnswerHandler;

	@ManagedProperty(value = "#{sessionInfoHelper}")
	private SessionInfoHelper sessionInfoHelper;
	
	private DefParamEntity selParam;

	private DefParamAnswerEntity selParamAnswer;
	private List<DefParamAnswerEntity> paramAnswerList;

	private OrganizationEntity rootOrganization;

	@PostConstruct
	public void init() {
		rootOrganization = sessionInfoHelper.currentRootOrganization();
	}
	
	public void setSelParam(DefParamEntity selParam) {
		this.selParam = selParam;
		findTypeParamAnswer();
	}

	public void paramAnswerRowSelectListener() {
		
	}

	
	public void saveParamAnswer() {
		if (selParamAnswer.isNew()) {
			jsfMessageHelper.addInfo("paramKayitIslemiBasarili");
		} else {
			jsfMessageHelper.addInfo("paramGuncellemeIslemiBasarili");
		}
		defParamAnswerHandler.saveParamAnswerEntity(selParamAnswer);
		findTypeParamAnswer();
	}

	public void deleteParamAnswer() {
		if (!selParamAnswer.isNew()) {
			defParamAnswerHandler.deleteParamAnswerEntity(selParamAnswer);
			jsfMessageHelper.addInfo("paramSilmeIslemiBasarili");
		}
		findTypeParamAnswer();
	}

	public void createParamAnswer() {
		selParamAnswer = new DefParamAnswerEntity();
		selParamAnswer.setParam(selParam);
	}

	public void findTypeParamAnswer() {
		createParamAnswer();
		paramAnswerList = null;
		if (selParam!=null){
			paramAnswerList = defParamAnswerHandler.getParamAnswerList(selParam.getId(), rootOrganization.getId());
		} else {
			paramAnswerList = new ArrayList<DefParamAnswerEntity>();
		}
//		System.out.println(paramList);
	}
	
	public JsfMessageHelper getJsfMessageHelper() {
		return jsfMessageHelper;
	}

	public void setJsfMessageHelper(JsfMessageHelper jsfMessageHelper) {
		this.jsfMessageHelper = jsfMessageHelper;
	}

	public DefParamAnswerEntity getSelParamAnswer() {
		return selParamAnswer;
	}

	public void setSelParamAnswer(DefParamAnswerEntity selParamAnswer) {
		if (selParamAnswer!=null){
			this.selParamAnswer = selParamAnswer;
		}
	}

	public List<DefParamAnswerEntity> getParamAnswerList() {
		return paramAnswerList;
	}

	public void setParamAnswerList(List<DefParamAnswerEntity> paramAnswerList) {
		this.paramAnswerList = paramAnswerList;
	}

	public SessionInfoHelper getSessionInfoHelper() {
		return sessionInfoHelper;
	}

	public void setSessionInfoHelper(SessionInfoHelper sessionInfoHelper) {
		this.sessionInfoHelper = sessionInfoHelper;
	}

	public DefParamAnswerHandler getDefParamAnswerHandler() {
		return defParamAnswerHandler;
	}

	public void setDefParamAnswerHandler(DefParamAnswerHandler defParamAnswerHandler) {
		this.defParamAnswerHandler = defParamAnswerHandler;
	}

}
