package org.abacus.definition.shared.event;

import java.util.List;

import org.abacus.common.shared.event.UpdatedEvent;
import org.abacus.definition.shared.entity.DefItemProductEntity;

public class UpdateItemProductEvent extends UpdatedEvent {

	private DefItemProductEntity product;
	private List<DefItemProductEntity> products;

	public UpdateItemProductEvent(List<DefItemProductEntity> products) {
		super();
		this.products = products;
	}

	public UpdateItemProductEvent(DefItemProductEntity product, String userUpdated) {
		this.product = product;
		this.userUpdated = userUpdated;
	}

	public List<DefItemProductEntity> getProducts() {
		return products;
	}

	public void setProducts(List<DefItemProductEntity> products) {
		this.products = products;
	}

	public DefItemProductEntity getProduct() {
		return product;
	}

	public void setProduct(DefItemProductEntity product) {
		this.product = product;
	}
	
}
