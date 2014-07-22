package org.abacus.report.jasper;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface JasperReportHandler extends Serializable{

	public Connection getConnection() throws SQLException;

	public List<Map<String, Object>> getSqlData(String sql);
	
}
