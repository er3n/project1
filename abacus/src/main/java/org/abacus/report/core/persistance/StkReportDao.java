package org.abacus.report.core.persistance;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.entity.DefItemEntity;
import org.abacus.organization.shared.entity.DepartmentEntity;
import org.abacus.organization.shared.entity.FiscalPeriodEntity;
import org.abacus.organization.shared.entity.OrganizationEntity;
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
public class StkReportDao implements Serializable {

	@PersistenceContext
	private EntityManager em;

	public List<StkDetailEntity> getStkDetail(ReportSearchCriteria reportSearchCriteria) {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(StkDetailEntity.class,"d");
		criteria.createAlias("d.document", "document");
		criteria.createAlias("document.organization", "o");
		criteria.createAlias("document.fiscalPeriod2", "fp2");		
		criteria.createAlias("d.item", "item");
		criteria.createAlias("d.department", "department");
		//
		criteria.add(Restrictions.eq("fp2.fiscalYear.id", reportSearchCriteria.getFiscalYear().getId()));
		criteria.add(Restrictions.like("document.typeStr", EnumList.DefTypeGroupEnum.STK.name()+"%"));
		criteria.add(Restrictions.eq("item.type.id", EnumList.DefTypeEnum.ITM_SR_ST.name()));
		if(reportSearchCriteria.getReportDate()!=null ){
			criteria.add(Restrictions.le("document.docDate", reportSearchCriteria.getReportDate()));
			//FIXME : Fiscal year in List
		}
		if (reportSearchCriteria.getDetailItem()!=null){
			criteria.add(Restrictions.eq("d.item.id", reportSearchCriteria.getDetailItem().getId()));
		}
		if(reportSearchCriteria.getDocTask()!=null ){
			criteria.add(Restrictions.eq("document.task", reportSearchCriteria.getDocTask()));
		}
		if (reportSearchCriteria.getDetailDepartment()!=null){
			criteria.add(Restrictions.eq("d.department.id", reportSearchCriteria.getDetailDepartment().getId()));
		}
		List<StkDetailEntity> result = criteria.list();
		return result;
	}

	public List<StkDetailEntity> getStkState(ReportSearchCriteria reportSearchCriteria) {
		Session session = em.unwrap(Session.class);
		StringBuffer sb = new StringBuffer();
		sb.append("select {item.*}, {organization.*}, {fiscalPeriod2.*}, {department.*}, v.baseDetailCount ");
		sb.append("  from org_organization organization, org_fiscal_period fiscalPeriod2, def_item item, org_department department,");
		sb.append("     (select c.organization_id, c.fiscal_period2_id, d.item_id, d.department_id, sum(d.base_detail_count*d.tr_state_detail) baseDetailCount ");
		sb.append("        from tra_detail d, stk_document c, def_item i, org_fiscal_period p");
		sb.append("       where p.fiscal_year_id = :p_fiscal_year_id");
		sb.append("         and c.fiscal_period2_id = p.id");
		sb.append("         and d.item_id = coalesce(:p_item_id, d.item_id)");
		sb.append("         and d.department_id = coalesce(:p_department_id, d.department_id)");
		sb.append("         and c.id = d.document_stk_id");
		sb.append("         and c.type_id like '"+EnumList.DefTypeGroupEnum.STK.name()+"%'");
		sb.append("         and c.doc_date <= coalesce(:p_report_date, c.doc_date)");
		sb.append("         and c.task_id = coalesce(:p_task_id, c.task_id)");
		sb.append("         and i.id = d.item_id");
		sb.append("         and i.type_id = '"+EnumList.DefTypeEnum.ITM_SR_ST+"'");
		sb.append("       group by c.organization_id, c.fiscal_period2_id, d.item_id, d.department_id) v");
		sb.append(" where item.id = v.item_id");
		sb.append("   and organization.id = v.organization_id");
		sb.append("   and fiscalPeriod2.id = v.fiscal_period2_id");
		sb.append("   and department.id = v.department_id");
		SQLQuery sq = session.createSQLQuery(sb.toString());
		//
		sq.addEntity("organization", OrganizationEntity.class);
		sq.addEntity("fiscalPeriod2", FiscalPeriodEntity.class);
		sq.addEntity("item", DefItemEntity.class);
		sq.addEntity("department", DepartmentEntity.class);
		sq.addScalar("baseDetailCount", BigDecimalType.INSTANCE);
		//
		sq.setParameter("p_fiscal_year_id", reportSearchCriteria.getFiscalYear().getId(), StringType.INSTANCE);
		sq.setParameter("p_report_date", reportSearchCriteria.getReportDate()==null?null:reportSearchCriteria.getReportDate(), DateType.INSTANCE);
		//FIXME : Fiscal year in List
		sq.setParameter("p_item_id", reportSearchCriteria.getDetailItem()==null?null:reportSearchCriteria.getDetailItem().getId(), LongType.INSTANCE);
		sq.setParameter("p_task_id", reportSearchCriteria.getDocTask()==null?null:reportSearchCriteria.getDocTask().getId(), LongType.INSTANCE);
		sq.setParameter("p_department_id", reportSearchCriteria.getDetailDepartment()==null?null:reportSearchCriteria.getDetailDepartment().getId(), LongType.INSTANCE);
		Query q = sq.setResultTransformer(Transformers.aliasToBean(StkDetailEntity.class));
		List<StkDetailEntity> result = q.list();
		return result;
	}

}
