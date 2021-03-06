package org.abacus.definition.shared.event;

import java.util.List;

import org.abacus.common.shared.event.UpdatedEvent;
import org.abacus.definition.shared.entity.DefItemProductEntity;

public class ItemProductUpdatedEvent extends UpdatedEvent {

	private List<DefItemProductEntity> updatedProductEntities;
	private DefItemProductEntity product;
	
	public ItemProductUpdatedEvent(DefItemProductEntity product){
		this.product = product;
	}

	public List<DefItemProductEntity> getUpdatedProductEntities() {
		return updatedProductEntities;
	}

	public void setUpdatedProductEntities(List<DefItemProductEntity> updatedProductEntities) {
		this.updatedProductEntities = updatedProductEntities;
	}
	
	public DefItemProductEntity getProduct() {
		return product;
	}

	public void setProduct(DefItemProductEntity product) {
		this.product = product;
	}


}
