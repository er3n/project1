package org.abacus.user.shared.event;

import org.abacus.common.shared.event.RequestReadEvent;

public class RequestReadCompaniesEvent extends RequestReadEvent {

	private String username;
	private String company;

	public RequestReadCompaniesEvent(String username, String company) {
		this.username = username;
		this.company = company;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

}
