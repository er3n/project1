package org.abacus.catering.shared.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.abacus.common.shared.entity.DynamicEntity;
import org.abacus.definition.shared.entity.DefItemEntity;
import org.abacus.definition.shared.entity.DefValueEntity;
import org.abacus.organization.shared.entity.FiscalYearEntity;

@Entity
@SuppressWarnings("serial")
@Table(name = "cat_meal_filter")
public class CatMealFilterEntity extends DynamicEntity {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fiscal_year_id", nullable = false)
	private FiscalYearEntity fiscalYear;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "meal_id", nullable = false)
	private DefItemEntity meal;

	@Column(name = "count_prepare", nullable = false, precision = 10, scale = 0)
	private BigDecimal countPrepare;

	@Column(name = "unit_price", nullable = false, precision = 10, scale = 2)
	private BigDecimal unitPrice;

	public DefItemEntity getMeal() {
		return meal;
	}

	public void setMeal(DefItemEntity meal) {
		this.meal = meal;
	}

	public BigDecimal getCountPrepare() {
		return countPrepare;
	}

	public void setCountPrepare(BigDecimal countPrepare) {
		this.countPrepare = countPrepare;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public FiscalYearEntity getFiscalYear() {
		return fiscalYear;
	}

	public void setFiscalYear(FiscalYearEntity fiscalYear) {
		this.fiscalYear = fiscalYear;
	}

}
