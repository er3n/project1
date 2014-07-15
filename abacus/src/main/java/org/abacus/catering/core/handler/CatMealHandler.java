package org.abacus.catering.core.handler;

import java.io.Serializable;
import java.util.List;

import org.abacus.catering.shared.entity.CatMealFilterEntity;
import org.abacus.organization.shared.entity.FiscalYearEntity;

public interface CatMealHandler extends Serializable{

	List<CatMealFilterEntity> getCatMealList(FiscalYearEntity fiscalYear);

	CatMealFilterEntity saveCatMealEntity(CatMealFilterEntity entity);
	
	void deleteCatMealEntity(CatMealFilterEntity entity);

}
