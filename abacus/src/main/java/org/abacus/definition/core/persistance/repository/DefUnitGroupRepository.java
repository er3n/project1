package org.abacus.definition.core.persistance.repository;

import java.util.List;

import org.abacus.definition.shared.entity.DefUnitGroupEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface DefUnitGroupRepository extends CrudRepository<DefUnitGroupEntity, Long> {

	@Query("select a from DefUnitGroupEntity a where a.organization.id = :organizationId order by a.code")
	List<DefUnitGroupEntity> getUnitGroupList(@Param("organizationId") String organizationId);
	
}
