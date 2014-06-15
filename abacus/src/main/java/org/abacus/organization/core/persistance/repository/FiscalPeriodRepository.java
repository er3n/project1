package org.abacus.organization.core.persistance.repository;

import java.util.Date;

import org.abacus.organization.shared.entity.FiscalPeriodEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface FiscalPeriodRepository extends CrudRepository<FiscalPeriodEntity, String> {

	@Query("select p from FiscalPeriodEntity p where p.fiscalYear.id = :fiscalYearId and :docDate between dateStart and dateFinish")
	public FiscalPeriodEntity findFiscalPeriod(@Param("fiscalYearId")String fiscalYearId, @Param("docDate")Date docDate);
	
}
