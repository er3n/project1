package org.abacus.transaction.shared.event;

import org.abacus.common.shared.event.CreatedEvent;
import org.abacus.transaction.shared.entity.StkDetailEntity;

public class DetailCreatedEvent extends CreatedEvent {
	
	public StkDetailEntity detail;

	public DetailCreatedEvent(StkDetailEntity detail) {
		this.detail = detail;
	}

	public StkDetailEntity getDetail() {
		return detail;
	}

	public void setDetail(StkDetailEntity detail) {
		this.detail = detail;
	}
	
	

}
