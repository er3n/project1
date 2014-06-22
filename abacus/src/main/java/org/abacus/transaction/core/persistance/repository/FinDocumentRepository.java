package org.abacus.transaction.core.persistance.repository;

import org.abacus.transaction.shared.entity.FinDocumentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface FinDocumentRepository extends CrudRepository<FinDocumentEntity, Long>, TraDocumentRepository<FinDocumentEntity> {

	@Query("select d from FinDocumentEntity d inner join fetch d.organization o inner join fetch d.fiscalPeriod fp where d.id = :id")
	FinDocumentEntity findWithFetch(@Param("id") Long id);
	

}
