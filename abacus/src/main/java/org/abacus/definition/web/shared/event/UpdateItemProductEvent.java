package org.abacus.definition.web.shared.event;

import org.abacus.common.shared.event.UpdatedEvent;
import org.abacus.definition.shared.entity.DefItemProductEntity;

public class UpdateItemProductEvent extends UpdatedEvent {

	private DefItemProductEntity product;

	public UpdateItemProductEvent(DefItemProductEntity product, String userUpdated) {
		this.product = product;
		this.userUpdated = userUpdated;
	}

	public DefItemProductEntity getProduct() {
		return product;
	}

	public void setProduct(DefItemProductEntity product) {
		this.product = product;
	}

}
