package org.abacus.transaction.core.persistance.repository;

import org.abacus.transaction.shared.entity.StkDocumentEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface StkDocumentRepository extends CrudRepository<StkDocumentEntity, Long>, TraDocumentRepository<StkDocumentEntity> {

	@Query("select d from StkDocumentEntity d inner join fetch d.organization o inner join fetch d.fiscalPeriod fp where d.id = :id")
	StkDocumentEntity findWithFetch(@Param("id") Long id);
	
	
	@Modifying
	@Transactional
	@Query("update StkDocumentEntity t set t.refFinDocumentId=null  where t.refFinDocumentId = :finDocId")
	void removeRefFinInfo(@Param("finDocId") Long id);

}
