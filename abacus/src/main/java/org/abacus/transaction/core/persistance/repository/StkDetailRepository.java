package org.abacus.transaction.core.persistance.repository;

import java.util.List;

import org.abacus.transaction.shared.entity.StkDetailEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface StkDetailRepository extends CrudRepository<StkDetailEntity, Long>{

	@Query("select d from StkDetailEntity d inner join fetch d.itemUnit inner join fetch d.item inner join fetch d.department left outer join fetch d.departmentOpp where d.document.id = :documentId")
	List<StkDetailEntity> findByDocumentId(@Param("documentId")Long documentId);

}
 