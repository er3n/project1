package org.abacus.definition.shared.event;

import java.util.List;

import org.abacus.common.shared.event.UpdatedEvent;
import org.abacus.definition.shared.entity.DefItemProductEntity;

public class ItemProductUpdatedEvent extends UpdatedEvent {

	private List<DefItemProductEntity> updatedProductEntities;

	public List<DefItemProductEntity> getUpdatedProductEntities() {
		return updatedProductEntities;
	}

	public void setUpdatedProductEntities(List<DefItemProductEntity> updatedProductEntities) {
		this.updatedProductEntities = updatedProductEntities;
	}

}
