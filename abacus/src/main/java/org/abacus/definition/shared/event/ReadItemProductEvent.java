package org.abacus.definition.shared.event;

import java.util.List;

import org.abacus.common.shared.event.RequestReadEvent;
import org.abacus.definition.shared.entity.DefItemProductEntity;

public class ReadItemProductEvent extends RequestReadEvent {

	private List<DefItemProductEntity> itemProducts;

	public List<DefItemProductEntity> getItemProducts() {
		return itemProducts;
	}

	public void setItemProducts(List<DefItemProductEntity> itemProducts) {
		this.itemProducts = itemProducts;
	}

}
