package org.abacus.definition.core.persistance.repository;

import org.abacus.definition.shared.entity.DefItemUnitEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface DefItemUnitRepository extends CrudRepository<DefItemUnitEntity, Long> {

	@Modifying
	@Transactional
	@Query("delete from DefItemUnitEntity e where e.item.id = :itemId")
	void delete(@Param("itemId")Long itemId);
	
}
