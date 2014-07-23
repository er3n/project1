package org.abacus.report.core.handler;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface SqlQueryHandler extends Serializable{

	public Connection getConnection() throws SQLException;

	public List<Map<String, Object>> getSqlData(String sql);
	
}
