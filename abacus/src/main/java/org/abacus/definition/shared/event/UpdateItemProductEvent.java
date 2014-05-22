package org.abacus.definition.shared.event;

import java.util.List;

import org.abacus.definition.shared.entity.DefItemProductEntity;

public class UpdateItemProductEvent {
	private List<DefItemProductEntity> products;

	public UpdateItemProductEvent(List<DefItemProductEntity> products) {
		super();
		this.products = products;
	}

	public List<DefItemProductEntity> getProducts() {
		return products;
	}

	public void setProducts(List<DefItemProductEntity> products) {
		this.products = products;
	}

}
