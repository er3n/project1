package org.abacus.budget.core.persistance.repository;

import java.util.List;

import org.abacus.budget.shared.entity.BudDetailEntity;
import org.abacus.definition.shared.constant.EnumList.BudgetType;
import org.abacus.organization.shared.entity.FiscalPeriodEntity;
import org.springframework.data.repository.CrudRepository;

public interface BudDetailRepository extends CrudRepository<BudDetailEntity, Long> {

	List<BudDetailEntity> findByFiscalPeriodAndBudgetType(FiscalPeriodEntity fiscalPeriod, BudgetType budgetType);

}
