package org.abacus.definition.shared.event;

import org.abacus.common.shared.event.CreatedEvent;
import org.abacus.definition.shared.entity.DefItemEntity;

public class ItemCreatedEvent extends CreatedEvent {

	private DefItemEntity createdItem;
	
	public ItemCreatedEvent(DefItemEntity item){
		this.createdItem = item;
	}

	public DefItemEntity getCreatedItem() {
		return createdItem;
	}

	public void setCreatedItem(DefItemEntity createdItem) {
		this.createdItem = createdItem;
	}

}
