package org.abacus.definition.core.persistance.repository;

import java.util.List;

import org.abacus.definition.shared.entity.DefValueEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface DefValueRepository extends CrudRepository<DefValueEntity, Long> {

	@Query("select a from DefValueEntity a where a.id>0 and a.type.id = :typeId order by a.code")
	List<DefValueEntity> findTypeValues(@Param("typeId") String typeId);
	
}
