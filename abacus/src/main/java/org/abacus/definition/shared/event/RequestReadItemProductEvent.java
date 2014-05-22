package org.abacus.definition.shared.event;

import org.abacus.common.shared.event.ReadEvent;

public class RequestReadItemProductEvent extends ReadEvent {

	private Long itemId;

	public RequestReadItemProductEvent(Long itemId) {
		this.itemId = itemId;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

}
