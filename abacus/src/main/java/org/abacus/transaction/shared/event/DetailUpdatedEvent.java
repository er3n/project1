package org.abacus.transaction.shared.event;

import org.abacus.common.shared.event.UpdatedEvent;
import org.abacus.transaction.shared.entity.StkDetailEntity;

public class DetailUpdatedEvent extends UpdatedEvent {

	private StkDetailEntity detail;

	public DetailUpdatedEvent(StkDetailEntity detail) {
		this.detail = detail;
	}

	public StkDetailEntity getDetail() {
		return detail;
	}

	public void setDetail(StkDetailEntity detail) {
		this.detail = detail;
	}

	
}
