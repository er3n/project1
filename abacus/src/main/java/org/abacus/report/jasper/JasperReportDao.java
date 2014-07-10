package org.abacus.report.jasper;

import java.sql.Connection;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.springframework.stereotype.Service;

@Service
public class JasperReportDao {

	@PersistenceContext
	private EntityManager em;

	public Connection getConnection() throws SQLException {
		Session session = em.unwrap(Session.class);
		SessionFactoryImplementor sfi = (SessionFactoryImplementor) session.getSessionFactory();
		ConnectionProvider cp = sfi.getConnectionProvider();
		Connection conn = cp.getConnection();
		return conn;
	}

}