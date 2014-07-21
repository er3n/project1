package org.abacus.transaction.core.persistance.repository;

import java.util.List;

import org.abacus.transaction.shared.entity.StkDetailEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface StkDetailRepository extends CrudRepository<StkDetailEntity, Long>, TraDetailRepository<StkDetailEntity> {

	@Query("select d from StkDetailEntity d inner join fetch d.item i inner join fetch d.department left outer join fetch d.itemUnit u left outer join fetch d.departmentOpp left outer join fetch i.itemUnitSet where d.document.id = :documentId")
	List<StkDetailEntity> findAllByDocumentId(@Param("documentId")Long documentId);

	@Query("select d from StkDetailEntity d inner join fetch d.item i inner join fetch d.department left outer join fetch d.itemUnit u left outer join fetch d.departmentOpp left outer join fetch i.itemUnitSet where d.document.id = :documentId and d.refStkDetailId is null")
	List<StkDetailEntity> findByDocumentId(@Param("documentId")Long documentId);

	@Query("select d from StkDetailEntity d where d.refStkDetailId = :refStkDetailId")
	StkDetailEntity findByRefId(@Param("refStkDetailId")Long refStkDetailId);

}
 