package org.abacus.user.shared.event;

import org.abacus.common.shared.event.RequestReadEvent;

public class RequestReadOrganizationsEvent extends RequestReadEvent {

	private String username;
	private String companyid;

	public RequestReadOrganizationsEvent(String username, String companyid) {
		this.username = username;
		this.companyid = companyid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCompanyid() {
		return companyid;
	}

	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}

}
