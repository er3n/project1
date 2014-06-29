package org.abacus.transaction.core.persistance.repository;

import java.util.List;

import org.abacus.transaction.shared.entity.FinDetailEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface FinDetailRepository extends CrudRepository<FinDetailEntity, Long>, TraDetailRepository<FinDetailEntity> {

	@Query("select d from FinDetailEntity d inner join fetch d.item i inner join fetch d.department where d.document.id = :documentId and d.refDetailId is null")
	List<FinDetailEntity> findByDocumentId(@Param("documentId")Long documentId);

}
 