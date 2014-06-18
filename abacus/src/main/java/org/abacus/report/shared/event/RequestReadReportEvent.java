package org.abacus.report.shared.event;

import org.abacus.common.shared.event.RequestReadEvent;
import org.abacus.report.shared.holder.ReportSearchCriteria;

public class RequestReadReportEvent extends RequestReadEvent {

	private ReportSearchCriteria reportSearchCriteria;
	private String organization;
	private String fiscalYearId;

	public RequestReadReportEvent(ReportSearchCriteria reportSearchCriteria, String organization, String fiscalYearId) {
		this.reportSearchCriteria = reportSearchCriteria;
		this.organization = organization;
		this.fiscalYearId = fiscalYearId;
	}

	public ReportSearchCriteria getDocumentSearchCriteria() {
		return reportSearchCriteria;
	}

	public void setDocumentSearchCriteria(ReportSearchCriteria reportSearchCriteria) {
		this.reportSearchCriteria = reportSearchCriteria;
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
