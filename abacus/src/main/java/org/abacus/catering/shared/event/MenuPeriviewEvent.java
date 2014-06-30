package org.abacus.catering.shared.event;

import java.util.List;

import org.abacus.transaction.shared.entity.StkDetailEntity;
import org.abacus.transaction.shared.entity.StkDocumentEntity;
import org.abacus.transaction.shared.entity.TraDocumentEntity;

public class MenuPeriviewEvent {

	private StkDocumentEntity document;
	private List<StkDetailEntity> details;

	public MenuPeriviewEvent(StkDocumentEntity document, List<StkDetailEntity> details) {
		this.document = document;
		this.details = details;
	}

	public StkDocumentEntity getDocument() {
		return document;
	}

	public void setDocument(StkDocumentEntity document) {
		this.document = document;
	}

	public List<StkDetailEntity> getDetails() {
		return details;
	}

	public void setDetails(List<StkDetailEntity> details) {
		this.details = details;
	}

}
