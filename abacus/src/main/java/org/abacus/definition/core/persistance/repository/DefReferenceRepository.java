package org.abacus.definition.core.persistance.repository;

import java.util.List;

import org.abacus.definition.shared.entity.DefReferenceEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface DefReferenceRepository extends CrudRepository<DefReferenceEntity, Long> {

	@Query("select a from DefReferenceEntity a where a.organization.id = :organizationId and a.type.id like :typeId")
	List<DefReferenceEntity> getReferenceList(@Param("organizationId")String organizationId, @Param("typeId") String typeId);

}
