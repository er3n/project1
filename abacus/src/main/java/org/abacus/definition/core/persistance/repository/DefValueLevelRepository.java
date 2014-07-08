package org.abacus.definition.core.persistance.repository;

import org.abacus.definition.shared.entity.DefValueLevelEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface DefValueLevelRepository extends CrudRepository<DefValueLevelEntity, String> {

	@Modifying
	@Transactional
	@Query("delete from DefValueLevelEntity e where e.value.id = :valueId")
	void deleteLevel(@Param("valueId")Long valueId);
	
}
