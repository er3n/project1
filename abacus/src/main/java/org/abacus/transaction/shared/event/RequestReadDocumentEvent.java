package org.abacus.transaction.shared.event;

import org.abacus.common.shared.event.RequestReadEvent;
import org.abacus.transaction.shared.entity.TraDocumentEntity;
import org.abacus.transaction.shared.holder.TraDocumentSearchCriteria;

public class RequestReadDocumentEvent<T extends TraDocumentEntity> extends RequestReadEvent {

	private TraDocumentSearchCriteria documentSearchCriteria;
	private String organization;
	private String fiscalYearId;

	public RequestReadDocumentEvent(TraDocumentSearchCriteria documentSearchCriteria, String organization, String fiscalYearId) {
		this.documentSearchCriteria = documentSearchCriteria;
		this.organization = organization;
		this.fiscalYearId = fiscalYearId;
	}

	public TraDocumentSearchCriteria getDocumentSearchCriteria() {
		return documentSearchCriteria;
	}

	public void setDocumentSearchCriteria(TraDocumentSearchCriteria documentSearchCriteria) {
		this.documentSearchCriteria = documentSearchCriteria;
	}

	public String getFiscalYearId() {
		return fiscalYearId;
	}

	public void setFiscalYearId(String fiscalYearId) {
		this.fiscalYearId = fiscalYearId;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

}
