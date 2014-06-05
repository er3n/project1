package org.abacus.transaction.shared.event;

import org.abacus.common.shared.event.UpdatedEvent;
import org.abacus.transaction.shared.entity.TraDetailEntity;

public class DetailUpdatedEvent extends UpdatedEvent {

	private TraDetailEntity detail;

	public DetailUpdatedEvent(TraDetailEntity detail) {
		this.detail = detail;
	}

	public TraDetailEntity getDetail() {
		return detail;
	}

	public void setDetail(TraDetailEntity detail) {
		this.detail = detail;
	}

	
}
