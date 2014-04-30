package org.abacus.user.shared.event;

import org.abacus.common.shared.event.CreatedEvent;
import org.abacus.user.shared.entity.SecUserEntity;

public class UserCreatedEvent extends CreatedEvent {

	private SecUserEntity secUser;

	public UserCreatedEvent(SecUserEntity secUser) {
		this.secUser = secUser;
	}

	public SecUserEntity getSecUser() {
		return secUser;
	}

	public void setSecUser(SecUserEntity secUser) {
		this.secUser = secUser;
	}

}
