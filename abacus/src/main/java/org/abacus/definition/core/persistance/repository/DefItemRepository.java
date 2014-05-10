package org.abacus.definition.core.persistance.repository;

import org.abacus.definition.shared.entity.DefItemEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface DefItemRepository extends CrudRepository<DefItemEntity, Long> {

	
	@Query("select f from DefItemEntity f inner join fetch f.unitGroup t where f.id = :itemId")
	DefItemEntity findWithFetch(@Param("itemId")Long itemId);

}
