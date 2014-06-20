package org.abacus.transaction.shared.event;

import org.abacus.common.shared.event.CreatedEvent;
import org.abacus.transaction.shared.entity.TraDetailEntity;

public class DetailCreatedEvent<T extends TraDetailEntity> extends CreatedEvent {

	public T detail;

	public DetailCreatedEvent(T detail) {
		this.detail = detail;
	}

	public T getDetail() {
		return detail;
	}

	public void setDetail(T detail) {
		this.detail = detail;
	}

}
