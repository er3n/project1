package org.abacus.transaction.shared.event;

import org.abacus.common.shared.event.UpdatedEvent;
import org.abacus.transaction.shared.entity.TraDetailEntity;

public class DetailUpdatedEvent<D extends TraDetailEntity<D>> extends UpdatedEvent {

	private D detail;

	public DetailUpdatedEvent(D detail) {
		this.detail = detail;
	}

	public D getDetail() {
		return detail;
	}

	public void setDetail(D detail) {
		this.detail = detail;
	}

	
}
