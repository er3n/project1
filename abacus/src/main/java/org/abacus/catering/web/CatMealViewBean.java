package org.abacus.catering.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.abacus.catering.core.handler.CatMealHandler;
import org.abacus.catering.shared.entity.CatMealFilterEntity;
import org.abacus.common.web.JsfMessageHelper;
import org.abacus.common.web.SessionInfoHelper;
import org.abacus.organization.core.handler.OrganizationHandler;
import org.abacus.organization.shared.entity.FiscalYearEntity;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class CatMealViewBean implements Serializable {

	private CatMealFilterEntity selCatMeal;
	private List<CatMealFilterEntity> catMealList;

	@ManagedProperty(value = "#{catMealHandler}")
	private CatMealHandler catMealHandler;

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
		selCatMeal = catMealHandler.saveCatMealEntity(selCatMeal);
		findCatMealList();
	}

	public void deleteCatMeal() {
		if (!selCatMeal.isNew()) {
			catMealHandler.deleteCatMealEntity(selCatMeal);
			jsfMessageHelper.addInfo("deleteSuccessful", "Ögün");
		}
		findCatMealList();
	}

	public void clearCatMeal() {
		selCatMeal = new CatMealFilterEntity();
		selCatMeal.setFiscalYear(sessionInfoHelper.currentFiscalYear());
	}

	public void findCatMealList() {
		clearCatMeal();
		if (fiscalYear!=null){
			catMealList = catMealHandler.getCatMealList(fiscalYear);	
		} else {
			catMealList = new ArrayList<CatMealFilterEntity>();
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

	public CatMealFilterEntity getSelCatMeal() {
		return selCatMeal;
	}

	public void setSelCatMeal(CatMealFilterEntity selCatMeal) {
		if (selCatMeal != null) {
			this.selCatMeal = selCatMeal;
		}
	}

	public List<CatMealFilterEntity> getCatMealList() {
		return catMealList;
	}

	public void setCatMealList(List<CatMealFilterEntity> catMealList) {
		this.catMealList = catMealList;
	}

	public CatMealHandler getCatMealHandler() {
		return catMealHandler;
	}

	public void setCatMealHandler(CatMealHandler defUnitHandler) {
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