package org.abacus.transaction.core.persistance.repository;

import java.util.List;

import org.abacus.transaction.shared.entity.ReqDetailEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ReqDetailRepository extends CrudRepository<ReqDetailEntity, Long>, TraDetailRepository<ReqDetailEntity> {

	@Query("select distinct(d) from ReqDetailEntity d inner join fetch d.item i left outer join fetch d.department left outer join fetch d.itemUnit u left outer join fetch d.departmentOpp left outer join fetch i.itemUnitSet left outer join fetch d.offerSet os where d.document.id = :documentId")
	List<ReqDetailEntity> findByDocumentId(@Param("documentId")Long documentId);

	@Query("select case when count(d) > 0 then true else false end from ReqDetailEntity d  where d.document.id = :documentId and d.stkDocument.id is not null")
	Boolean isAnyStkDocumentCreated(@Param("documentId")Long id);

	@Query("select d from ReqDetailEntity d inner join fetch d.offerSet o where d.document.id = :documentId and o.vendorItem.id = :vendorId and o.isSelected = true")
	List<ReqDetailEntity> findByDocumentAndSelectedVendor(@Param("documentId")Long document, @Param("vendorId")Long vendor);

}
 