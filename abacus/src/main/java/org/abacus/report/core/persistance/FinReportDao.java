package org.abacus.report.core.persistance;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;



@Service
public class FinReportDao implements Serializable {

	@PersistenceContext
	private EntityManager em;

}
