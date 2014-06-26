package org.abacus.definition.core.persistance.repository;

import java.util.Set;

import org.abacus.definition.shared.entity.DefItemProductEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface DefItemProductRepository extends CrudRepository<DefItemProductEntity, Long>{

	@Query("select t from DefItemProductEntity t where t.item.id = :itemId")
	Set<DefItemProductEntity> findItemProducts( @Param("itemId")Long itemId);

}
