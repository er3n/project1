package org.abacus.report.jasper;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

public interface JasperReportHandler extends Serializable{

	public Connection getConnection() throws SQLException;
	
}
