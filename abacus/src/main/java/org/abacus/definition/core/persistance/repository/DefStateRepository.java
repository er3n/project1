package org.abacus.definition.core.persistance.repository;

import java.util.List;

import org.abacus.definition.shared.entity.DefStateEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface DefStateRepository extends CrudRepository<DefStateEntity, String> {

	@Query("select a from DefValueEntity a where a.type.id = :typeId order by a.id")
	List<DefStateEntity> findTypeStates(@Param("typeId") String typeId);
	
}
