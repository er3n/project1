package org.abacus.definition.core.persistance;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.abacus.definition.shared.entity.DefParamAnswerEntity;
import org.springframework.stereotype.Service;

@SuppressWarnings("serial")
@Service
public class DefParamAnswerDao implements Serializable {

	@PersistenceContext
	private EntityManager em;

	public DefParamAnswerEntity getParamAnswer(String paramId, String organizationId) {
		//hiyerarsik olarak en secilen organizasyona en yakin cevabi bulur
		StringBuilder sb = new StringBuilder();
		sb.append("select a.* from def_param_answer a, org_organization o");
		sb.append(" where a.param_id = :paramId");
		sb.append("   and o.id like a.organization_id||'%'");
		sb.append("   and o.id = :organizationId");
		sb.append("   order by a.organization_id desc");
		Query query = em.createNativeQuery(sb.toString(), DefParamAnswerEntity.class);
		query.setParameter("paramId", paramId);
		query.setParameter("organizationId", organizationId);
		List<DefParamAnswerEntity> resultList = query.getResultList();
		if (!resultList.isEmpty()){
			resultList.get(0);
		}
		return null;
	}
	
}
