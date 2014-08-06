package org.abacus.budget.shared.event;

import org.abacus.budget.shared.entity.BudDetailEntity;
import org.abacus.budget.shared.entity.BudDocumentEntity;
import org.abacus.common.security.SecUser;
import org.abacus.common.shared.event.CreatedEvent;

public class CreateBudgetDetailEvent extends CreatedEvent {

	private BudDocumentEntity document;
	private BudDetailEntity detail;
	private SecUser user;

	public CreateBudgetDetailEvent(BudDocumentEntity document, BudDetailEntity detail, SecUser currentUser) {
		this.document = document;
		this.detail = detail;
		this.user = currentUser;
	}

	public BudDocumentEntity getDocument() {
		return document;
	}

	public void setDocument(BudDocumentEntity document) {
		this.document = document;
	}

	public BudDetailEntity getDetail() {
		return detail;
	}

	public void setDetail(BudDetailEntity detail) {
		this.detail = detail;
	}

	public SecUser getUser() {
		return user;
	}

	public void setUser(SecUser user) {
		this.user = user;
	}

}
