package org.abacus.transaction.core.persistance.repository;

import org.abacus.transaction.shared.entity.StkDocumentEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface StkDocumentRepository extends CrudRepository<StkDocumentEntity, Long>, TraDocumentRepository<StkDocumentEntity> {

	@Query("select d from StkDocumentEntity d inner join fetch d.organization o inner join fetch d.fiscalPeriod1 fp1 where d.id = :id")
	StkDocumentEntity findWithFetch(@Param("id") Long id);

	@Query("select d from StkDocumentEntity d where d.refFinDocumentId = :id")
	StkDocumentEntity findRefFinDocument(@Param("id") Long id);

	@Query("select d from StkDocumentEntity d where d.refStkDocumentId = :id and d.organization.id = :organizationId")
	StkDocumentEntity findRefStkDocument(@Param("id") Long id, @Param("organizationId")String organizationId);

	@Modifying
	@Transactional
	@Query("update StkDocumentEntity t set t.refFinDocumentId=null  where t.refFinDocumentId = :finDocId")
	void deleteRefFinInfo(@Param("finDocId") Long id);

}
