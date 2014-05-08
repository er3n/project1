package org.abacus.definition.core.persistance.repository;

import java.util.List;

import org.abacus.definition.shared.entity.DefParamEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface DefParamAnswerRepository extends CrudRepository<DefParamEntity, String> {

	@Query("select a from DefParamAnswerEntity a where a.id = :paramId order by a.id")
	List<DefParamEntity> getParamList(@Param("paramId") String paramId);
	
}
