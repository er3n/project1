package org.abacus.transaction.shared.event;

import org.abacus.common.shared.event.CreatedEvent;
import org.abacus.transaction.shared.entity.TraDetailEntity;

public class CreateDetailEvent extends CreatedEvent {

	public TraDetailEntity detail;
	private String user;

	public CreateDetailEvent(TraDetailEntity detail, String user) {
		this.detail = detail;
		this.user = user;
	}

	public TraDetailEntity getDetail() {
		return detail;
	}

	public void setDetail(TraDetailEntity detail) {
		this.detail = detail;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

}