package org.abacus.catering.core.persistance.repository;

import java.util.List;

import org.abacus.catering.shared.entity.CatMenuInfoEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MenuInfoRepository extends CrudRepository<CatMenuInfoEntity,Long>{

	@Query("select a from CatMenuInfoEntity a where a.fiscalYear.id = :fiscalYearId order by a.meal.code")
	List<CatMenuInfoEntity> getMenuInfoList(@Param("fiscalYearId")String fiscalYearId);
	
}
