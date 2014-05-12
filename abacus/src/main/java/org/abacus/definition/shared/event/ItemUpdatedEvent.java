package org.abacus.definition.shared.event;

import org.abacus.common.shared.event.UpdatedEvent;
import org.abacus.definition.shared.entity.DefItemEntity;

public class ItemUpdatedEvent extends UpdatedEvent {

	private DefItemEntity item;

	public ItemUpdatedEvent(DefItemEntity item) {
		this.item = item;
	}

	public DefItemEntity getItem() {
		return item;
	}

	public void setItem(DefItemEntity item) {
		this.item = item;
	}

}
