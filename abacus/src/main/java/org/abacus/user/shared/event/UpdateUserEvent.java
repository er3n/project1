package org.abacus.user.shared.event;

import java.util.List;

import org.abacus.common.shared.event.UpdatedEvent;
import org.abacus.user.shared.entity.SecGroupEntity;
import org.abacus.user.shared.entity.SecUserEntity;

public class UpdateUserEvent extends UpdatedEvent {

	private SecUserEntity user;
	private List<SecGroupEntity> userGroupList;
	private String userUpdated;

	public UpdateUserEvent(SecUserEntity user, List<SecGroupEntity> userGroupList, String userUpdated) {
		this.user = user;
		this.userGroupList = userGroupList;
		this.userUpdated = userUpdated;
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

}
