package org.abacus.transaction.shared.event;

import org.abacus.common.shared.event.DeletedEvent;
import org.abacus.transaction.shared.entity.TraDetailEntity;

public class DeleteDetailEvent<D extends TraDetailEntity<D>> extends DeletedEvent {
	
	private D detail;

	public DeleteDetailEvent(D detail) {
		this.detail = detail;
	}

	public D getDetail() {
		return detail;
	}

	public void setDetail(D detail) {
		this.detail = detail;
	}

}
