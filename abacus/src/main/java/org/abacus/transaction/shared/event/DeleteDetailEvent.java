package org.abacus.transaction.shared.event;

import org.abacus.common.shared.event.DeletedEvent;
import org.abacus.transaction.shared.entity.TraDetailEntity;

public class DeleteDetailEvent<T extends TraDetailEntity> extends DeletedEvent {
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
