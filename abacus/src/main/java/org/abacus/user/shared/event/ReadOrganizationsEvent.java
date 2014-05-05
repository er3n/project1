package org.abacus.user.shared.event;

import java.util.List;

import org.abacus.common.shared.event.ReadEvent;
import org.abacus.organization.shared.entity.OrganizationEntity;

public class ReadOrganizationsEvent extends ReadEvent {

	private List<OrganizationEntity> organizationList;

	public ReadOrganizationsEvent(List<OrganizationEntity> organizationList) {
		this.organizationList = organizationList;
	}

	public List<OrganizationEntity> getOrganizationList() {
		return organizationList;
	}

	public void setOrganizationList(List<OrganizationEntity> organizationList) {
		this.organizationList = organizationList;
	}

}
