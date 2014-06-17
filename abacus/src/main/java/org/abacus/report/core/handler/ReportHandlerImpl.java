package org.abacus.report.core.handler;

import java.util.List;

import org.abacus.report.core.persistance.FinReportDao;
import org.abacus.report.core.persistance.StkReportDao;
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
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<StkDetailEntity> getStkState(String fiscalYearId) {
		return stkReportDao.getStkState(fiscalYearId);
	}
		
}
