package org.abacus.definition.shared.event;

import org.abacus.common.shared.event.UpdatedEvent;
import org.abacus.definition.shared.entity.DefItemEntity;

public class UpdateItemEvent extends UpdatedEvent {

	private DefItemEntity item;
	private String userUpdated;
	private String organization;

	public UpdateItemEvent(DefItemEntity item, String userUpdated, String organization) {
		this.item = item;
		this.userUpdated = userUpdated;
		this.organization = organization;
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

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

}
