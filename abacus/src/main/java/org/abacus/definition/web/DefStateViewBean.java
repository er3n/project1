package org.abacus.definition.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.abacus.common.web.JsfMessageHelper;
import org.abacus.definition.core.handler.DefStateHandler;
import org.abacus.definition.shared.entity.DefStateEntity;
import org.abacus.definition.shared.entity.DefTypeEntity;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class DefStateViewBean implements Serializable {

	@ManagedProperty(value = "#{jsfMessageHelper}")
	private JsfMessageHelper jsfMessageHelper;
	
	@ManagedProperty(value = "#{defStateHandler}")
	private DefStateHandler defStateService;

	private DefTypeEntity selType;

	private DefStateEntity selState;
	private List<DefStateEntity> stateList;


	@PostConstruct
	public void init() {
	}
	
	public void setSelType(DefTypeEntity selType) {
		this.selType = selType;
		findTypeState();
	}

	public void stateRowSelectListener() {
//		System.out.println("stateRowSelectListener");
	}

	
	public void saveState() {
		if (selState.isNew()) {
			jsfMessageHelper.addInfo("stateKayitIslemiBasarili");
		} else {
			jsfMessageHelper.addInfo("stateGuncellemeIslemiBasarili");
		}
		defStateService.saveStateEntity(selState);
		findTypeState();
	}

	public void deleteState() {
		if (!selState.isNew()) {
			defStateService.deleteStateEntity(selState);
			jsfMessageHelper.addInfo("stateSilmeIslemiBasarili");
		}
		findTypeState();
	}

	public void createState() {
		selState = new DefStateEntity();
		selState.setType(selType);
	}

	public void findTypeState() {
		createState();
		stateList = null;
		if (selType!=null){
			stateList = defStateService.getStateList(selType.getId());
		} else {
			stateList = new ArrayList<DefStateEntity>();
		}
//		System.out.println(stateList);
	}
	
	public JsfMessageHelper getJsfMessageHelper() {
		return jsfMessageHelper;
	}

	public void setJsfMessageHelper(JsfMessageHelper jsfMessageHelper) {
		this.jsfMessageHelper = jsfMessageHelper;
	}


	public DefStateEntity getSelState() {
		return selState;
	}

	public void setSelState(DefStateEntity selState) {
		if (selState!=null){
			this.selState = selState;
		}
	}

	public DefStateHandler getDefStateService() {
		return defStateService;
	}

	public void setDefStateService(DefStateHandler defStateService) {
		this.defStateService = defStateService;
	}

	public List<DefStateEntity> getStateList() {
		return stateList;
	}

	public void setStateList(List<DefStateEntity> stateList) {
		this.stateList = stateList;
	}


}
