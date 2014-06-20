package org.abacus.transaction.shared.event;

import org.abacus.common.shared.event.UpdatedEvent;
import org.abacus.transaction.shared.entity.TraDetailEntity;

public class DetailUpdatedEvent<T extends TraDetailEntity> extends UpdatedEvent {

	private T detail;

	public DetailUpdatedEvent(T detail) {
		this.detail = detail;
	}

	public T getDetail() {
		return detail;
	}

	public void setDetail(T detail) {
		this.detail = detail;
	}

	
}
