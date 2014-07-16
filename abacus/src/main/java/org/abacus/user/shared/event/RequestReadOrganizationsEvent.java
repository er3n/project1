package org.abacus.user.shared.event;

import org.abacus.common.shared.event.RequestReadEvent;
import org.abacus.organization.shared.entity.OrganizationEntity;

public class RequestReadOrganizationsEvent extends RequestReadEvent {

	private String username;
	private OrganizationEntity organization;

	public RequestReadOrganizationsEvent(String username, OrganizationEntity organization) {
		this.username = username;
		this.organization = organization;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public OrganizationEntity getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationEntity organization) {
		this.organization = organization;
	}

}
