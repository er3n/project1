package org.abacus.definition.shared.event;

import org.abacus.common.shared.event.UpdatedEvent;
import org.abacus.definition.shared.entity.DefItemProductEntity;

public class DeleteItemProductEvent extends UpdatedEvent {

	private DefItemProductEntity product;

	public DeleteItemProductEvent(DefItemProductEntity product) {
		this.product = product;
	}

	public DefItemProductEntity getProduct() {
		return product;
	}

	public void setProduct(DefItemProductEntity product) {
		this.product = product;
	}
	
}
