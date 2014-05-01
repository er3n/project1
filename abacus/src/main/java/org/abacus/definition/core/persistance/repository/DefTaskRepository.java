package org.abacus.definition.core.persistance.repository;

import java.util.List;

import org.abacus.definition.shared.entity.DefStateEntity;
import org.abacus.definition.shared.entity.DefTaskEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface DefTaskRepository extends CrudRepository<DefTaskEntity, String> {

	@Query("select a from DefTaskEntity a where a.type.id = :typeId order by a.id")
	List<DefStateEntity> findTypeTaskList(@Param("typeId") String typeId);
	
}
