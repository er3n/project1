package org.abacus.budget.core.persistance.repository;

import org.abacus.budget.shared.entity.BudDocumentEntity;
import org.springframework.data.repository.CrudRepository;

public interface BudDocumentRepository extends CrudRepository<BudDocumentEntity, Long> {

}
