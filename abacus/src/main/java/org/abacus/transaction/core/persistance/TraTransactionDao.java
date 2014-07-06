package org.abacus.transaction.core.persistance;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

	public List<T> readTraDocument(TraDocumentSearchCriteria documentSearchCriteria, String organization, String fiscalYearId) {
		Session currentSession = em.unwrap(Session.class);
		Criteria criteria = currentSession.createCriteria(getDocumentClass(), "s");
		criteria.createAlias("s.fiscalPeriod", "fp", JoinType.INNER_JOIN);
		criteria.createAlias("s.item", "itm", JoinType.LEFT_OUTER_JOIN);

		if (documentSearchCriteria.getDocumentId() != null) {
			criteria.add(Restrictions.eq("s.id", documentSearchCriteria.getDocumentId()));
		}

		if (StringUtils.hasText(organization)) {
			criteria.add(Restrictions.like("s.organization.id", organization + "%"));
		}

		if (StringUtils.hasText(fiscalYearId)) {
			criteria.add(Restrictions.eq("fp.fiscalYear.id", fiscalYearId));
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

		criteria.addOrder(Order.desc("s.docDate"));

		List<T> result = criteria.list();

		return result;
	}

}
