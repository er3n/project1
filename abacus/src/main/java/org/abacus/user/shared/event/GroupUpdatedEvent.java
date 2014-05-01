package org.abacus.user.shared.event;

import org.abacus.common.shared.event.UpdatedEvent;
import org.abacus.user.shared.entity.SecGroupEntity;

public class GroupUpdatedEvent extends UpdatedEvent {

	private SecGroupEntity group;

	public GroupUpdatedEvent(SecGroupEntity group) {
		this.group = group;
	}

	public SecGroupEntity getGroup() {
		return group;
	}

	public void setGroup(SecGroupEntity group) {
		this.group = group;
	}

}
