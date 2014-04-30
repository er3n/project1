package org.abacus.common.shared.event;


public class UpdatedEvent implements Event {

	protected String userUpdated;

	public String getUserUpdated() {
		return userUpdated;
	}

	public void setUserUpdated(String userUpdated) {
		this.userUpdated = userUpdated;
	}

}
