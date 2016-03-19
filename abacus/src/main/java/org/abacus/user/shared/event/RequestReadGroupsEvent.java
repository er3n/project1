package org.abacus.user.shared.event;

import org.abacus.common.shared.event.RequestReadEvent;

public class RequestReadGroupsEvent extends RequestReadEvent {

	private String username;
	private Boolean isRoot;

	public RequestReadGroupsEvent(String username, Boolean isRoot) {
		super();
		this.username = username;
		this.isRoot = isRoot;
	}

	public Boolean getIsRoot() {
		return isRoot;
	}

	public void setIsRoot(Boolean isRoot) {
		this.isRoot = isRoot;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
