package org.abacus.transaction.shared.event;

import org.abacus.common.shared.event.DeletedEvent;

public class DeleteDetailEvent extends DeletedEvent {
	private Long detailId;

	public DeleteDetailEvent(Long detailId) {
		this.detailId = detailId;
	}

	public Long getDetailId() {
		return detailId;
	}

	public void setDetailId(Long detailId) {
		this.detailId = detailId;
	}

}
