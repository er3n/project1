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
import org.abacus.definition.shared.entity.DefUnitCodeEntity;

@Entity
@SuppressWarnings("serial")
@Table(name = "cat_menu_item")
public class CatMenuItemEntity extends DynamicEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "menu_id", nullable = false)
	private CatMenuEntity menu;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "item_id", nullable = false)
	private DefItemEntity item;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "unit_id", nullable = false)
	private DefUnitCodeEntity unit;

	@Column(name = "menu_item_count", nullable = false, precision = 10, scale = 3)
	private BigDecimal menuItemCount = BigDecimal.ONE;
	
	public DefUnitCodeEntity getUnit() {
		return unit;
	}

	public void setUnit(DefUnitCodeEntity unit) {
		this.unit = unit;
	}

	public CatMenuEntity getMenu() {
		return menu;
	}

	public void setMenu(CatMenuEntity menu) {
		this.menu = menu;
	}

	public DefItemEntity getItem() {
		return item;
	}

	public void setItem(DefItemEntity item) {
		this.item = item;
	}

	public BigDecimal getMenuItemCount() {
		return menuItemCount;
	}

	public void setMenuItemCount(BigDecimal menuItemCount) {
		this.menuItemCount = menuItemCount;
	}

}
