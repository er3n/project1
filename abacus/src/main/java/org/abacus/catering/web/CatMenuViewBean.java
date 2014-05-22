package org.abacus.catering.web;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.abacus.catering.core.handler.CatMenuHandler;
import org.abacus.catering.shared.entity.CatMealFilterEntity;
import org.abacus.catering.shared.holder.CatMenuSearchCriteria;
import org.abacus.catering.shared.holder.MenuSummary;
import org.abacus.common.web.JsfMessageHelper;
import org.abacus.common.web.SessionInfoHelper;
import org.abacus.definition.shared.constant.EnumList;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CatMenuViewBean implements Serializable {

	@ManagedProperty(value = "#{jsfMessageHelper}")
	private JsfMessageHelper jsfMessageHelper;

	@ManagedProperty(value = "#{sessionInfoHelper}")
	private SessionInfoHelper sessionInfoHelper;

	@ManagedProperty(value = "#{catMenuHandler}")
	private CatMenuHandler menuHandler;

	private CatMenuSearchCriteria searchCriteria;

	private MenuSummary menuSummary;

	private List<CatMealFilterEntity> meals;

	@PostConstruct
	private void init() {
		this.searchCriteria = new CatMenuSearchCriteria();
		this.searchCriteria.setPeriod(EnumList.CatMenuPeriod.WEEKLY);
		searchCriteria.setDate(Calendar.getInstance().getTime());
		this.initMenuSummary();
	}

	public void initMenuSummary() {
		this.menuSummary = menuHandler.findMenuSummary(this.searchCriteria);
		this.meals = menuSummary.getMeals();
	}
	
	public void menuDateSelected(){
		searchCriteria.refreshDate();
		this.initMenuSummary();
	}

	public JsfMessageHelper getJsfMessageHelper() {
		return jsfMessageHelper;
	}

	public void setJsfMessageHelper(JsfMessageHelper jsfMessageHelper) {
		this.jsfMessageHelper = jsfMessageHelper;
	}

	public SessionInfoHelper getSessionInfoHelper() {
		return sessionInfoHelper;
	}

	public void setSessionInfoHelper(SessionInfoHelper sessionInfoHelper) {
		this.sessionInfoHelper = sessionInfoHelper;
	}

	public CatMenuSearchCriteria getSearchCriteria() {
		return searchCriteria;
	}

	public void setSearchCriteria(CatMenuSearchCriteria searchCriteria) {
		this.searchCriteria = searchCriteria;
	}

	public CatMenuHandler getMenuHandler() {
		return menuHandler;
	}

	public void setMenuHandler(CatMenuHandler menuHandler) {
		this.menuHandler = menuHandler;
	}

	public MenuSummary getMenuSummary() {
		return menuSummary;
	}

	public void setMenuSummary(MenuSummary menuSummary) {
		this.menuSummary = menuSummary;
	}

	public List<CatMealFilterEntity> getMeals() {
		return meals;
	}

	public void setMeals(List<CatMealFilterEntity> meals) {
		this.meals = meals;
	}
	
	

}
