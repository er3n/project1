package org.abacus.catering.core.persistance;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.abacus.catering.shared.entity.CatMealFilterEntity;
import org.abacus.catering.shared.entity.CatMenuEntity;
import org.abacus.catering.shared.holder.CatMenuSearchCriteria;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;



@Service
public class DefMenuDao {

	@PersistenceContext
	private EntityManager em;

	public List<CatMenuEntity> findMenuList(CatMenuSearchCriteria searchCriteria) {
		Session session = em.unwrap(Session.class);

		Criteria criteria = session.createCriteria(CatMenuEntity.class,"m");

		if(searchCriteria.getStartDate() != null){
			criteria.add(Restrictions.ge("m.menuDate", searchCriteria.getStartDate()));
		}
		if(searchCriteria.getEndDate() != null){
			criteria.add(Restrictions.le("m.menuDate", searchCriteria.getEndDate()));
		}
		if(StringUtils.hasText(searchCriteria.getOrganization())){
			criteria.add(Restrictions.eq("m.organization.id", searchCriteria.getOrganization()));
		}
		
		criteria.addOrder(Order.asc("m.menuDate"));
		
		List<CatMenuEntity> resultList = criteria.list();
		
		return resultList;
	}

	public List<CatMealFilterEntity> findMealList(CatMenuSearchCriteria searchCriteria) {
		Session session = em.unwrap(Session.class);
		
		Criteria criteria = session.createCriteria(CatMealFilterEntity.class,"m");

		if(searchCriteria.getStartDate() != null){
			criteria.add(Restrictions.ge("m.menuDate", searchCriteria.getStartDate()));
		}
		if(searchCriteria.getEndDate() != null){
			criteria.add(Restrictions.le("m.menuDate", searchCriteria.getEndDate()));
		}
		if(StringUtils.hasText(searchCriteria.getOrganization())){
			criteria.add(Restrictions.eq("m.organization.id", searchCriteria.getOrganization()));
		}
		
		
		return null;
	}

}
