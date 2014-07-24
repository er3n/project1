package org.abacus.report.core.persistance.repository;

import java.util.List;

import org.abacus.report.shared.entity.RepPivotEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RepPivotRepository extends CrudRepository<RepPivotEntity, Long> {

	@Query("select c from RepPivotEntity c where c.organization.id = :organizationId")
	List<RepPivotEntity> findReport(@Param("organizationId")String organizationId);

}
