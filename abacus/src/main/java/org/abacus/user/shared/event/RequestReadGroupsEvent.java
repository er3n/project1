package org.abacus.user.shared.event;

import org.abacus.common.shared.event.RequestReadEvent;

public class RequestReadGroupsEvent extends RequestReadEvent {

	private String username;

	public RequestReadGroupsEvent() {
		super();
	}

	public RequestReadGroupsEvent(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
