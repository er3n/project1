package org.abacus.budget.core.handler;

import java.util.List;

import org.abacus.budget.core.persistance.repository.BudDetailRepository;
import org.abacus.budget.core.persistance.repository.BudDocumentRepository;
import org.abacus.budget.shared.entity.BudDocumentEntity;
import org.abacus.budget.shared.holder.BudgetHolder;
import org.abacus.organization.core.handler.FiscalHandler;
import org.abacus.organization.shared.entity.FiscalPeriodEntity;
import org.abacus.organization.shared.entity.FiscalYearEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value="budgedHandler")
public class BudgetHandlerImpl implements BudgetHandler {
	
	@Autowired
	private FiscalHandler fiscalHandler;
	
	@Autowired
	private BudDocumentRepository budDocumentRepository;
	
	@Autowired
	private BudDetailRepository budDetailRepository;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public BudgetHolder summerizeProjectBudget(FiscalYearEntity fiscalYear) {
		
		List<FiscalPeriodEntity> fiscalPeriodList = fiscalHandler.findFiscalPeriodList(fiscalYear);
		
		BudDocumentEntity document = new BudDocumentEntity();
		BudgetHolder budged = new BudgetHolder();
		
		
		for(FiscalPeriodEntity fiscalPeriod : fiscalPeriodList){
			
		}
		
		return budged;
	}
	
}
