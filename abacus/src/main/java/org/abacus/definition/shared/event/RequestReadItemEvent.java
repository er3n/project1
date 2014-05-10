package org.abacus.definition.shared.event;

import org.abacus.common.shared.event.RequestReadEvent;
import org.abacus.definition.shared.holder.ItemSearchCriteria;

public class RequestReadItemEvent extends RequestReadEvent {

	private ItemSearchCriteria itemSearchCriteria;

	public RequestReadItemEvent(ItemSearchCriteria itemSearchCriteria) {
		this.itemSearchCriteria = itemSearchCriteria;
	}

	public ItemSearchCriteria getItemSearchCriteria() {
		return itemSearchCriteria;
	}

	public void setItemSearchCriteria(ItemSearchCriteria itemSearchCriteria) {
		this.itemSearchCriteria = itemSearchCriteria;
	}

}
