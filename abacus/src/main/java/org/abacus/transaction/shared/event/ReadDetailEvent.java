package org.abacus.transaction.shared.event;

import java.util.List;

import org.abacus.common.shared.event.ReadEvent;
import org.abacus.transaction.shared.entity.TraDetailEntity;

public class ReadDetailEvent<T extends TraDetailEntity> extends ReadEvent {

	List<T> details;

	public ReadDetailEvent(List<T> details) {
		this.details = details;
	}

	public List<T> getDetails() {
		return details;
	}

	public void setDetails(List<T> details) {
		this.details = details;
	}

}
