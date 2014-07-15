package org.abacus.organization.core.persistance.repository;

import java.util.Set;

import org.abacus.organization.shared.entity.FiscalYearEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface FiscalYearRepository extends CrudRepository<FiscalYearEntity, String> {

	@Query("select f from FiscalYearEntity f where f.organization.id = :companyId order by f.dateStart desc")
	public Set<FiscalYearEntity> findCompanyFiscalYearSet(@Param("companyId")String companyId);
	
}
