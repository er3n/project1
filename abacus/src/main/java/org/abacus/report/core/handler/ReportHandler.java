package org.abacus.report.core.handler;

import java.io.Serializable;

import org.abacus.report.shared.event.ReadReportEvent;
import org.abacus.report.shared.event.RequestReadReportEvent;

public interface ReportHandler extends Serializable{
	
	ReadReportEvent getStkState(RequestReadReportEvent requestReadReportEvent);

	ReadReportEvent getStkDetail(RequestReadReportEvent requestReadReportEvent);
		
}
