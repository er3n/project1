package org.abacus.organization.shared.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.abacus.common.shared.entity.DynamicEntity;
import org.abacus.definition.shared.entity.DefValueEntity;

@Entity
@SuppressWarnings("serial")
@Table(name = "org_menu_item")
public class MenuItemEntity extends DynamicEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "menu_id", nullable = false)
	private MenuEntity menu;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "item_id", nullable = false)
	private DefValueEntity item;

	public MenuEntity getMenu() {
		return menu;
	}

	public void setMenu(MenuEntity menu) {
		this.menu = menu;
	}

	public DefValueEntity getItem() {
		return item;
	}

	public void setItem(DefValueEntity item) {
		this.item = item;
	}

}
