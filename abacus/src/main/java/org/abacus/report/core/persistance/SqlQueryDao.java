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
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.abacus.report.shared.holder.SqlDataHolder;
import org.abacus.user.shared.entity.SecUserEntity;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.jdbc.ReturningWork;
import org.springframework.stereotype.Service;

@Service
public class SqlQueryDao {

	@PersistenceContext
	private EntityManager em;
	
	private Logger logger = Logger.getLogger(SqlQueryDao.class);

	public SecUserEntity getUser(String usrName, String usrPass) {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(SecUserEntity.class,"u");
		criteria.add(Restrictions.eq("u.id", usrName));
		criteria.add(Restrictions.eq("u.password", usrPass));
		List<SecUserEntity> userList = criteria.list();
		if (userList.size()>0){
			return userList.get(0);
		}
		return null;
	}
	
	public Connection getConnection() throws SQLException {
		Session session = em.unwrap(Session.class);
		SessionFactoryImplementor sfi = (SessionFactoryImplementor) session
				.getSessionFactory();
		ConnectionProvider cp = sfi.getConnectionProvider();
		Connection conn = cp.getConnection();
		return conn;
	}

	public SqlDataHolder getSqlData(String sql) {
		Statement statement = null;
		ResultSet resultSet = null;
		Set<String> columnSet = new TreeSet<String>();
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
			for (int columnIndex = 1; columnIndex <= columnCount; ++columnIndex) {
				String columnName = metaData.getColumnName(columnIndex);
				columnSet.add(columnName);
			}
			while (resultSet.next()) {
				Map<String, Object> row = new HashMap<String, Object>();
				for (String columnName : columnSet) {
					Object obj = resultSet.getObject(columnName);
					row.put(columnName, obj);
				}
				listMap.add(row);
			}
			return new SqlDataHolder(listMap, columnSet);
		} catch (Exception e) {
			System.out.println("PivotSQL:"+sql);
			logger.error(e,e);
			return new SqlDataHolder(listMap, columnSet);
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