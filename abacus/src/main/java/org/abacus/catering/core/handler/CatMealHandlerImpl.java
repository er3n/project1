package org.abacus.catering.core.handler;

import java.util.List;

import org.abacus.catering.core.persistance.repository.MealFilterRepository;
import org.abacus.catering.shared.entity.CatMealFilterEntity;
import org.abacus.organization.shared.entity.FiscalYearEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("serial")
@Service("catMealHandler")
public class CatMealHandlerImpl implements CatMealHandler {

	@Autowired
	private MealFilterRepository mealRepo;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
	public List<CatMealFilterEntity> getCatMealList(FiscalYearEntity fiscalYear){
		return mealRepo.getMealFilterList(fiscalYear.getId());
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public CatMealFilterEntity saveCatMealEntity(CatMealFilterEntity entity){
		entity = mealRepo.save(entity);
		return entity;	
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public void deleteCatMealEntity(CatMealFilterEntity entity){
		mealRepo.delete(entity);
	}

}
