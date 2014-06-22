package org.abacus.transaction.core.persistance.repository;

import java.util.List;

import org.abacus.transaction.shared.entity.TraDetailEntity;

public interface TraDetailRepository<D extends TraDetailEntity> {

	List<D> findByDocumentId(Long documentId);

}
