package org.abacus.transaction.core.persistance;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.abacus.transaction.shared.entity.FinDocumentEntity;
import org.abacus.transaction.shared.entity.TraDetailEntity;
import org.abacus.transaction.shared.entity.TraDocumentEntity;
import org.abacus.transaction.shared.holder.TraDocumentSearchCriteria;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.util.StringUtils;

public abstract class TraTransactionDao<T extends TraDocumentEntity, D extends TraDetailEntity<D>> {

	@PersistenceContext
	private EntityManager em;

	public abstract Class<T> getDocumentClass();

	public abstract Class<D> getDetailClass();

	public List<T> readTraDocument(TraDocumentSearchCriteria documentSearchCriteria, String organization, String fiscalYearId2) {
		Class<T> docClass = getDocumentClass(); 
		Session currentSession = em.unwrap(Session.class);
		Criteria criteria = currentSession.createCriteria(docClass, "s");
//		criteria.createAlias("s.fiscalPeriod1", "fp1", JoinType.INNER_JOIN);
		criteria.createAlias("s.fiscalPeriod2", "fp2", JoinType.INNER_JOIN);
		criteria.createAlias("s.item", "itm", JoinType.LEFT_OUTER_JOIN);
		if (docClass == FinDocumentEntity.class){
			criteria.createAlias("s.finInfoSet", "info", JoinType.LEFT_OUTER_JOIN);
		}
		
		if (documentSearchCriteria.getDocumentId() != null) {
			criteria.add(Restrictions.eq("s.id", documentSearchCriteria.getDocumentId()));
		}

		if (StringUtils.hasText(organization)) {
			criteria.add(Restrictions.like("s.organization.id", organization + "%"));
		}

		// Gelen organizasyonun level bilgisi gore sirketse tum alt projeleri gorsun gibi eklemeler olabilir 
		
//		if (StringUtils.hasText(fiscalYearId1)) {//Company Fiscali
//			criteria.add(Restrictions.eq("fp1.fiscalYear.id", fiscalYearId1));
//		}

		if (StringUtils.hasText(fiscalYearId2)) {//Orjinal Fiscal
			criteria.add(Restrictions.eq("fp2.fiscalYear.id", fiscalYearId2));
		}

		if (documentSearchCriteria.getDocTask() != null) {
			criteria.add(Restrictions.eq("s.task", documentSearchCriteria.getDocTask()));
		}

		if (documentSearchCriteria.getDocType() != null) {
			criteria.add(Restrictions.like("s.typeStr", documentSearchCriteria.getDocType().getName() + "%"));
		}

		if (StringUtils.hasText(documentSearchCriteria.getDocNo())) {
			criteria.add(Restrictions.like("s.docNo", "%" + documentSearchCriteria.getDocNo() + "%"));
		}

		if (documentSearchCriteria.getDocStartDate() != null) {
			criteria.add(Restrictions.ge("s.docDate", documentSearchCriteria.getDocStartDate()));
		}

		if (documentSearchCriteria.getDocEndDate() != null) {
			criteria.add(Restrictions.le("s.docDate", documentSearchCriteria.getDocEndDate()));
		}
		if (documentSearchCriteria.getDepartment() != null) {
			criteria.add(Restrictions.eq("s.department.id", documentSearchCriteria.getDepartment().getId()));
		}
		if (documentSearchCriteria.getDepartmentOpp() != null) {
			criteria.add(Restrictions.eq("s.departmentOpp.id", documentSearchCriteria.getDepartmentOpp().getId()));
		}
		if(documentSearchCriteria.getRequestStatus() != null){
			criteria.add(Restrictions.eq("s.requestStatus", documentSearchCriteria.getRequestStatus()));
		}
		if (StringUtils.hasText(documentSearchCriteria.getIsIntegrated())) {//*:hepsi 0:hayir 1:evet 
			if (documentSearchCriteria.getIsIntegrated().equals("0")){
				criteria.add(Restrictions.isNull("s.refFinDocumentId"));
			} else if (documentSearchCriteria.getIsIntegrated().equals("1")){
				criteria.add(Restrictions.isNotNull("s.refFinDocumentId"));
			}
		}

//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.addOrder(Order.desc("s.docDate"));

		List<T> result = criteria.list();

		return result;
	}

}
