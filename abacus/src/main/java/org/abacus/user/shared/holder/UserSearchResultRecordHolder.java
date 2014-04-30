package org.abacus.user.shared.holder;

import java.io.Serializable;
import java.util.List;

import org.abacus.user.shared.entity.SecGroupEntity;
import org.abacus.user.shared.entity.SecUserEntity;

public class UserSearchResultRecordHolder implements Serializable {

	private SecUserEntity user;
	private List<SecGroupEntity> groupList;

	public SecUserEntity getUser() {
		return user;
	}

	public void setUser(SecUserEntity user) {
		this.user = user;
	}

	public List<SecGroupEntity> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<SecGroupEntity> groupList) {
		this.groupList = groupList;
	}

}
