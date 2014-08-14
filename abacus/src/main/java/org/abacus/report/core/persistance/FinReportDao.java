package org.abacus.report.core.persistance;

import java.io.Serializable;
import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.abacus.common.web.AbcUtility;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.entity.DefItemEntity;
import org.abacus.organization.shared.entity.FiscalPeriodEntity;
import org.abacus.organization.shared.entity.OrganizationEntity;
import org.abacus.report.shared.holder.ReportSearchCriteria;
import org.abacus.transaction.shared.entity.FinDetailEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.TypeLocatorImpl;
import org.hibernate.transform.Transformers;
import org.hibernate.type.BigDecimalType;
import org.hibernate.type.DateType;
import org.hibernate.type.EnumType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;
import org.hibernate.type.TypeResolver;
import org.springframework.stereotype.Service;



@Service
public class FinReportDao implements Serializable {

	@PersistenceContext
	private EntityManager em;

	public List<FinDetailEntity> getFinDetail(ReportSearchCriteria reportSearchCriteria) {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(FinDetailEntity.class,"d");
		criteria.createAlias("d.document", "document");
		criteria.createAlias("document.organization", "o");
		criteria.createAlias("document.fiscalPeriod2", "fp2");		
		criteria.createAlias("d.item", "item");

		criteria.add(Restrictions.eq("fp2.fiscalYear.id", reportSearchCriteria.getFiscalYear().getId()));
		if(reportSearchCriteria.getReportDate()!=null ){
			criteria.add(Restrictions.le("document.docDate", reportSearchCriteria.getReportDate()));
		}
		if (reportSearchCriteria.getDetailItem()!=null){
			criteria.add(Restrictions.eq("d.item.id", reportSearchCriteria.getDetailItem().getId()));
		}
		if(reportSearchCriteria.getDocTask()!=null ){
			criteria.add(Restrictions.eq("document.task", reportSearchCriteria.getDocTask()));
		}
		if(reportSearchCriteria.getAccountGLC() !=null ){
			criteria.add(Restrictions.eq("d.glc", reportSearchCriteria.getAccountGLC()));
		}
		if(reportSearchCriteria.getAccountITM() !=null ){
			criteria.add(Restrictions.like("item.type.id", reportSearchCriteria.getAccountITM().name()+"%"));
		}
		List<FinDetailEntity> result = criteria.list();
		return result;
	}

	public List<FinDetailEntity> getFinState(ReportSearchCriteria reportSearchCriteria) {

		Session session = em.unwrap(Session.class);
		StringBuffer sb = new StringBuffer();
		sb.append("select {organization.*}, {fiscalPeriod2.*}, {item.*}, v.glc_id as glc, v.baseDetailAmount ");
		sb.append("  from org_organization organization, org_fiscal_period fiscalPeriod2, def_item item,");
		sb.append("     (select c.organization_id, c.fiscal_period2_id, d.glc_id, d.item_id, sum(d.base_detail_amount*d.tr_state_detail) baseDetailAmount ");
		sb.append("        from tra_detail d, fin_document c, def_item i, org_fiscal_period fp2");
		sb.append("       where fp2.fiscal_year_id = :p_fiscal_year_id");
		sb.append("         and c.fiscal_period2_id = fp2.id");
		sb.append("         and d.item_id = coalesce(:p_item_id, d.item_id)");
		sb.append("         and c.id = d.document_fin_id");
//		sb.append("         and c.type_id like '"+EnumList.DefTypeGroupEnum.FIN.name()+"%'");
		sb.append("         and c.doc_date <= coalesce(:p_report_date, c.doc_date)");
		sb.append("         and c.task_id = coalesce(:p_task_id, c.task_id)");
		sb.append("         and d.glc_id = coalesce(:p_account_glc, d.glc_id)");
		sb.append("         and i.type_id = coalesce(:p_account_itm, i.type_id)");
		sb.append("         and i.id = d.item_id");
		sb.append("       group by c.organization_id, c.fiscal_period2_id, d.glc_id, d.item_id) v");
		sb.append(" where item.id = v.item_id");
		sb.append("   and organization.id = v.organization_id");
		sb.append("   and fiscalPeriod2.id = v.fiscal_period2_id");
		//
		SQLQuery sq = session.createSQLQuery(sb.toString());
		sq.addEntity("organization", OrganizationEntity.class);
		sq.addEntity("fiscalPeriod2", FiscalPeriodEntity.class);
		sq.addEntity("item", DefItemEntity.class);
		sq.addScalar("glc", AbcUtility.getEnumHibernateType(EnumList.AccountGLC.class));
		sq.addScalar("baseDetailAmount", BigDecimalType.INSTANCE);
		//
		sq.setParameter("p_fiscal_year_id", reportSearchCriteria.getFiscalYear().getId(), StringType.INSTANCE);
		sq.setParameter("p_report_date", reportSearchCriteria.getReportDate()==null?null:reportSearchCriteria.getReportDate(), DateType.INSTANCE);
		sq.setParameter("p_item_id", reportSearchCriteria.getDetailItem()==null?null:reportSearchCriteria.getDetailItem().getId(), LongType.INSTANCE);
		sq.setParameter("p_task_id", reportSearchCriteria.getDocTask()==null?null:reportSearchCriteria.getDocTask().getId(), LongType.INSTANCE);
		sq.setParameter("p_account_glc", reportSearchCriteria.getAccountGLC()==null?null:reportSearchCriteria.getAccountGLC().getName(), StringType.INSTANCE);
		sq.setParameter("p_account_itm", reportSearchCriteria.getAccountITM()==null?null:reportSearchCriteria.getAccountITM().getName(), StringType.INSTANCE);
		
		Query q = sq.setResultTransformer(Transformers.aliasToBean(FinDetailEntity.class));
		List<FinDetailEntity> result = q.list();
		return result;
	}

}
