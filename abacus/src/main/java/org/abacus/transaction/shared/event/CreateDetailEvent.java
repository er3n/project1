package org.abacus.transaction.shared.event;

import org.abacus.common.shared.event.CreatedEvent;
import org.abacus.transaction.shared.entity.TraDetailEntity;

public class CreateDetailEvent<D extends TraDetailEntity> extends CreatedEvent {

	public D detail;
	private String user;
	private Boolean isOppositeCreate;

	public CreateDetailEvent(D detail, String user) {
		this.detail = detail;
		this.user = user;
		this.isOppositeCreate = false;
	}

	public CreateDetailEvent(D detail, Boolean isOppositeCreate) {
		this.detail = detail;
		this.user = detail.getUserCreated();
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