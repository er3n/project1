package org.abacus.definition.core.persistance.repository;

import org.abacus.definition.shared.entity.DefLevelEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface DefLevelRepository extends CrudRepository<DefLevelEntity, String> {

	@Modifying
	@Transactional
	@Query("delete from DefLevelEntity e where e.value.id = :valueId")
	void deleteLevel(@Param("valueId")Long valueId);
	
}
