package org.abacus.transaction.core.persistance.repository;

import org.abacus.transaction.shared.entity.ReqDocumentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ReqDocumentRepository extends CrudRepository<ReqDocumentEntity, Long>, TraDocumentRepository<ReqDocumentEntity> {

	@Query("select d from ReqDocumentEntity d inner join fetch d.organization o inner join fetch d.fiscalPeriod fp where d.id = :id")
	ReqDocumentEntity findWithFetch(@Param("id") Long id);

}
