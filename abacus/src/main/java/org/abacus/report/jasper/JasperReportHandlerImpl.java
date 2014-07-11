package org.abacus.report.jasper;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("serial")
@Service("jasperReportHandler")
public class JasperReportHandlerImpl implements JasperReportHandler {

	@Autowired
	private JasperReportDao jasperReportDao;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
	public Connection getConnection() throws SQLException {
		Connection conn = jasperReportDao.getConnection();
		return conn;
	}

	public JasperReportDao getJasperReportDao() {
		return jasperReportDao;
	}

	public void setJasperReportDao(JasperReportDao jasperReportDao) {
		this.jasperReportDao = jasperReportDao;
	}


}




	