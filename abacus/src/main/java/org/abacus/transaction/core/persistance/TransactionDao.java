package org.abacus.transaction.core.persistance;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.abacus.definition.shared.constant.EnumList;
import org.abacus.transaction.shared.entity.FinDocumentEntity;
import org.abacus.transaction.shared.entity.StkDocumentEntity;
import org.abacus.transaction.shared.entity.TraDetailEntity;
import org.abacus.transaction.shared.entity.TraDocumentEntity;
import org.abacus.transaction.shared.holder.TraDocumentSearchCriteria;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;



@Service
public class TransactionDao<T extends TraDocumentEntity, D extends TraDetailEntity> {

	@PersistenceContext
	private EntityManager em;
	
	public T documentSave(T document) {
		Session currentSession = em.unwrap(Session.class);
		currentSession.save(document);
		return document;
	}

	public D detailSave(D detail) {
		Session currentSession = em.unwrap(Session.class);
		currentSession.save(detail);
		return detail;		
	}

	public List<T> readDocument(TraDocumentSearchCriteria documentSearchCriteria, String organization, String fiscalYearId) {
		Session currentSession = em.unwrap(Session.class);
		Criteria criteria = null;
		if (documentSearchCriteria.getDocumentGroupEnum().equals(EnumList.DefTypeGroupEnum.STK)){
			criteria = currentSession.createCriteria(StkDocumentEntity.class,"s");
		} else if (documentSearchCriteria.getDocumentGroupEnum().equals(EnumList.DefTypeGroupEnum.FIN)){
			criteria = currentSession.createCriteria(FinDocumentEntity.class,"s");
		}
		
		criteria.createAlias("s.fiscalPeriod", "fp");
		criteria.createAlias("s.item", "itm", JoinType.LEFT_OUTER_JOIN);

		criteria.add(Restrictions.like("s.typeStr", documentSearchCriteria.getDocumentGroupEnum().name()+"%"));

		if(documentSearchCriteria.getDocumentId() != null){
			criteria.add(Restrictions.eq("s.id", documentSearchCriteria.getDocumentId()));
		}
		
		if(StringUtils.hasText(organization)){
			criteria.add(Restrictions.like("s.organization.id", organization+"%"));
		}
		
		if(StringUtils.hasText(fiscalYearId)){
			criteria.add(Restrictions.eq("fp.fiscalYear.id", fiscalYearId));
		}
		
		if(StringUtils.hasText(documentSearchCriteria.getDocNo())){
			criteria.add(Restrictions.like("s.docNo","%"+documentSearchCriteria.getDocNo()+"%"));
		}

		if(documentSearchCriteria.getDocStartDate()!=null ){
			criteria.add(Restrictions.ge("s.docDate", documentSearchCriteria.getDocStartDate()));
		}

		if(documentSearchCriteria.getDocEndDate()!=null ){
			criteria.add(Restrictions.le("s.docDate", documentSearchCriteria.getDocEndDate()));
		}

		criteria.addOrder(Order.desc("s.docDate"));
		
		List<T> result = criteria.list();
		
		return result;
	}


}
