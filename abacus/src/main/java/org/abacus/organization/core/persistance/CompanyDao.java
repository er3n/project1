package org.abacus.organization.core.persistance;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.abacus.definition.shared.constant.EnumList;
import org.abacus.organization.shared.entity.CompanyEntity;
import org.springframework.stereotype.Service;

@SuppressWarnings("serial")
@Service
public class CompanyDao implements Serializable {

	@PersistenceContext
	private EntityManager em;

	public CompanyEntity findParentCompany(CompanyEntity child){
		StringBuilder sb = new StringBuilder();
		sb.append("select c.* from org_company c ");
		sb.append("	where :companyId like c.id||'%'"); 
		sb.append("	  and c.level_enum < :levelStr ");
		sb.append("	order by c.id desc ");
		Query query = em.createNativeQuery(sb.toString(), CompanyEntity.class);
		query.setParameter("companyId", child.getId());
		query.setParameter("levelStr", child.getLevel().name());
		List<CompanyEntity> resultList = query.getResultList();
		if (resultList!=null && resultList.size()>0){
			return resultList.get(0);
		}
		return null;
	}
	
}
