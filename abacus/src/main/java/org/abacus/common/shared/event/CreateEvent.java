package org.abacus.common.shared.event;


public class CreateEvent implements Event {

	protected String userCreated;

	public String getUserCreated() {
		return userCreated;
	}

	public void setUserCreated(String userCreated) {
		this.userCreated = userCreated;
	}

}
