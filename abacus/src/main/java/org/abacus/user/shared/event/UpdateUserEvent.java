package org.abacus.user.shared.event;

import java.util.List;

import org.abacus.common.shared.event.UpdatedEvent;
import org.abacus.organization.shared.entity.CompanyEntity;
import org.abacus.user.shared.entity.SecGroupEntity;
import org.abacus.user.shared.entity.SecUserEntity;

public class UpdateUserEvent extends UpdatedEvent {

	private SecUserEntity user;
	private List<SecGroupEntity> userGroupList;
	private List<CompanyEntity> companies;
	private String userUpdated;

	public UpdateUserEvent(SecUserEntity user,
			List<SecGroupEntity> userGroupList,List<CompanyEntity> companies, String userUpdated) {
		this.user = user;
		this.userGroupList = userGroupList;
		this.userUpdated = userUpdated;
		this.companies = companies;
	}

	public SecUserEntity getUser() {
		return user;
	}

	public void setUser(SecUserEntity user) {
		this.user = user;
	}

	public List<SecGroupEntity> getUserGroupList() {
		return userGroupList;
	}

	public void setUserGroupList(List<SecGroupEntity> userGroupList) {
		this.userGroupList = userGroupList;
	}

	public String getUserUpdated() {
		return userUpdated;
	}

	public void setUserUpdated(String userUpdated) {
		this.userUpdated = userUpdated;
	}

	public List<CompanyEntity> getCompanies() {
		return companies;
	}

	public void setCompanies(List<CompanyEntity> companies) {
		this.companies = companies;
	}

}
