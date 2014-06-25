package org.abacus.transaction.shared.event;

import java.util.List;

import org.abacus.common.shared.event.ReadEvent;
import org.abacus.transaction.shared.entity.TraDetailEntity;

public class ReadDetailEvent<D extends TraDetailEntity<D>> extends ReadEvent {

	List<D> details;

	public ReadDetailEvent(List<D> details) {
		this.details = details;
	}

	public List<D> getDetails() {
		return details;
	}

	public void setDetails(List<D> details) {
		this.details = details;
	}

}
