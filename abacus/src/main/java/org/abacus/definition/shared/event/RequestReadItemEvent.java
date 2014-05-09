package org.abacus.definition.shared.event;


public class RequestReadItemEvent {

	private String organizationId;


	public RequestReadItemEvent(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

}
