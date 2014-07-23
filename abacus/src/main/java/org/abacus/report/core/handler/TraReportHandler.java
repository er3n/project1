package org.abacus.report.core.handler;

import java.io.Serializable;

import org.abacus.report.shared.event.ReadReportEvent;
import org.abacus.report.shared.event.RequestReadReportEvent;
import org.abacus.transaction.shared.entity.FinDetailEntity;
import org.abacus.transaction.shared.entity.StkDetailEntity;

public interface TraReportHandler extends Serializable{
	
	ReadReportEvent<StkDetailEntity> getStkState(RequestReadReportEvent requestReadReportEvent);

	ReadReportEvent<StkDetailEntity> getStkDetail(RequestReadReportEvent requestReadReportEvent);

	ReadReportEvent<FinDetailEntity> getFinState(RequestReadReportEvent requestReadReportEvent);

	ReadReportEvent<FinDetailEntity> getFinDetail(RequestReadReportEvent requestReadReportEvent);

}
