package org.abacus.transaction.core.persistance.repository;

import org.abacus.transaction.shared.entity.TraDocumentEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface TraDocumentRepository<T extends TraDocumentEntity> {

	@Modifying
	@Transactional
	T save(T entity);
	
	T findWithFetch(Long id);

}
