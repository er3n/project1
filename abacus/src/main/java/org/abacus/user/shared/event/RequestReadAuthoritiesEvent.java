package org.abacus.user.shared.event;

import org.abacus.common.shared.event.RequestReadEvent;

public class RequestReadAuthoritiesEvent extends RequestReadEvent {

	private Long groupId;

	public RequestReadAuthoritiesEvent(Long groupId) {
		this.groupId = groupId;
	}

	public RequestReadAuthoritiesEvent() {
		super();
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

}
