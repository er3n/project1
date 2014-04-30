package org.abacus.user.shared.event;

import java.util.List;

import org.abacus.common.shared.event.ReadEvent;
import org.abacus.user.shared.entity.SecUserEntity;

public class ReadUserEvent extends ReadEvent {

	private List<SecUserEntity> userEntityList;

	public ReadUserEvent(List<SecUserEntity> userEntityList) {
		this.userEntityList = userEntityList;
	}

	public List<SecUserEntity> getUserEntityList() {
		return userEntityList;
	}

	public void setUserEntityList(List<SecUserEntity> userEntityList) {
		this.userEntityList = userEntityList;
	}

}
