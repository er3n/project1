package org.abacus.transaction.shared.event;

import java.util.List;

import org.abacus.common.shared.event.ReadEvent;
import org.abacus.transaction.shared.entity.StkDetailEntity;

public class ReadDetailEvent extends ReadEvent {

	List<StkDetailEntity> details;

	public ReadDetailEvent(List<StkDetailEntity> details) {
		this.details = details;
	}

	public List<StkDetailEntity> getDetails() {
		return details;
	}

	public void setDetails(List<StkDetailEntity> details) {
		this.details = details;
	}

}
