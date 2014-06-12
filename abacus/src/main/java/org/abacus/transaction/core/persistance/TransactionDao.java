package org.abacus.transaction.core.persistance;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.abacus.transaction.shared.entity.StkDocumentEntity;
import org.abacus.transaction.shared.entity.TraDetailEntity;
import org.abacus.transaction.shared.entity.TraDocumentEntity;
import org.abacus.transaction.shared.event.RequestReadDocumentEvent;
import org.abacus.transaction.shared.holder.TraDocumentSearchCriteria;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;



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

	public List<TraDocumentEntity> readDocument(TraDocumentSearchCriteria documentSearchCriteria, String organization, String fiscalYearId) {
		Session currentSession = em.unwrap(Session.class);
		
		Criteria criteria = currentSession.createCriteria(StkDocumentEntity.class,"s");
		criteria.createAlias("s.fiscalPeriod", "fp");
		
		if(StringUtils.hasText(organization)){
			criteria.add(Restrictions.eq("s.organization.id", organization));
		}
		
		if(StringUtils.hasText(fiscalYearId)){
			criteria.add(Restrictions.eq("fp.fiscalYear.id", fiscalYearId));
		}
		
		if(StringUtils.hasText(documentSearchCriteria.getDocNo())){
			criteria.add(Restrictions.eq("s.docNo",documentSearchCriteria.getDocNo()));
		}
		
		criteria.addOrder(Order.desc("s.docDate"));
		
		List<TraDocumentEntity> result = criteria.list();
		
		return result;
	}


}
