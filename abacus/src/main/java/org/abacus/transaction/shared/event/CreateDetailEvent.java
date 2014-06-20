package org.abacus.transaction.shared.event;

import org.abacus.common.shared.event.CreatedEvent;
import org.abacus.transaction.shared.entity.TraDetailEntity;

public class CreateDetailEvent<T extends TraDetailEntity> extends CreatedEvent {

	public T detail;
	private String user;

	public CreateDetailEvent(T detail, String user) {
		this.detail = detail;
		this.user = user;
	}

	public T getDetail() {
		return detail;
	}

	public void setDetail(T detail) {
		this.detail = detail;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

}