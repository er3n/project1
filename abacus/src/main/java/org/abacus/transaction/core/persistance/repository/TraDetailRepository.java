package org.abacus.transaction.core.persistance.repository;

import java.util.List;

import org.abacus.transaction.shared.entity.TraDetailEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface TraDetailRepository<D extends TraDetailEntity> {

	@Modifying
	@Transactional
	D save(D entity);
	
	List<D> findByDocumentId(Long documentId);

}
