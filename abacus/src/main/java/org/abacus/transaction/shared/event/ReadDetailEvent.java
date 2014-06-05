package org.abacus.transaction.shared.event;

import java.util.List;

import org.abacus.common.shared.event.ReadEvent;
import org.abacus.transaction.shared.entity.TraDetailEntity;

public class ReadDetailEvent extends ReadEvent {

	List<TraDetailEntity> details;

	public ReadDetailEvent(List<TraDetailEntity> details) {
		this.details = details;
	}

	public List<TraDetailEntity> getDetails() {
		return details;
	}

	public void setDetails(List<TraDetailEntity> details) {
		this.details = details;
	}

}
