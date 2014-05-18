package org.abacus.definition.shared.event;

import org.abacus.common.shared.event.CreatedEvent;
import org.abacus.definition.shared.entity.DefItemProductEntity;

public class CreateItemProductEvent extends CreatedEvent {

	private DefItemProductEntity product;
	private String createdUser;

	public CreateItemProductEvent(DefItemProductEntity product, String createdUser) {
		this.product = product;
		this.createdUser = createdUser;
	}

	public DefItemProductEntity getProduct() {
		return product;
	}

	public void setProduct(DefItemProductEntity product) {
		this.product = product;
	}

	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

}
