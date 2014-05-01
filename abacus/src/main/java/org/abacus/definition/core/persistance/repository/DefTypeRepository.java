package org.abacus.definition.core.persistance.repository;

import java.util.List;

import org.abacus.definition.shared.entity.DefTypeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface DefTypeRepository extends CrudRepository<DefTypeEntity, String> {

	@Query("select a from DefTypeEntity a where a.id != '.' and a.group = :groupEnum order by a.id")
	List<DefTypeEntity> getTypeList(@Param("groupEnum") String groupEnum);
	
}
