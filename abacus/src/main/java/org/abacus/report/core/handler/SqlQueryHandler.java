package org.abacus.report.core.handler;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

import org.abacus.report.shared.holder.SqlDataHolder;

public interface SqlQueryHandler extends Serializable{

	public Connection getConnection() throws SQLException;

	public SqlDataHolder getSqlData(String sql);
	
}
