package org.abacus.report.core.persistance;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.entity.DefItemEntity;
import org.abacus.organization.shared.entity.OrganizationEntity;
import org.abacus.report.shared.holder.ReportSearchCriteria;
import org.abacus.transaction.shared.entity.FinDetailEntity;
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
public class FinReportDao implements Serializable {

	@PersistenceContext
	private EntityManager em;

	public List<FinDetailEntity> getFinDetail(ReportSearchCriteria reportSearchCriteria) {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(FinDetailEntity.class,"d");
		criteria.createAlias("d.document", "document");
		criteria.createAlias("d.item", "item");
		criteria.createAlias("document.organization", "org");
		criteria.add(Restrictions.eq("d.fiscalYear.id", reportSearchCriteria.getFiscalYear().getId()));
		criteria.add(Restrictions.like("document.typeStr", EnumList.DefTypeGroupEnum.FIN.name()+"%"));
		if (reportSearchCriteria.getDetailItem()!=null){
			criteria.add(Restrictions.eq("d.item.id", reportSearchCriteria.getDetailItem().getId()));
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
		List<FinDetailEntity> result = criteria.list();
		return result;
	}

	public List<FinDetailEntity> getFinState(ReportSearchCriteria reportSearchCriteria) {
		Session session = em.unwrap(Session.class);
		StringBuffer sb = new StringBuffer();
		sb.append("select {item.*}, {organization.*}, v.baseDetailAmount ");
		sb.append("  from org_organization organization, def_item item,");
		sb.append("     (select d.item_id, c.organization_id, sum(d.base_detail_amount*d.tr_state_detail) baseDetailAmount ");
		sb.append("        from tra_detail d, fin_document c, def_item i");
		sb.append("       where d.fiscal_year_id = :p_fiscal_year_id");
		sb.append("         and d.item_id = coalesce(:p_item_id, d.item_id)");
		sb.append("         and c.id = d.document_fin_id");
		sb.append("         and c.type_id like '"+EnumList.DefTypeGroupEnum.FIN.name()+"%'");
		sb.append("         and c.doc_date >= coalesce(:p_date_start, c.doc_date)");
		sb.append("         and c.doc_date <= coalesce(:p_date_end, c.doc_date)");
		sb.append("         and c.task_id = coalesce(:p_task_id, c.task_id)");
		sb.append("         and i.id = d.item_id");
		sb.append("       group by d.item_id, c.organization_id) v");
		sb.append(" where item.id = v.item_id");
		sb.append("   and organization.id = v.organization_id");
		SQLQuery sq = session.createSQLQuery(sb.toString());
		sq.addEntity("item", DefItemEntity.class);
		sq.addEntity("organization", OrganizationEntity.class);
		sq.addScalar("baseDetailAmount", BigDecimalType.INSTANCE);
		sq.setParameter("p_fiscal_year_id", reportSearchCriteria.getFiscalYear().getId(), StringType.INSTANCE);
		sq.setParameter("p_item_id", reportSearchCriteria.getDetailItem()==null?null:reportSearchCriteria.getDetailItem().getId(), LongType.INSTANCE);
		sq.setParameter("p_date_start", reportSearchCriteria.getDocStartDate()==null?null:reportSearchCriteria.getDocStartDate(), DateType.INSTANCE);
		sq.setParameter("p_date_end", reportSearchCriteria.getDocEndDate()==null?null:reportSearchCriteria.getDocEndDate(), DateType.INSTANCE);
		sq.setParameter("p_task_id", reportSearchCriteria.getDocTask()==null?null:reportSearchCriteria.getDocTask().getId(), LongType.INSTANCE);
		Query q = sq.setResultTransformer(Transformers.aliasToBean(FinDetailEntity.class));
		List<FinDetailEntity> result = q.list();
		return result;
	}

}
