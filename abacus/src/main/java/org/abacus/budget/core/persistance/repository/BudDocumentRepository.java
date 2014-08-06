package org.abacus.budget.core.persistance.repository;

import org.abacus.budget.shared.entity.BudDocumentEntity;
import org.abacus.organization.shared.entity.FiscalYearEntity;
import org.springframework.data.repository.CrudRepository;

public interface BudDocumentRepository extends CrudRepository<BudDocumentEntity, Long> {

	BudDocumentEntity findByFiscalYear(FiscalYearEntity fiscalYear);

	

}
