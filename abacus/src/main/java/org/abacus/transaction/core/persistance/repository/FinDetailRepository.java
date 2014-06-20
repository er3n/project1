package org.abacus.transaction.core.persistance.repository;

import java.util.List;

import org.abacus.transaction.shared.entity.FinDetailEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface FinDetailRepository extends CrudRepository<FinDetailEntity, Long>{

	@Query("select d from FinDetailEntity d inner join fetch d.itemUnit inner join fetch d.item inner join fetch d.department where d.document.id = :documentId")
	List<FinDetailEntity> findByDocumentId(@Param("documentId")Long documentId);

}
 