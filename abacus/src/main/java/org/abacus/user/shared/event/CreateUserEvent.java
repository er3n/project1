package org.abacus.user.shared.event;

import java.util.List;

import org.abacus.common.shared.event.CreatedEvent;
import org.abacus.organization.shared.entity.OrganizationEntity;
import org.abacus.user.shared.entity.SecGroupEntity;
import org.abacus.user.shared.entity.SecUserEntity;

public class CreateUserEvent extends CreatedEvent {

	private SecUserEntity user;
	private List<SecGroupEntity> userGroups;
	private List<OrganizationEntity> organizationList;
	private String userCreated;

	public CreateUserEvent(SecUserEntity user, List<SecGroupEntity> userGroups,
			List<OrganizationEntity> organizationList, String userCreated) {
		this.user = user;
		this.userGroups = userGroups;
		this.organizationList = organizationList;
		this.userCreated = userCreated;
	}

	public SecUserEntity getUser() {
		return user;
	}

	public void setUser(SecUserEntity user) {
		this.user = user;
	}

	public List<SecGroupEntity> getUserGroups() {
		return userGroups;
	}

	public void setUserGroups(List<SecGroupEntity> userGroups) {
		this.userGroups = userGroups;
	}

	public String getUserCreated() {
		return userCreated;
	}

	public void setUserCreated(String userCreated) {
		this.userCreated = userCreated;
	}

	public List<OrganizationEntity> getOrganizationList() {
		return organizationList;
	}

	public void setOrganizationList(List<OrganizationEntity> organizationList) {
		this.organizationList = organizationList;
	}

}
