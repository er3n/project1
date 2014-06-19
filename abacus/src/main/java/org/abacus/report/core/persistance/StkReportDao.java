package org.abacus.report.core.persistance;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.entity.DefItemEntity;
import org.abacus.organization.shared.entity.DepartmentEntity;
import org.abacus.report.shared.holder.ReportSearchCriteria;
import org.abacus.transaction.shared.entity.StkDetailEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.BigDecimalType;
import org.hibernate.type.DateType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Service;

@Service
public class StkReportDao {

	@PersistenceContext
	private EntityManager em;

	public List<StkDetailEntity> getStkDetail(ReportSearchCriteria reportSearchCriteria) {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(StkDetailEntity.class,"d");
		criteria.createAlias("d.document", "document");
		criteria.createAlias("d.item", "item");
		criteria.createAlias("d.department", "department");
		criteria.add(Restrictions.eq("d.fiscalYear.id", reportSearchCriteria.getFiscalYear().getId()));
		criteria.add(Restrictions.eq("item.type.id", EnumList.DefTypeEnum.ITM_SR_ST.name()));
		if (reportSearchCriteria.getDetailItem()!=null){
			criteria.add(Restrictions.eq("d.item.id", reportSearchCriteria.getDetailItem().getId()));
		}
		if (reportSearchCriteria.getDetailDepartment()!=null){
			criteria.add(Restrictions.eq("d.department.id", reportSearchCriteria.getDetailDepartment().getId()));
		}
		if(reportSearchCriteria.getDocStartDate()!=null ){
			criteria.add(Restrictions.ge("document.docDate", reportSearchCriteria.getDocStartDate()));
		}
		if(reportSearchCriteria.getDocEndDate()!=null ){
			criteria.add(Restrictions.le("document.docDate", reportSearchCriteria.getDocEndDate()));
		}
		if(reportSearchCriteria.getDocTask()!=null ){
			criteria.add(Restrictions.eq("document.task", reportSearchCriteria.getDocTask()));
		}
		List<StkDetailEntity> result = criteria.list();
		return result;
	}

	public List<StkDetailEntity> getStkState(ReportSearchCriteria reportSearchCriteria) {
		Session session = em.unwrap(Session.class);
		StringBuffer sb = new StringBuffer();
		sb.append("select {item.*}, {department.*}, v.baseDetailCount ");
		sb.append("  from org_department department, def_item item,");
		sb.append("     (select item_id, department_id, sum(d.base_detail_count*d.tr_state_detail) baseDetailCount ");
		sb.append("        from stk_detail d, stk_document c, def_item i");
		sb.append("       where d.fiscal_year_id = :p_fiscal_year_id");
		sb.append("         and d.item_id = coalesce(:p_item_id, d.item_id)");
		sb.append("         and d.department_id = coalesce(:p_department_id, d.department_id)");
		sb.append("         and c.id = d.document_id");
		sb.append("         and c.doc_date >= coalesce(:p_date_start, c.doc_date)");
		sb.append("         and c.doc_date <= coalesce(:p_date_end, c.doc_date)");
		sb.append("         and c.task_id = coalesce(:p_task_id, c.task_id)");
		sb.append("         and i.id = d.item_id");
		sb.append("         and i.type_id = '"+EnumList.DefTypeEnum.ITM_SR_ST+"'");
		sb.append("       group by item_id,department_id) v");
		sb.append(" where item.id = v.item_id");
		sb.append("   and department.id = v.department_id");
		SQLQuery sq = session.createSQLQuery(sb.toString());
		sq.addEntity("item", DefItemEntity.class);
		sq.addEntity("department", DepartmentEntity.class);
		sq.addScalar("baseDetailCount", BigDecimalType.INSTANCE);
		sq.setParameter("p_fiscal_year_id", reportSearchCriteria.getFiscalYear().getId(), StringType.INSTANCE);
		sq.setParameter("p_item_id", reportSearchCriteria.getDetailItem()==null?null:reportSearchCriteria.getDetailItem().getId(), LongType.INSTANCE);
		sq.setParameter("p_department_id", reportSearchCriteria.getDetailDepartment()==null?null:reportSearchCriteria.getDetailDepartment().getId(), LongType.INSTANCE);
		sq.setParameter("p_date_start", reportSearchCriteria.getDocStartDate()==null?null:reportSearchCriteria.getDocStartDate(), DateType.INSTANCE);
		sq.setParameter("p_date_end", reportSearchCriteria.getDocEndDate()==null?null:reportSearchCriteria.getDocEndDate(), DateType.INSTANCE);
		sq.setParameter("p_task_id", reportSearchCriteria.getDocTask()==null?null:reportSearchCriteria.getDocTask().getId(), LongType.INSTANCE);
		Query q = sq.setResultTransformer(Transformers.aliasToBean(StkDetailEntity.class));
		List<StkDetailEntity> result = q.list();
		return result;
	}

}
