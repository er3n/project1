package org.abacus.transaction.shared.event;

import org.abacus.common.shared.event.RequestReadEvent;
import org.abacus.organization.shared.entity.FiscalYearEntity;
import org.abacus.organization.shared.entity.OrganizationEntity;
import org.abacus.transaction.shared.entity.TraDocumentEntity;
import org.abacus.transaction.shared.holder.TraDocumentSearchCriteria;

public class RequestReadDocumentEvent<T extends TraDocumentEntity> extends RequestReadEvent {

	private TraDocumentSearchCriteria documentSearchCriteria;
	private OrganizationEntity organization;
	private FiscalYearEntity fiscalYear2;

	public RequestReadDocumentEvent(TraDocumentSearchCriteria documentSearchCriteria, OrganizationEntity organization, FiscalYearEntity fiscalYear2) {
		this.documentSearchCriteria = documentSearchCriteria;
		this.organization = organization;
		this.fiscalYear2 = fiscalYear2;
	}

	public TraDocumentSearchCriteria getDocumentSearchCriteria() {
		return documentSearchCriteria;
	}

	public void setDocumentSearchCriteria(TraDocumentSearchCriteria documentSearchCriteria) {
		this.documentSearchCriteria = documentSearchCriteria;
	}

	public FiscalYearEntity getFiscalYear2() {
		return fiscalYear2;
	}

	public void setFiscalYear2(FiscalYearEntity fiscalYear2) {
		this.fiscalYear2 = fiscalYear2;
	}

	public OrganizationEntity getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationEntity organization) {
		this.organization = organization;
	}

}
