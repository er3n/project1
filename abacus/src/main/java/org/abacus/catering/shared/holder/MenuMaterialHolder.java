package org.abacus.catering.shared.holder;

import org.abacus.definition.shared.entity.DefItemEntity;
import org.abacus.definition.shared.entity.DefUnitCodeEntity;

public class MenuMaterialHolder {
	private DefItemEntity item;
	private DefUnitCodeEntity unit;

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

}
