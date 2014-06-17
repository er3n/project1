package org.abacus.report.core.persistance;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;



@Service
public class FinReportDao {

	@PersistenceContext
	private EntityManager em;

}
