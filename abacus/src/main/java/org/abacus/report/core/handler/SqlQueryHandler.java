package org.abacus.report.core.handler;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

import org.abacus.report.shared.holder.SqlDataHolder;
import org.abacus.user.shared.entity.SecUserEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public interface SqlQueryHandler extends Serializable{

	public Connection getConnection() throws SQLException;

	public SqlDataHolder getSqlData(String sql);
	
	public SecUserEntity getUser(String usrName, String usrPass);
}
