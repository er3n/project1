package org.abacus.transaction.core.persistance.repository;

import java.util.List;

import org.abacus.transaction.shared.entity.TraDetailEntity;

public interface TraDetailRepository<D extends TraDetailEntity<D>> {

	D save(D entity);
	
	List<D> findByDocumentId(Long documentId);

}
