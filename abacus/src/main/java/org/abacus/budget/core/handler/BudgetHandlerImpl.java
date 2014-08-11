package org.abacus.budget.core.handler;

import java.math.BigDecimal;
import java.util.List;

import org.abacus.budget.core.persistance.BudDetailDao;
import org.abacus.budget.core.persistance.repository.BudDetailRepository;
import org.abacus.budget.core.persistance.repository.BudDocumentRepository;
import org.abacus.budget.shared.entity.BudDetailEntity;
import org.abacus.budget.shared.entity.BudDocumentEntity;
import org.abacus.budget.shared.event.CreateBudDocumentEvent;
import org.abacus.budget.shared.event.CreateBudgetDetailEvent;
import org.abacus.budget.shared.event.UpdateBudDocumentEvent;
import org.abacus.budget.shared.event.UpdateBudgetDetailEvent;
import org.abacus.budget.shared.holder.BudgetHolder;
import org.abacus.budget.shared.holder.BudgetPeriodHolder;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.organization.core.handler.FiscalHandler;
import org.abacus.organization.shared.entity.FiscalPeriodEntity;
import org.abacus.organization.shared.entity.FiscalYearEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "budgetHandler")
public class BudgetHandlerImpl implements BudgetHandler {

	@Autowired
	private FiscalHandler fiscalHandler;

	@Autowired
	private BudDocumentRepository budDocumentRepository;

	@Autowired
	private BudDetailRepository budDetailRepository;

	@Autowired
	private BudDetailDao budDetailDao;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public BudgetHolder summerizeProjectBudget(FiscalYearEntity fiscalYear) {

		List<FiscalPeriodEntity> fiscalPeriodList = fiscalHandler.findFiscalPeriodList(fiscalYear);

		BudDocumentEntity document = budDocumentRepository.findByFiscalYear(fiscalYear);
		BudgetHolder budget = new BudgetHolder(document);

		for (FiscalPeriodEntity fiscalPeriod : fiscalPeriodList) {

			List<BudDetailEntity> details = budDetailRepository.findByFiscalPeriodAndBudgetType(fiscalPeriod, EnumList.BudgetType.ESTIMATE);

			budget.addNullSafePeriod(details, fiscalPeriod);

		}

		this.calculateSum(budget);

		return budget;
	}

	private void calculateSum(BudgetHolder budget) {
		
		List<BudgetPeriodHolder> periodList = budget.getPeriodList();
		
		BigDecimal budgetRevanueSum = BigDecimal.ZERO;
		BigDecimal budgetExpenseSum = BigDecimal.ZERO;
		
		for(BudgetPeriodHolder period : periodList){
			
			BigDecimal periodRevanue = period.getRevanue().getBudgetAmount() == null ? BigDecimal.ZERO : period.getRevanue().getBudgetAmount();
			BigDecimal periodExpense = period.getExpense().getBudgetAmount() == null ? BigDecimal.ZERO : period.getExpense().getBudgetAmount();
			
			BigDecimal periodSum = periodRevanue.subtract(periodExpense);
			budgetRevanueSum = budgetRevanueSum.add(periodRevanue);
			budgetExpenseSum = budgetExpenseSum.add(periodExpense);
			
			period.setSum(periodSum);
			
		}
		
		BigDecimal budgetSum = budgetRevanueSum.subtract(budgetExpenseSum);
		
		budget.setRevanueSum(budgetRevanueSum);
		budget.setExpenseSum(budgetExpenseSum);
		budget.setSum(budgetSum);
		
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public BudDocumentEntity createDocument(CreateBudDocumentEvent createBudDocumentEvent) {

		BudDocumentEntity document = createBudDocumentEvent.getDocument();
		String user = createBudDocumentEvent.getUser();

		document.createHook(user);
		document = budDocumentRepository.save(document);

		return document;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public BudDocumentEntity updateDocument(UpdateBudDocumentEvent updateBudDocumentEvent) {
		BudDocumentEntity document = updateBudDocumentEvent.getDocument();
		String user = updateBudDocumentEvent.getUser();

		document.updateHook(user);
		document = budDocumentRepository.save(document);

		return document;

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public BudgetHolder createDetail(CreateBudgetDetailEvent createBudgetDetailEvent) {

		BudDocumentEntity document = createBudgetDetailEvent.getDocument();
		BudDetailEntity detail = createBudgetDetailEvent.getDetail();
		String user = createBudgetDetailEvent.getUser().getUsername();

		detail.setBudgetType(EnumList.BudgetType.ESTIMATE);
		detail.setDocument(document);
		detail.createHook(user);

		detail = budDetailRepository.save(detail);

		BudgetHolder holder = this.summerizeProjectBudget(document.getFiscalYear());

		return holder;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public BudgetHolder updateDetail(UpdateBudgetDetailEvent updateBudgetDetailEvent) {

		BudDetailEntity detail = updateBudgetDetailEvent.getDetail();
		String user = updateBudgetDetailEvent.getUser().getUsername();
		FiscalYearEntity fiscalYear = updateBudgetDetailEvent.getFiscalYear();

		detail.updateHook(user);
		detail = budDetailRepository.save(detail);

		BudgetHolder holder = this.summerizeProjectBudget(fiscalYear);

		return holder;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void convertBudget(FiscalPeriodEntity period){
		BudgetHolder budHolder = summerizeProjectBudget(period.getFiscalYear());
		List<BudDetailEntity> budList = budDetailDao.getFinAccrueBudget(period);
			
		for (BudDetailEntity bud : budList) {
			bud.setDocument(budHolder.getDocument());
			bud.setFiscalPeriod(period);
			budDetailRepository.save(bud);
		}
	}
	
}
