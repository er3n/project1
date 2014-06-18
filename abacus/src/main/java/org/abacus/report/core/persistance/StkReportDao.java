package org.abacus.report.core.persistance;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.abacus.definition.shared.entity.DefItemEntity;
import org.abacus.organization.shared.entity.DepartmentEntity;
import org.abacus.transaction.shared.entity.StkDetailEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.BigDecimalType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Service;

@Service
public class StkReportDao {

	@PersistenceContext
	private EntityManager em;

	public List<StkDetailEntity> getStkDetail(String fiscalYearId) {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(StkDetailEntity.class,"d");
		criteria.createAlias("d.document", "document");
		criteria.createAlias("d.item", "item");
		criteria.createAlias("d.department", "department");
		criteria.add(Restrictions.eq("d.fiscalYear.id", fiscalYearId));
		List<StkDetailEntity> result = criteria.list();
		return result;
	}

	public List<StkDetailEntity> getStkState(String fiscalYearId) {
		Session session = em.unwrap(Session.class);
		StringBuffer sb = new StringBuffer();
		sb.append(" select {department.*}, {item.*}, v.baseDetailCount ");
		sb.append(" from org_department department, def_item item,");
		sb.append(" (select department_id, item_id, sum(d.base_detail_count*d.tr_state_detail) baseDetailCount ");
		sb.append("    from stk_detail d");
		sb.append("   where d.fiscal_year_id = :p_fiscal_year_id");
		sb.append("   group by department_id,item_id) v");
		sb.append(" where department.id = v.department_id");
		sb.append("   and item.id = v.item_id");
		SQLQuery sq = session.createSQLQuery(sb.toString());
		sq.addEntity("department", DepartmentEntity.class);
		sq.addEntity("item", DefItemEntity.class);
		sq.addScalar("baseDetailCount", BigDecimalType.INSTANCE);
		sq.setParameter("p_fiscal_year_id", fiscalYearId, StringType.INSTANCE);
		Query q = sq.setResultTransformer(Transformers.aliasToBean(StkDetailEntity.class));
		List<StkDetailEntity> result = q.list();
		return result;
	}

}
