package org.abacus.transaction.core.persistance;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.abacus.transaction.shared.entity.TraDetailEntity;
import org.abacus.transaction.shared.entity.TraDocumentEntity;
import org.hibernate.Session;
import org.springframework.stereotype.Service;



@Service
public class TransactionDao {

	@PersistenceContext
	private EntityManager em;

	public TraDocumentEntity save(TraDocumentEntity document) {
		Session currentSession = em.unwrap(Session.class);
		
		currentSession.save(document);
		
		return document;
	}

	public TraDetailEntity save(TraDetailEntity detail) {
		Session currentSession = em.unwrap(Session.class);
		
		currentSession.save(detail);
		
		return detail;		
	}


}
