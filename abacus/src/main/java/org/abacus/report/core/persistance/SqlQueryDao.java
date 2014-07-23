package org.abacus.report.core.persistance;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.jdbc.ReturningWork;
import org.springframework.stereotype.Service;

@Service
public class SqlQueryDao {

	@PersistenceContext
	private EntityManager em;
	
	private Logger logger = Logger.getLogger(SqlQueryDao.class);

	public Connection getConnection() throws SQLException {
		Session session = em.unwrap(Session.class);
		SessionFactoryImplementor sfi = (SessionFactoryImplementor) session
				.getSessionFactory();
		ConnectionProvider cp = sfi.getConnectionProvider();
		Connection conn = cp.getConnection();
		return conn;
	}

	public List<Map<String, Object>> getSqlData(String sql) {
		Statement statement = null;
		ResultSet resultSet = null;
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		try {
			Session sess = em.unwrap(Session.class);
			statement = sess.doReturningWork(new ReturningWork<Statement>() {
				@Override
				public Statement execute(Connection con) throws SQLException {
					return con.createStatement();
				}
			});
			resultSet = statement.executeQuery(sql);
			ResultSetMetaData metaData = resultSet.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (resultSet.next()) {
				Map<String, Object> row = new HashMap<String, Object>();
				for (int columnIndex = 1; columnIndex <= columnCount; ++columnIndex) {
					String columnName = metaData.getColumnName(columnIndex);
					Object obj = resultSet.getObject(columnName);
					row.put(columnName, obj);
				}
				listMap.add(row);
			}
			return listMap;
		} catch (Exception e) {
			logger.error(e,e);
			return listMap;
		} finally {
			try {
				resultSet.close();
			} catch (Exception e) {
				logger.error(e,e);
			}
			try {
				statement.close();
			} catch (Exception e) {
				logger.error(e,e);
			}
		}
	}

}