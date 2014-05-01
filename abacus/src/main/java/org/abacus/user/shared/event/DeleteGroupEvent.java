package org.abacus.user.shared.event;

import org.abacus.common.shared.event.DeletedEvent;

public class DeleteGroupEvent extends DeletedEvent {

	private Long groupId;

	public DeleteGroupEvent(Long groupId) {
		this.groupId = groupId;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

}
