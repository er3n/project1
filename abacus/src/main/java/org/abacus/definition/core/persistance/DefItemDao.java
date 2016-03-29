package org.abacus.definition.core.persistance;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.abacus.definition.shared.entity.DefItemEntity;
import org.abacus.definition.shared.holder.ItemSearchCriteria;
import org.abacus.organization.shared.entity.OrganizationEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@SuppressWarnings("serial")
@Service
public class DefItemDao implements Serializable {

	@PersistenceContext
	private EntityManager em;

	private Criteria createRequestItemsCriteria(ItemSearchCriteria searchCriteria){
		Session currentSession = em.unwrap(Session.class);
		Criteria criteria  = currentSession.createCriteria(DefItemEntity.class,"i");
		criteria.createAlias("i.type","t", JoinType.INNER_JOIN);
		criteria.createAlias("i.organization","o", JoinType.LEFT_OUTER_JOIN);
		
		Criterion orgRoot = Restrictions.isNull("i.organization");
		if(searchCriteria.getOrganization()!=null){
			List<OrganizationEntity> upList = searchCriteria.getOrganization().getParentList();
			Criterion orgUpper = Restrictions.in("i.organization", upList);
			Criterion orgDown = Restrictions.like("i.organization.id", searchCriteria.getOrganization().getId()+"%");
			criteria.add(Restrictions.or(orgRoot, orgUpper, orgDown));			
		} else {
			criteria.add(orgRoot);	
		}
		
		if(searchCriteria.getItemType() != null){
			criteria.add(Restrictions.like("t.id", searchCriteria.getItemType().name()+"%"));
		}
		if(searchCriteria.getItemClass() != null){
			criteria.add(Restrictions.eq("i.itemClass", searchCriteria.getItemClass()));
		}
		if(StringUtils.hasText(searchCriteria.getCodeLike())){
			criteria.add(Restrictions.ilike("i.code", "%" + searchCriteria.getCodeLike().toLowerCase() + "%"));
		}
		if(StringUtils.hasText(searchCriteria.getCode())){
			criteria.add(Restrictions.ilike("i.code", searchCriteria.getCode()));
		}
		if(StringUtils.hasText(searchCriteria.getNameLike())){
			criteria.add(Restrictions.ilike("i.name", "%" + searchCriteria.getNameLike() + "%"));
		}
		if(StringUtils.hasText(searchCriteria.getCategoryNameLike())){
			criteria.createAlias("i.category", "c");
			criteria.add(Restrictions.ilike("c.name", "%" + searchCriteria.getCategoryNameLike() + "%"));
		}
		if(searchCriteria.getStatus() != null){
			criteria.add(Restrictions.eq("i.active", searchCriteria.getStatus()));
		}
		if(searchCriteria.getFilterTypeDesc() != null){
			criteria.add(Restrictions.eq("t.name", (String)searchCriteria.getFilterTypeDesc()));
		}
		
		return criteria;
	}
	
	public List<DefItemEntity> requestItems(ItemSearchCriteria searchCriteria) {
		if (searchCriteria.getItemList()!=null && searchCriteria.getItemList().size()>0){
			return searchCriteria.getItemList();
		}
		Criteria criteria = this.createRequestItemsCriteria(searchCriteria);
		if(searchCriteria.getFirst() != null && searchCriteria.getPageSize() != null){
			criteria.setFirstResult(searchCriteria.getFirst());
			criteria.setMaxResults(searchCriteria.getPageSize());
		}		
		criteria.addOrder(Order.asc("i.type.id"));
		criteria.addOrder(Order.asc("i.code"));
		List<DefItemEntity> resultList = criteria.list();
		return resultList;
	}
	
	public Integer itemCount(ItemSearchCriteria searchCriteria){
		Criteria criteria = this.createRequestItemsCriteria(searchCriteria);
		criteria.setProjection(Projections.rowCount());
		Long count = (Long) criteria.uniqueResult();
		return count.intValue();
	}

	
	
}
