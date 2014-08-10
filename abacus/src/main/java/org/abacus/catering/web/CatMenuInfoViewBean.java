package org.abacus.catering.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.abacus.catering.core.handler.CatMenuHandler;
import org.abacus.catering.shared.entity.CatMenuInfoEntity;
import org.abacus.common.web.JsfMessageHelper;
import org.abacus.common.web.SessionInfoHelper;
import org.abacus.organization.core.handler.OrganizationHandler;
import org.abacus.organization.shared.entity.FiscalYearEntity;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class CatMenuInfoViewBean implements Serializable {

	private CatMenuInfoEntity selCatMeal;
	private List<CatMenuInfoEntity> catMealList;

	@ManagedProperty(value = "#{catMenuHandler}")
	private CatMenuHandler catMealHandler;

	@ManagedProperty(value = "#{sessionInfoHelper}")
	private SessionInfoHelper sessionInfoHelper;

	@ManagedProperty(value = "#{jsfMessageHelper}")
	private JsfMessageHelper jsfMessageHelper;

	@ManagedProperty(value = "#{organizationHandler}")
	private OrganizationHandler organizationHandler;

	private FiscalYearEntity fiscalYear;

	@PostConstruct
	public void init() {
		fiscalYear = sessionInfoHelper.currentFiscalYear();
		findCatMealList();
	}

	public void groupChangeListener() {
		clearCatMeal();
	}

	public void catMealRowSelectListener() {
	}

	public void saveCatMeal() {
		if (selCatMeal.isNew()) {
			jsfMessageHelper.addInfo("createSuccessful", "Ögün");
		} else {
			jsfMessageHelper.addInfo("updateSuccessful", "Ögün");
		}
		selCatMeal = catMealHandler.saveMenuInfoEntity(selCatMeal);
		findCatMealList();
	}

	public void deleteCatMeal() {
		if (!selCatMeal.isNew()) {
			catMealHandler.deleteMenuInfoEntity(selCatMeal);
			jsfMessageHelper.addInfo("deleteSuccessful", "Ögün");
		}
		findCatMealList();
	}

	public void clearCatMeal() {
		selCatMeal = new CatMenuInfoEntity();
		selCatMeal.setFiscalYear(sessionInfoHelper.currentFiscalYear());
	}

	public void findCatMealList() {
		clearCatMeal();
		if (fiscalYear!=null){
			catMealList = catMealHandler.getMenuInfoList(fiscalYear);	
		} else {
			catMealList = new ArrayList<CatMenuInfoEntity>();
		}
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

	public CatMenuInfoEntity getSelCatMeal() {
		return selCatMeal;
	}

	public void setSelCatMeal(CatMenuInfoEntity selCatMeal) {
		if (selCatMeal != null) {
			this.selCatMeal = selCatMeal;
		}
	}

	public List<CatMenuInfoEntity> getCatMealList() {
		return catMealList;
	}

	public void setCatMealList(List<CatMenuInfoEntity> catMealList) {
		this.catMealList = catMealList;
	}

	public CatMenuHandler getCatMealHandler() {
		return catMealHandler;
	}

	public void setCatMealHandler(CatMenuHandler defUnitHandler) {
		this.catMealHandler = defUnitHandler;
	}

	public OrganizationHandler getOrganizationHandler() {
		return organizationHandler;
	}

	public void setOrganizationHandler(OrganizationHandler organizationHandler) {
		this.organizationHandler = organizationHandler;
	}

	public FiscalYearEntity getFiscalYear() {
		return fiscalYear;
	}

	public void setFiscalYear(FiscalYearEntity fiscalYear) {
		this.fiscalYear = fiscalYear;
	}

}
