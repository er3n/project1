package org.abacus.definition.core.persistance.repository;

import java.util.List;

import org.abacus.definition.shared.entity.DefTypeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface DefTypeRepository extends CrudRepository<DefTypeEntity, String> {

	@Query("select a from DefTypeEntity a where a.id!='.' order by a.id")
	List<DefTypeEntity> findAllOrderById();
	
}
