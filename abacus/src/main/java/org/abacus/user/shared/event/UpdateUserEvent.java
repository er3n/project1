package org.abacus.user.shared.event;

import java.util.List;

import org.abacus.common.shared.event.UpdatedEvent;
import org.abacus.organization.shared.entity.OrganizationEntity;
import org.abacus.user.shared.entity.SecGroupEntity;
import org.abacus.user.shared.entity.SecUserEntity;

public class UpdateUserEvent extends UpdatedEvent {

	private SecUserEntity user;
	private List<SecGroupEntity> userGroupList;
	private List<OrganizationEntity> organizationList;
	private String userUpdated;

	public UpdateUserEvent(SecUserEntity user,
			List<SecGroupEntity> userGroupList,List<OrganizationEntity> organizationList, String userUpdated) {
		this.user = user;
		this.userGroupList = userGroupList;
		this.userUpdated = userUpdated;
		this.organizationList = organizationList;
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

	public List<OrganizationEntity> getOrganizationList() {
		return organizationList;
	}

	public void setOrganizationList(List<OrganizationEntity> organizationList) {
		this.organizationList = organizationList;
	}

}
