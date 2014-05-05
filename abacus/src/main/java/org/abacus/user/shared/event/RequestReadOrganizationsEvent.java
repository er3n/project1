package org.abacus.user.shared.event;

import org.abacus.common.shared.event.RequestReadEvent;

public class RequestReadOrganizationsEvent extends RequestReadEvent {

	private String username;
	private String organizationid;

	public RequestReadOrganizationsEvent(String username, String organizationid) {
		this.username = username;
		this.organizationid = organizationid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOrganizationid() {
		return organizationid;
	}

	public void setOrganizationid(String organization) {
		this.organizationid = organization;
	}

}
