package org.abacus.user.shared.event;

import java.util.List;

import org.abacus.common.shared.event.CreatedEvent;
import org.abacus.user.shared.entity.SecGroupEntity;
import org.abacus.user.shared.entity.SecUserEntity;

public class CreateUserEvent extends CreatedEvent {

	private SecUserEntity user;
	private List<SecGroupEntity> userGroups;
	private String userCreated;

	public CreateUserEvent(SecUserEntity user, List<SecGroupEntity> userGroups, String userCreated) {
		this.user = user;
		this.userGroups = userGroups;
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

}
