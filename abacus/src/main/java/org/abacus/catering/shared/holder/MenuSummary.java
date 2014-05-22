package org.abacus.catering.shared.holder;

import java.util.List;

import org.abacus.catering.shared.entity.CatMealFilterEntity;

public class MenuSummary {
	private List<DailyMenuDetail> dailyMenuDetails;
	private List<CatMealFilterEntity> meals;

	public List<DailyMenuDetail> getDailyMenuDetails() {
		return dailyMenuDetails;
	}

	public void setDailyMenuDetails(List<DailyMenuDetail> dailyMenuDetails) {
		this.dailyMenuDetails = dailyMenuDetails;
	}

	public List<CatMealFilterEntity> getMeals() {
		return meals;
	}

	public void setMeals(List<CatMealFilterEntity> meals) {
		this.meals = meals;
	}

}
