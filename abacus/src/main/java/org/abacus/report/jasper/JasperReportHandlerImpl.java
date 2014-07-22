package org.abacus.report.jasper;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("serial")
@Service("jasperReportHandler")
public class JasperReportHandlerImpl implements JasperReportHandler {

	@Autowired
	private JasperReportDao jasperReportDao;

	public JasperReportDao getJasperReportDao() {
		return jasperReportDao;
	}

	public void setJasperReportDao(JasperReportDao jasperReportDao) {
		this.jasperReportDao = jasperReportDao;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public Connection getConnection() throws SQLException {
		Connection conn = jasperReportDao.getConnection();
		return conn;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public List<Map<String, Object>> getSqlData(String sql) {
		List<Map<String, Object>> sqlData = jasperReportDao.getSqlData(sql);
		return sqlData;
	}

}




	