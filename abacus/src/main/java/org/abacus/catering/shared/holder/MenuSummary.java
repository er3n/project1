package org.abacus.catering.shared.holder;

import java.util.List;

import org.abacus.catering.shared.entity.CatMenuInfoEntity;

public class MenuSummary {
	private List<DailyMenuDetail> dailyMenuDetails;
	private List<CatMenuInfoEntity> meals;

	public List<DailyMenuDetail> getDailyMenuDetails() {
		return dailyMenuDetails;
	}

	public void setDailyMenuDetails(List<DailyMenuDetail> dailyMenuDetails) {
		this.dailyMenuDetails = dailyMenuDetails;
	}

	public List<CatMenuInfoEntity> getMeals() {
		return meals;
	}

	public void setMeals(List<CatMenuInfoEntity> meals) {
		this.meals = meals;
	}

}
