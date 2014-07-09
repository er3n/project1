package org.abacus.report.jasper;

import java.io.Serializable;
import java.sql.Connection;

public interface JasperReportHandler extends Serializable{

	public Connection getConnection();
	
}
