package org.abacus.definition.core.persistance.repository;

import java.util.List;

import org.abacus.definition.shared.entity.DefTaskEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface DefTaskRepository extends CrudRepository<DefTaskEntity, Long> {

	@Query("select a from DefTaskEntity a where a.organization.id = :organizationId and a.type.id = :typeId order by a.id")
	List<DefTaskEntity> getTaskList(@Param("organizationId")String organizationId, @Param("typeId") String typeId);
	
}
