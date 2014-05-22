package org.abacus.definition.web.shared.event;

import java.util.List;

import org.abacus.common.shared.event.CreateEvent;
import org.abacus.definition.shared.entity.DefItemProductEntity;

public class CreateItemProductEvent extends CreateEvent {

	private List<DefItemProductEntity> createItemProducts;

	public CreateItemProductEvent(List<DefItemProductEntity> createItemProducts) {
		this.createItemProducts = createItemProducts;
	}

	public List<DefItemProductEntity> getCreateItemProducts() {
		return createItemProducts;
	}

	public void setCreateItemProducts(List<DefItemProductEntity> createItemProducts) {
		this.createItemProducts = createItemProducts;
	}

}
