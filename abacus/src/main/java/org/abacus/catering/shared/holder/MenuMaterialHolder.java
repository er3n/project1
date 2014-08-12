package org.abacus.catering.shared.holder;

import java.math.BigDecimal;

import org.abacus.definition.shared.entity.DefItemEntity;
import org.abacus.definition.shared.entity.DefUnitCodeEntity;

public class MenuMaterialHolder {
	private DefItemEntity item;
	private DefUnitCodeEntity unit;
	private BigDecimal unitItemCount;
	private BigDecimal countPrepare;

	public DefItemEntity getItem() {
		return item;
	}

	public void setItem(DefItemEntity item) {
		this.item = item;
	}

	public DefUnitCodeEntity getUnit() {
		return unit;
	}

	public void setUnit(DefUnitCodeEntity unit) {
		this.unit = unit;
	}

	public BigDecimal getUnitItemCount() {
		return unitItemCount;
	}

	public void setUnitItemCount(BigDecimal unitItemCount) {
		this.unitItemCount = unitItemCount;
	}

	public BigDecimal getCountPrepare() {
		return countPrepare;
	}

	public void setCountPrepare(BigDecimal countPrepare) {
		this.countPrepare = countPrepare;
	}

}
