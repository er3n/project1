package org.abacus.user.shared.event;

import org.abacus.common.shared.event.UpdatedEvent;
import org.abacus.user.shared.entity.SecUserEntity;

public class UserUpdatedEvent extends UpdatedEvent {

	private SecUserEntity user;

	public UserUpdatedEvent(SecUserEntity updatingUser) {
		this.user = updatingUser;
	}

	public SecUserEntity getUser() {
		return user;
	}

	public void setUser(SecUserEntity user) {
		this.user = user;
	}

}
