package org.abacus.definition.core.persistance.repository;

import java.util.List;

import org.abacus.definition.shared.entity.DefUnitGroupEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface DefUnitGroupRepository extends CrudRepository<DefUnitGroupEntity, Long> {

	@Query("select distinct(a) from DefUnitGroupEntity a left outer join fetch a.unitCodeList c where a.organization.id = :organizationId order by a.code")
	List<DefUnitGroupEntity> getUnitGroupList(@Param("organizationId") String organizationId);
	
}
