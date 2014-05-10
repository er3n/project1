package org.abacus.definition.core.persistance;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.abacus.definition.shared.entity.DefItemEntity;
import org.abacus.definition.shared.holder.ItemSearchCriteria;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@SuppressWarnings("serial")
@Service
public class DefItemDao implements Serializable {

	@PersistenceContext
	private EntityManager em;

	public List<DefItemEntity> requestItems(ItemSearchCriteria searchCriteria) {

		Criteria criteria = this.createRequestItemsCriteria(searchCriteria);
		
		if(searchCriteria.getFirst() != null && searchCriteria.getPageSize() != null){
			criteria.setFirstResult(searchCriteria.getFirst());
			criteria.setMaxResults(searchCriteria.getFirst() + searchCriteria.getPageSize());
		}
		
		criteria.addOrder(Order.asc("i.code"));
		
		List<DefItemEntity> resultList = criteria.list();
		
		Long totalCount = this.itemCount(searchCriteria);
		
		searchCriteria.setResultCount(totalCount);
		
		return resultList;
	}
	
	public Criteria createRequestItemsCriteria(ItemSearchCriteria searchCriteria){
		Session currentSession = em.unwrap(Session.class);
		Criteria criteria  = currentSession.createCriteria(DefItemEntity.class,"i");
		
		if(StringUtils.hasText(searchCriteria.getOrganization())){
			criteria.add(Restrictions.eq("i.organization.id", searchCriteria.getOrganization()));
		}
		if(searchCriteria.getItemType() != null){
			criteria.add(Restrictions.eq("i.type.id", searchCriteria.getItemType().name()));
		}
		if(searchCriteria.getItemClass() != null){
			criteria.add(Restrictions.eq("i.itemClass", searchCriteria.getItemClass()));
		}
		
		return criteria;
	}
	
	public Long itemCount(ItemSearchCriteria searchCriteria){
		Criteria criteria = this.createRequestItemsCriteria(searchCriteria);
		criteria.setProjection(Projections.rowCount());
		Long count = (Long) criteria.uniqueResult();
		return count;
	}

	
	
}
