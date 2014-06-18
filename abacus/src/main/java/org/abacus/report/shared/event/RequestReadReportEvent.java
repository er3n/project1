package org.abacus.report.shared.event;

import org.abacus.common.shared.event.RequestReadEvent;
import org.abacus.report.shared.holder.ReportSearchCriteria;

public class RequestReadReportEvent extends RequestReadEvent {

	private ReportSearchCriteria reportSearchCriteria;

	public RequestReadReportEvent(ReportSearchCriteria reportSearchCriteria) {
		this.reportSearchCriteria = reportSearchCriteria;
	}

	public ReportSearchCriteria getReportSearchCriteria() {
		return reportSearchCriteria;
	}

	public void setReportSearchCriteria(ReportSearchCriteria reportSearchCriteria) {
		this.reportSearchCriteria = reportSearchCriteria;
	}

}
