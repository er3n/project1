package org.abacus.definition.core.persistance.repository;

import java.util.List;

import org.abacus.definition.shared.entity.DefParamAnswerEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface DefParamAnswerRepository extends CrudRepository<DefParamAnswerEntity, Long> {

	@Query("select a from DefParamAnswerEntity a where a.param.id = :paramId and a.organization.id like :organizationId% order by a.organization.id")
	List<DefParamAnswerEntity> getParamAnswerList(@Param("paramId") String paramId, @Param("organizationId") String organizationId);
	
}
