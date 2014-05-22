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

@ManagedBean
@ViewScoped
public class CatMenuViewBean implements Serializable {

	@ManagedProperty(value = "#{jsfMessageHelper}")
	private JsfMessageHelper jsfMessageHelper;

	@ManagedProperty(value = "#{sessionInfoHelper}")
	private SessionInfoHelper sessionInfoHelper;

	@ManagedProperty(value = "#{menuHandler}")
	private CatMenuHandler menuHandler;

	private CatMenuSearchCriteria searchCriteria;

	private MenuSummary menuSummary;
	
	private List<CatMealFilterEntity> meals;

	@PostConstruct
	private void init() {
		this.searchCriteria = new CatMenuSearchCriteria();
		searchCriteria.setDate(Calendar.getInstance().getTime());
		searchCriteria.setPeriod(EnumList.CatMenuPeriod.WEEKLY);
		this.initMenuSummary();
	}

	public void initMenuSummary() {
		this.menuSummary = menuHandler.findMenuSummary(this.searchCriteria);
		this.meals = menuSummary.getMeals();
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

	
}
