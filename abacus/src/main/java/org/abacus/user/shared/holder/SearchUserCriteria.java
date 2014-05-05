package org.abacus.user.shared.holder;

import java.io.Serializable;
import java.util.List;

import org.abacus.organization.shared.entity.OrganizationEntity;
import org.abacus.user.shared.entity.SecGroupEntity;

public class SearchUserCriteria implements Serializable {

	private String username;
	private List<SecGroupEntity> groupList;
	private OrganizationEntity company;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<SecGroupEntity> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<SecGroupEntity> groupList) {
		this.groupList = groupList;
	}

	public OrganizationEntity getCompany() {
		return company;
	}

	public void setCompany(OrganizationEntity company) {
		this.company = company;
	}

}
