package org.abacus.definition.shared.event;

import org.abacus.common.shared.event.CreatedEvent;
import org.abacus.definition.shared.entity.DefItemEntity;

public class CreateItemEvent extends CreatedEvent {

	private DefItemEntity item;
	private String createdUser;

	public CreateItemEvent(DefItemEntity item, String createdUser) {
		this.item = item;
		this.createdUser = createdUser;
	}

	public DefItemEntity getItem() {
		return item;
	}

	public void setItem(DefItemEntity item) {
		this.item = item;
	}

	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

}
