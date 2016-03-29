package org.abacus.definition.core.persistance.repository;

import java.util.List;

import org.abacus.definition.shared.entity.DefItemEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface DefItemRepository extends CrudRepository<DefItemEntity, Long> {

	@Query("select f from DefItemEntity f left outer join fetch f.unitGroup t left outer join fetch f.itemUnitSet ius left outer join fetch ius.unitCode left outer join fetch f.itemProductSet ip left outer join fetch ip.materialItem m left outer join fetch ip.materialUnitCode left outer join fetch m.itemUnitSet  where f.id = :itemId")
	DefItemEntity findWithFetch(@Param("itemId") Long itemId);

	@Query("select f from DefItemEntity f where f.code = :code and f.type.id = :type and f.organization.id = :organization")
	DefItemEntity itemExists(@Param("code") String code, @Param("type") String type, @Param("organization") String organization);

	@Query("select f from DefItemEntity f where f.code = :code and f.type.id = :type and f.organization.id like :organization||'%' and f.active=true and f.login=true")
	List<DefItemEntity> loginItemList(@Param("code") String code, @Param("type") String type, @Param("organization") String organization);

}
