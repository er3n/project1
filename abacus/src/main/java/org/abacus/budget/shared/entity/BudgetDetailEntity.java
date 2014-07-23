package org.abacus.budget.shared.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.abacus.common.shared.entity.DynamicEntity;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.organization.shared.entity.FiscalPeriodEntity;

@Entity
@Table(name = "budget_detail")
@SuppressWarnings("serial")
public class BudgetDetailEntity extends DynamicEntity {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "document_id", nullable = false)
	private BudgetDocumentEntity document;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fiscal_period_id", nullable = false)
	private FiscalPeriodEntity fiscalPeriod;

	@Enumerated(EnumType.STRING)
	@Column(name = "budget_rx", nullable = false, length = 30)
	private EnumList.BudgetRX budgetRX;

	@Enumerated(EnumType.STRING)
	@Column(name = "budget_type", nullable = false, length = 30)
	private EnumList.BudgetType budgetType; // Giris : Estimate 

	@Column(name = "budget_amount", nullable = false, precision = 12, scale = 2)
	private BigDecimal budgetAmount;

	public BudgetDetailEntity() {
	}

}
