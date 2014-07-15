package org.abacus.catering.core.persistance.repository;

import java.util.List;

import org.abacus.catering.shared.entity.CatMealFilterEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MealFilterRepository extends CrudRepository<CatMealFilterEntity,Long>{

	@Query("select a from CatMealFilterEntity a where a.fiscalYear.id = :fiscalYearId order by a.meal.code")
	List<CatMealFilterEntity> getMealFilterList(@Param("fiscalYearId")String fiscalYearId);
	
}
