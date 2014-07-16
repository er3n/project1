package org.abacus.transaction.shared.event;

import org.abacus.common.shared.event.RequestReadEvent;
import org.abacus.organization.shared.entity.FiscalYearEntity;
import org.abacus.organization.shared.entity.OrganizationEntity;
import org.abacus.transaction.shared.entity.TraDocumentEntity;
import org.abacus.transaction.shared.holder.TraDocumentSearchCriteria;

public class RequestReadDocumentEvent<T extends TraDocumentEntity> extends RequestReadEvent {

	private TraDocumentSearchCriteria documentSearchCriteria;
	private OrganizationEntity organization;
	private FiscalYearEntity fiscalYearId;

	public RequestReadDocumentEvent(TraDocumentSearchCriteria documentSearchCriteria, OrganizationEntity organization, FiscalYearEntity fiscalYearId) {
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

	public FiscalYearEntity getFiscalYearId() {
		return fiscalYearId;
	}

	public void setFiscalYearId(FiscalYearEntity fiscalYearId) {
		this.fiscalYearId = fiscalYearId;
	}

	public OrganizationEntity getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationEntity organization) {
		this.organization = organization;
	}

}
