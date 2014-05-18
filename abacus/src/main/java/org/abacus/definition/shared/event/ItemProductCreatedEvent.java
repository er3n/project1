package org.abacus.definition.shared.event;

import org.abacus.common.shared.event.CreatedEvent;
import org.abacus.definition.shared.entity.DefItemProductEntity;

public class ItemProductCreatedEvent extends CreatedEvent {

	private DefItemProductEntity product;

	public ItemProductCreatedEvent(DefItemProductEntity product) {
		this.product = product;
	}

	public DefItemProductEntity getProduct() {
		return product;
	}

	public void setProduct(DefItemProductEntity product) {
		this.product = product;
	}

}
