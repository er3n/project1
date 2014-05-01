package org.abacus.user.shared.holder;

import java.io.Serializable;
import java.util.List;

import org.abacus.user.shared.entity.CompanyEntity;
import org.abacus.user.shared.entity.SecGroupEntity;

public class SearchUserCriteria implements Serializable {

	private String username;
	private List<SecGroupEntity> groupList;
	private CompanyEntity company;

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

	public CompanyEntity getCompany() {
		return company;
	}

	public void setCompany(CompanyEntity company) {
		this.company = company;
	}

}
