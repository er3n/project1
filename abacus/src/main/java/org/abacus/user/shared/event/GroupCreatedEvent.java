package org.abacus.user.shared.event;

import org.abacus.user.shared.entity.SecGroupEntity;

public class GroupCreatedEvent {

	private SecGroupEntity group;

	public GroupCreatedEvent(SecGroupEntity group) {
		this.group = group;
	}

	public SecGroupEntity getGroup() {
		return group;
	}

	public void setGroup(SecGroupEntity group) {
		this.group = group;
	}

}
