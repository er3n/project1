package org.abacus.organization.core.persistance;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.abacus.organization.shared.entity.DepartmentEntity;
import org.abacus.organization.shared.entity.OrganizationEntity;
import org.abacus.user.core.persistance.repository.UserDepartmentRepository;
import org.abacus.user.shared.entity.SecUserDepartmentEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@SuppressWarnings("serial")
@Service
public class DepartmentDao implements Serializable {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private UserDepartmentRepository userDepartmentRepository;

	public List<SecUserDepartmentEntity> findUserDepartmentListOrgLike(String username, OrganizationEntity organization){
		return userDepartmentRepository.findUserDepartmentList(username, organization.getId()+"%");
	}
	public List<SecUserDepartmentEntity> findUserDepartmentListOrgOnly(String username, OrganizationEntity organization){
		return userDepartmentRepository.findUserDepartmentList(username, organization.getId());
	}
	
	public DepartmentEntity findDepartment(Long departmentId) {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(DepartmentEntity.class,"d");
		criteria.createAlias("d.departmentUserList", "u",JoinType.LEFT_OUTER_JOIN);
		criteria.add(Restrictions.eq("d.id", departmentId));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<DepartmentEntity> depList = criteria.list();
		if (!depList.isEmpty()){
			return depList.get(0);
		}
		return null;
	}
	
}
