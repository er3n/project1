package org.abacus.transaction.shared.event;

import org.abacus.common.shared.event.UpdateEvent;
import org.abacus.transaction.shared.entity.TraDetailEntity;

public class UpdateDetailEvent<D extends TraDetailEntity> extends UpdateEvent {

	private D detail;
	private String user;
	private Boolean isOppositeCreate;

	public UpdateDetailEvent(D detail, String user) {
		this.detail = detail;
		this.user = user;
		this.isOppositeCreate = false;
	}

	public UpdateDetailEvent(D detail, Boolean isOppositeCreate) {
		this.detail = detail;
		this.user = detail.getUserUpdated();
		this.isOppositeCreate = isOppositeCreate;
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

	public Boolean getIsOppositeCreate() {
		return isOppositeCreate;
	}

	public void setIsOppositeCreate(Boolean isOppositeCreate) {
		this.isOppositeCreate = isOppositeCreate;
	}

}
