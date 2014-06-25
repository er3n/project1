package org.abacus.transaction.shared.event;

import org.abacus.common.shared.event.CreatedEvent;
import org.abacus.transaction.shared.entity.TraDetailEntity;

public class CreateDetailEvent<D extends TraDetailEntity<D>> extends CreatedEvent {

	public D detail;
	private String user;

	public CreateDetailEvent(D detail, String user) {
		this.detail = detail;
		this.user = user;
	}

	public D getDetail() {
		return detail;
	}

	public void setDetail(D detail) {
		this.detail = detail;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

}