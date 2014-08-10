package org.abacus.catering.core.persistance.repository;

import java.util.Date;
import java.util.List;

import org.abacus.catering.shared.entity.CatMenuEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MenuRepository extends CrudRepository<CatMenuEntity,Long>{

	@Query("select a from CatMenuEntity a where a.fiscalYear.id = :fiscalYearId and a.menuDate = coalesce(:menuDate, a.menuDate) and a.finDocument is null order by a.menuDate, a.id")
	List<CatMenuEntity> getMenuListForFinace(@Param("fiscalYearId")String fiscalYearId, @Param("menuDate")Date menuDate);

}
