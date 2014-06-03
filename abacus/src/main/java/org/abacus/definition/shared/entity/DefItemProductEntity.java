package org.abacus.definition.shared.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.abacus.common.shared.entity.DynamicEntity;

@Entity
@Table(name = "def_item_product")
@SuppressWarnings("serial")
public class DefItemProductEntity extends DynamicEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id", nullable = false)
	private DefItemEntity item;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "material_item_id", nullable = false) //Material 
	private DefItemEntity materialItem;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_code_id", nullable = false)
	private DefUnitCodeEntity materialUnitCode;

	@Column(name = "material_count", nullable = false, precision = 10, scale = 3)
	private BigDecimal materialCount;

	@Column(name = "material_order", nullable = false)
	private Long materialOrder = 0L;

	public DefItemProductEntity(){
	}

	public DefItemEntity getItem() {
		return item;
	}

	public void setItem(DefItemEntity item) {
		this.item = item;
	}

	public DefItemEntity getMaterialItem() {
		return materialItem;
	}

	public void setMaterialItem(DefItemEntity materialItem) {
		this.materialItem = materialItem;
	}

	public DefUnitCodeEntity getMaterialUnitCode() {
		return materialUnitCode;
	}

	public void setMaterialUnitCode(DefUnitCodeEntity materialUnitCode) {
		this.materialUnitCode = materialUnitCode;
	}

	public BigDecimal getMaterialCount() {
		return materialCount;
	}

	public void setMaterialCount(BigDecimal materialCount) {
		this.materialCount = materialCount;
	}

	public Long getMaterialOrder() {
		return materialOrder;
	}

	public void setMaterialOrder(Long materialOrder) {
		this.materialOrder = materialOrder;
	}

	
}
