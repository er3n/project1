package org.abacus.catering.core.handler;

import java.io.Serializable;
import java.util.List;

import org.abacus.catering.shared.entity.CatMealFilterEntity;

public interface CatMealHandler extends Serializable{


	List<CatMealFilterEntity> getCatMealList(String organizationId);

	CatMealFilterEntity saveCatMealEntity(CatMealFilterEntity entity);
	
	void deleteCatMealEntity(CatMealFilterEntity entity);

}
