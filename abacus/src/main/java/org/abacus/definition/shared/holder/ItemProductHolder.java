package org.abacus.definition.shared.holder;

import java.io.Serializable;
import java.math.BigDecimal;

import org.abacus.common.shared.holder.KeyValue;

public class ItemProductHolder implements Serializable {

	private static final long serialVersionUID = -89395978004321624L;

	private Long id;

	private KeyValue materialItem;

	private KeyValue unitType;

	private BigDecimal itemCount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public KeyValue getMaterialItem() {
		return materialItem;
	}

	public void setMaterialItem(KeyValue materialItem) {
		this.materialItem = materialItem;
	}

	public KeyValue getUnitType() {
		return unitType;
	}

	public void setUnitType(KeyValue unitType) {
		this.unitType = unitType;
	}

	public BigDecimal getItemCount() {
		return itemCount;
	}

	public void setItemCount(BigDecimal itemCount) {
		this.itemCount = itemCount;
	}

}
