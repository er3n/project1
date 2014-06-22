package org.abacus.transaction.core.persistance.repository;

import org.abacus.transaction.shared.entity.TraDocumentEntity;

public interface TraDocumentRepository<T extends TraDocumentEntity> {

	T findWithFetch(Long id);

}
