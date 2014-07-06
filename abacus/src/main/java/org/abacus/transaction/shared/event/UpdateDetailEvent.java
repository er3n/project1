package org.abacus.transaction.shared.event;

import org.abacus.common.shared.event.UpdateEvent;
import org.abacus.transaction.shared.entity.TraDetailEntity;

public class UpdateDetailEvent<D extends TraDetailEntity<D>> extends UpdateEvent {

	private D detail;
	private String user;

	public UpdateDetailEvent(D detail, String user) {
		this.detail = detail;
		this.user = user;
	}

	public UpdateDetailEvent(D detail) {
		this.detail = detail;
		this.user = detail.getUserUpdated();
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
