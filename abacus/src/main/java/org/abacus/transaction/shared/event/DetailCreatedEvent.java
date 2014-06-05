package org.abacus.transaction.shared.event;

import org.abacus.common.shared.event.CreatedEvent;
import org.abacus.transaction.shared.entity.TraDetailEntity;

public class DetailCreatedEvent extends CreatedEvent {
	
	public TraDetailEntity detail;

	public DetailCreatedEvent(TraDetailEntity detail) {
		this.detail = detail;
	}

	public TraDetailEntity getDetail() {
		return detail;
	}

	public void setDetail(TraDetailEntity detail) {
		this.detail = detail;
	}
	
	

}
