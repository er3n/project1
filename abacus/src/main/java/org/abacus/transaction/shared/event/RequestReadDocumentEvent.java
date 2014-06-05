package org.abacus.transaction.shared.event;

import org.abacus.common.shared.event.RequestReadEvent;
import org.abacus.transaction.shared.holder.SearchDocumentCriteria;

public class RequestReadDocumentEvent extends RequestReadEvent {

	private SearchDocumentCriteria criteria;

	public RequestReadDocumentEvent(SearchDocumentCriteria criteria) {
		this.criteria = criteria;
	}

	public SearchDocumentCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(SearchDocumentCriteria criteria) {
		this.criteria = criteria;
	}

}
