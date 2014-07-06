package org.abacus.report.core.handler;

import java.util.List;

import org.abacus.report.core.persistance.FinReportDao;
import org.abacus.report.core.persistance.StkReportDao;
import org.abacus.report.shared.event.ReadReportEvent;
import org.abacus.report.shared.event.RequestReadReportEvent;
import org.abacus.transaction.shared.entity.FinDetailEntity;
import org.abacus.transaction.shared.entity.StkDetailEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service(value="reportHandler")
public class ReportHandlerImpl implements ReportHandler {

	@Autowired
	private StkReportDao stkReportDao;

	@Autowired
	private FinReportDao finReportDao;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ReadReportEvent<StkDetailEntity> getStkState(RequestReadReportEvent requestReadReportEvent) {
		List<StkDetailEntity> detailList = stkReportDao.getStkState(requestReadReportEvent.getReportSearchCriteria());
		ReadReportEvent<StkDetailEntity> readEvent = new ReadReportEvent<StkDetailEntity>(detailList);
		return readEvent;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ReadReportEvent<StkDetailEntity> getStkDetail(RequestReadReportEvent requestReadReportEvent) {
		List<StkDetailEntity> detailList = stkReportDao.getStkDetail(requestReadReportEvent.getReportSearchCriteria());
		ReadReportEvent<StkDetailEntity> readEvent = new ReadReportEvent<StkDetailEntity>(detailList);
		return readEvent;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ReadReportEvent<FinDetailEntity> getFinState(RequestReadReportEvent requestReadReportEvent) {
		List<FinDetailEntity> detailList = finReportDao.getFinState(requestReadReportEvent.getReportSearchCriteria());
		ReadReportEvent<FinDetailEntity> readEvent = new ReadReportEvent<FinDetailEntity>(detailList);
		return readEvent;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ReadReportEvent<FinDetailEntity> getFinDetail(RequestReadReportEvent requestReadReportEvent) {
		List<FinDetailEntity> detailList = finReportDao.getFinDetail(requestReadReportEvent.getReportSearchCriteria());
		ReadReportEvent<FinDetailEntity> readEvent = new ReadReportEvent<FinDetailEntity>(detailList);
		return readEvent;
	}

}
