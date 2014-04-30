package org.abacus.user.shared.event;

import org.abacus.common.shared.event.RequestReadEvent;
import org.abacus.user.shared.holder.SearchUserCriteria;

public class RequestReadUserEvent extends RequestReadEvent {

	private SearchUserCriteria criteria;

	public RequestReadUserEvent(SearchUserCriteria searchUserCriteria) {
		this.criteria = searchUserCriteria;
	}

	public SearchUserCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(SearchUserCriteria criteria) {
		this.criteria = criteria;
	}

}
