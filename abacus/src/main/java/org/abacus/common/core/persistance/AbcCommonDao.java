package org.abacus.common.core.persistance;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

@SuppressWarnings("serial")
@Service
public class AbcCommonDao implements Serializable {

	@PersistenceContext
	private EntityManager em;

	
	public Long getNewId(){
		StringBuilder sb = new StringBuilder();
		sb.append("select nextval('seq_id') newId");
		Query query = em.createNativeQuery(sb.toString());
		List<BigInteger> resultList = query.getResultList();
		return Long.parseLong(resultList.get(0).toString());				
	}
	
}
