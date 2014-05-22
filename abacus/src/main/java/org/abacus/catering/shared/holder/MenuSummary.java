package org.abacus.catering.shared.holder;

import java.util.List;
import java.util.Map;

import org.abacus.catering.shared.entity.CatMealFilterEntity;

public class MenuSummary {
	private Map<String, MenuDetail> menuDetailMap;
	private List<CatMealFilterEntity> meals;

	public Map<String, MenuDetail> getMenuDetailMap() {
		return menuDetailMap;
	}

	public void setMenuDetailMap(Map<String, MenuDetail> menuDetailMap) {
		this.menuDetailMap = menuDetailMap;
	}

	public List<CatMealFilterEntity> getMeals() {
		return meals;
	}

	public void setMeals(List<CatMealFilterEntity> meals) {
		this.meals = meals;
	}

}
