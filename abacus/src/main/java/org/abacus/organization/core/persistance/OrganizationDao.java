package org.abacus.organization.core.persistance;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.abacus.definition.shared.constant.EnumList;
import org.abacus.organization.shared.entity.OrganizationEntity;
import org.springframework.stereotype.Service;

@SuppressWarnings("serial")
@Service
public class OrganizationDao implements Serializable {

	@PersistenceContext
	private EntityManager em;

	public OrganizationEntity findParentOrganization(OrganizationEntity child){
		String parentLevel = "L0";//null
		if (child.getLevel().ordinal()>0){
			parentLevel = EnumList.OrgOrganizationLevelEnum.values()[child.getLevel().ordinal()-1].name();
		}
		StringBuilder sb = new StringBuilder();
		sb.append("select c.* from org_organization c ");
		sb.append("	where :organizationId like c.id||'%'"); 
		sb.append("	  and c.level_enum = :levelStr ");
		sb.append("	  and length(c.id) < length(:organizationId)");
		sb.append("	order by c.id desc ");
		Query query = em.createNativeQuery(sb.toString(), OrganizationEntity.class);
		query.setParameter("organizationId", child.getId());
		query.setParameter("levelStr", parentLevel);
		List<OrganizationEntity> resultList = query.getResultList();
		if (resultList!=null && resultList.size()>0){
			return resultList.get(0);
		}
		return null;
	}
	
}
