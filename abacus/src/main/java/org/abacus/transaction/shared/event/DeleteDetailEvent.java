package org.abacus.transaction.shared.event;

import org.abacus.common.shared.event.DeletedEvent;
import org.abacus.transaction.shared.entity.TraDetailEntity;

public class DeleteDetailEvent<D extends TraDetailEntity<D>> extends DeletedEvent {
	
	private D detail;
	private Boolean isOppositeCreate;

	public DeleteDetailEvent(D detail) {
		this.detail = detail;
		this.isOppositeCreate = false;
	}

	public DeleteDetailEvent(D detail, Boolean isOppositeCreate) {
		this.detail = detail;
		this.isOppositeCreate = isOppositeCreate;
	}

	public D getDetail() {
		return detail;
	}

	public void setDetail(D detail) {
		this.detail = detail;
	}

	public Boolean getIsOppositeCreate() {
		return isOppositeCreate;
	}

	public void setIsOppositeCreate(Boolean isOppositeCreate) {
		this.isOppositeCreate = isOppositeCreate;
	}

}
