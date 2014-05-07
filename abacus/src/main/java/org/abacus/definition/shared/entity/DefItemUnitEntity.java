package org.abacus.definition.shared.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.abacus.common.shared.entity.DynamicEntity;

@Entity
@Table(name = "def_item_unit")
@SuppressWarnings("serial")
public class DefItemUnitEntity extends DynamicEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id", nullable = false)
	private DefItemEntity item;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_code_id", nullable = false)
	private DefUnitCodeEntity unitCode;

	public DefItemUnitEntity(){
	}

	public DefItemEntity getItem() {
		return item;
	}

	public void setItem(DefItemEntity item) {
		this.item = item;
	}

	public DefUnitCodeEntity getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(DefUnitCodeEntity unitCode) {
		this.unitCode = unitCode;
	}

	
}
