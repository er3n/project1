package org.abacus.definition.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.abacus.common.web.JsfMessageHelper;
import org.abacus.definition.core.handler.DefUnitHandler;
import org.abacus.definition.shared.entity.DefUnitCodeEntity;
import org.abacus.definition.shared.entity.DefUnitGroupEntity;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class UnitCodeViewBean implements Serializable {

	@ManagedProperty(value = "#{jsfMessageHelper}")
	private JsfMessageHelper jsfMessageHelper;
	
	@ManagedProperty(value = "#{defUnitHandler}")
	private DefUnitHandler defUnitHandler;

	private DefUnitGroupEntity selUnitGruop;

	private DefUnitCodeEntity selUnitCode;
	private List<DefUnitCodeEntity> unitCodeList;

	@PostConstruct
	public void init() {
	}
	
	public void setSelUnitGroup(DefUnitGroupEntity unitGroup) {
		this.selUnitGruop = unitGroup;
		findUnitCode();
	}

	public void unitCodeRowSelectListener() {
	}

	public void groupChangeListener(){
		findUnitCode();
	}
	
	public void saveUnitCode() {
		if (selUnitCode.isNew()) {
			jsfMessageHelper.addInfo("unitCodeKayitIslemiBasarili");
		} else {
			jsfMessageHelper.addInfo("unitCodeGuncellemeIslemiBasarili");
		}
		defUnitHandler.saveUnitCodeEntity(selUnitCode);
		findUnitCode();
	}

	public void deleteUnitCode() {
		if (!selUnitCode.isNew()) {
			defUnitHandler.deleteUnitCodeEntity(selUnitCode);
			jsfMessageHelper.addInfo("unitCodeSilmeIslemiBasarili");
		}
		findUnitCode();
	}

	public void createUnitCode() {
		selUnitCode = new DefUnitCodeEntity();
		selUnitCode.setUnitGroup(selUnitGruop);
	}

	public void findUnitCode() {
		createUnitCode();
		unitCodeList = null;
		if (selUnitGruop!=null){
			unitCodeList = defUnitHandler.getUnitCodeList(selUnitGruop.getId());
		} else {
			unitCodeList = new ArrayList<DefUnitCodeEntity>();
		}
	}
	
	public JsfMessageHelper getJsfMessageHelper() {
		return jsfMessageHelper;
	}

	public void setJsfMessageHelper(JsfMessageHelper jsfMessageHelper) {
		this.jsfMessageHelper = jsfMessageHelper;
	}


	public DefUnitCodeEntity getSelUnitCode() {
		return selUnitCode;
	}

	public void setSelUnitCode(DefUnitCodeEntity selUnitCode) {
		if (selUnitCode!=null){
			this.selUnitCode = selUnitCode;
		}
	}

	public DefUnitGroupEntity getSelUnitGruop() {
		return selUnitGruop;
	}

	public void setSelUnitGruop(DefUnitGroupEntity selUnitGruop) {
		this.selUnitGruop = selUnitGruop;
	}

	public List<DefUnitCodeEntity> getUnitCodeList() {
		return unitCodeList;
	}

	public void setUnitCodeList(List<DefUnitCodeEntity> unitCodeList) {
		this.unitCodeList = unitCodeList;
	}

	public DefUnitHandler getDefUnitHandler() {
		return defUnitHandler;
	}

	public void setDefUnitHandler(DefUnitHandler defUnitHandler) {
		this.defUnitHandler = defUnitHandler;
	}



}
