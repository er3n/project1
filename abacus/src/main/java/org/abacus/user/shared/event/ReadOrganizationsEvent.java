package org.abacus.user.shared.event;

import java.util.List;

import org.abacus.common.shared.event.ReadEvent;
import org.abacus.organization.shared.entity.OrganizationEntity;

public class ReadOrganizationsEvent extends ReadEvent {

	private List<OrganizationEntity> companyList;

	public ReadOrganizationsEvent(List<OrganizationEntity> companyList) {
		this.companyList = companyList;
	}

	public List<OrganizationEntity> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(List<OrganizationEntity> companyList) {
		this.companyList = companyList;
	}

}
