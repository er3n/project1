package org.abacus.definition.shared.event;

import org.abacus.common.shared.event.UpdatedEvent;
import org.abacus.definition.shared.entity.DefItemEntity;

public class UpdateItemEvent extends UpdatedEvent {

	private DefItemEntity item;
	private String userUpdated;
	
	public UpdateItemEvent(DefItemEntity item, String userUpdated){
		this.item = item;
		this.userUpdated = userUpdated;
	}

	public DefItemEntity getItem() {
		return item;
	}

	public void setItem(DefItemEntity item) {
		this.item = item;
	}

	public String getUserUpdated() {
		return userUpdated;
	}

	public void setUserUpdated(String userUpdated) {
		this.userUpdated = userUpdated;
	}

}
