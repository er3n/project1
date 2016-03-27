package org.abacus.definition.core.persistance.repository;

import org.abacus.definition.shared.entity.DefValueEntity;
import org.springframework.data.repository.CrudRepository;

public interface DefValueRepository extends CrudRepository<DefValueEntity, Long> {

	//@Query("select a from DefValueEntity a where (a.organization.id is null or a.organization.id like :organizationId||'%') and a.type.id = :typeId order by a.code")
	//List<DefValueEntity> getValueList(@Param("organizationId")String organizationId, @Param("typeId") String typeId);

	//@Query("select f from DefValueEntity f where f.code = :code and f.type.id = :type and f.organization.id = :organization")
	//DefValueEntity valueExists(@Param("code") String code, @Param("type") String type, @Param("organization") String organization);

}
