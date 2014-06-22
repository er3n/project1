package org.abacus.transaction.shared.event;

import org.abacus.common.shared.event.UpdateEvent;
import org.abacus.transaction.shared.entity.TraDetailEntity;

public class UpdateDetailEvent<T extends TraDetailEntity> extends UpdateEvent {

	private T detail;
	private String user;

	public UpdateDetailEvent(T detail, String user) {
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
