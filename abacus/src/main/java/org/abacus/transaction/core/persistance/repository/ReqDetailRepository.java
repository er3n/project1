package org.abacus.transaction.core.persistance.repository;

import java.util.List;

import org.abacus.transaction.shared.entity.ReqDetailEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ReqDetailRepository extends CrudRepository<ReqDetailEntity, Long>, TraDetailRepository<ReqDetailEntity> {

	@Query("select d from ReqDetailEntity d inner join fetch d.item i inner join fetch d.department left outer join fetch d.itemUnit u left outer join fetch d.departmentOpp left outer join fetch i.itemUnitSet where d.document.id = :documentId and d.refDetailId is null")
	List<ReqDetailEntity> findByDocumentId(@Param("documentId")Long documentId);

}
 