package org.abacus.definition.core.persistance.repository;

import java.util.List;

import org.abacus.definition.shared.entity.DefUnitCodeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface DefUnitCodeRepository extends CrudRepository<DefUnitCodeEntity, Long> {

	@Query("select a from DefUnitCodeEntity a where a.unitGroup.id = :unitGroupId order by a.ratio desc")
	List<DefUnitCodeEntity> getUnitCodeList(@Param("unitGroupId") Long unitGroupId);
	
}
