package org.abacus.transaction.shared.event;

import org.abacus.common.shared.event.CreatedEvent;
import org.abacus.transaction.shared.entity.StkDetailEntity;

public class CreateDetailEvent extends CreatedEvent {

	public StkDetailEntity detail;
	private String user;

	public CreateDetailEvent(StkDetailEntity detail, String user) {
		this.detail = detail;
		this.user = user;
	}

	public StkDetailEntity getDetail() {
		return detail;
	}

	public void setDetail(StkDetailEntity detail) {
		this.detail = detail;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

}