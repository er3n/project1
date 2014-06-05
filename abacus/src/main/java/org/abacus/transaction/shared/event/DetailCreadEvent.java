package org.abacus.transaction.shared.event;

import org.abacus.common.shared.event.CreatedEvent;
import org.abacus.transaction.shared.entity.TraDetailEntity;

public class DetailCreadEvent extends CreatedEvent {
	
	public TraDetailEntity detail;

	public DetailCreadEvent(TraDetailEntity detail) {
		this.detail = detail;
	}

	public TraDetailEntity getDetail() {
		return detail;
	}

	public void setDetail(TraDetailEntity detail) {
		this.detail = detail;
	}
	
	

}
