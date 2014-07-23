package org.abacus.report.core.handler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.abacus.report.core.persistance.SqlQueryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("serial")
@Service("sqlQueryHandler")
public class SqlQueryHandlerImpl implements SqlQueryHandler {

	@Autowired
	private SqlQueryDao sqlQueryDao;

	public SqlQueryDao getJasperReportDao() {
		return sqlQueryDao;
	}

	public void setJasperReportDao(SqlQueryDao jasperReportDao) {
		this.sqlQueryDao = jasperReportDao;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public Connection getConnection() throws SQLException {
		Connection conn = sqlQueryDao.getConnection();
		return conn;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public List<Map<String, Object>> getSqlData(String sql) {
		List<Map<String, Object>> sqlData = sqlQueryDao.getSqlData(sql);
		return sqlData;
	}

}




	