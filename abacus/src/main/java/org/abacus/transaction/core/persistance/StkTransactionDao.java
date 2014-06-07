package org.abacus.transaction.core.persistance;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;



@Service
public class StkTransactionDao {

	@PersistenceContext
	private EntityManager em;


}
