package org.abacus.budget.core.persistance;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.abacus.budget.core.handler.BudgetHandler;
import org.abacus.budget.core.persistance.repository.BudDetailRepository;
import org.abacus.budget.core.persistance.repository.BudDocumentRepository;
import org.abacus.budget.shared.entity.BudDetailEntity;
import org.abacus.budget.shared.entity.BudDocumentEntity;
import org.abacus.budget.shared.holder.BudgetHolder;
import org.abacus.common.web.AbcUtility;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.organization.shared.entity.FiscalPeriodEntity;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.BigDecimalType;
import org.hibernate.type.DateType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BudDetailDao implements Serializable {

	@PersistenceContext
	private EntityManager em;

	public List<BudDetailEntity> getFinAccrueBudget(FiscalPeriodEntity period) {

		Session session = em.unwrap(Session.class);
		StringBuffer sb = new StringBuffer();
		
		sb.append("Select c.doc_date accrueDate, 'ACCRUE' budgetType,");
		sb.append("       (case when d.glc_id = 'GLC_R' then 'BUD_R' ELSE 'BUD_X' end) budgetRX,");
		sb.append("	      sum(d.tr_state_detail*d.base_detail_amount)*(case when d.glc_id = 'GLC_R' then (-1) ELSE (+1) end) budgetAmount");  
		sb.append("	 from fin_document c, tra_detail d, org_fiscal_period fiscalPeriod");
		sb.append(" where d.document_fin_id = c.id");
		sb.append("   and fiscalPeriod.id = c.fiscal_period2_id");
		sb.append("   and c.fiscal_period2_id = :p_fiscal_period");
		sb.append("   and d.glc_id in ('GLC_R','GLC_X')");
		sb.append(" group by c.doc_date, d.glc_id");
		//
		SQLQuery sq = session.createSQLQuery(sb.toString());
		sq.addScalar("accrueDate", DateType.INSTANCE);
		sq.addScalar("budgetType", AbcUtility.getEnumHibernateType(EnumList.BudgetType.class));
		sq.addScalar("budgetRX", AbcUtility.getEnumHibernateType(EnumList.BudgetRX.class));
		sq.addScalar("budgetAmount", BigDecimalType.INSTANCE);
		//
		sq.setParameter("p_fiscal_period", period.getId(), StringType.INSTANCE);
		
		Query q = sq.setResultTransformer(Transformers.aliasToBean(BudDetailEntity.class));
		List<BudDetailEntity> result = q.list();
		return result;
	}

	
	
}
