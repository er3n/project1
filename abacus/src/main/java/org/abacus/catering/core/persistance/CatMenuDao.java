package org.abacus.catering.core.persistance;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.abacus.catering.shared.entity.CatMenuEntity;
import org.abacus.catering.shared.holder.CatMenuSearchCriteria;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

@Service
public class CatMenuDao {

	@PersistenceContext
	private EntityManager em;

	public List<CatMenuEntity> findMenuList(CatMenuSearchCriteria searchCriteria) {
		Session session = em.unwrap(Session.class);

		Criteria criteria = session.createCriteria(CatMenuEntity.class,"m");

		if(searchCriteria.getFiscalYear()!=null){
			criteria.add(Restrictions.eq("m.fiscalYear.id", searchCriteria.getFiscalYear().getId()));
		}
		if(searchCriteria.getStartDate() != null){
			criteria.add(Restrictions.ge("m.menuDate", searchCriteria.getStartDate()));
		}
		if(searchCriteria.getEndDate() != null){
			criteria.add(Restrictions.le("m.menuDate", searchCriteria.getEndDate()));
		}
		criteria.addOrder(Order.asc("m.menuDate"));
		
		List<CatMenuEntity> resultList = criteria.list();
		
		return resultList;
	}

}
