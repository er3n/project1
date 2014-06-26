package org.abacus.catering.shared.holder;

import java.math.BigDecimal;

import org.abacus.definition.shared.entity.DefItemEntity;
import org.abacus.definition.shared.entity.DefUnitCodeEntity;

public class MenuMaterialHolder {
	private DefItemEntity item;
	private DefUnitCodeEntity unit;
	private BigDecimal countSpend;

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

	public BigDecimal getCountSpend() {
		return countSpend;
	}

	public void setCountSpend(BigDecimal countSpend) {
		this.countSpend = countSpend;
	}

}
