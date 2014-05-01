package org.abacus.user.core.persistance;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.abacus.user.core.persistance.repository.UserRepository;
import org.abacus.user.shared.entity.SecGroupEntity;
import org.abacus.user.shared.entity.SecUserEntity;
import org.abacus.user.shared.holder.SearchUserCriteria;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Service
public class UserDao implements Serializable {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private UserRepository userRepository;

	public List<SecUserEntity> findUser(
			SearchUserCriteria searchUserCriteria) {

		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(SecUserEntity.class,"u");
		criteria.createAlias("u.groupMemberList", "gm",JoinType.LEFT_OUTER_JOIN);
		criteria.createAlias("u.companyList", "uc",JoinType.LEFT_OUTER_JOIN);
		
		if(StringUtils.hasText(searchUserCriteria.getUsername())){
			criteria.add(Restrictions.eq("u.id", searchUserCriteria.getUsername()));
		}
		
		if(searchUserCriteria.getCompany() != null){
			criteria.add(Restrictions.like("uc.company.id", searchUserCriteria.getCompany().getId() + "%"));
		}
		
		if(!CollectionUtils.isEmpty(searchUserCriteria.getGroupList())){
			
			List<Long> groupListLong = new ArrayList<>();
			for(SecGroupEntity group : searchUserCriteria.getGroupList()){
				groupListLong.add(group.getId());
			}
			
			criteria.add(Restrictions.in("gm.group.id", groupListLong));

		}

		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		criteria.addOrder(Order.asc("u.id"));
		
		List<SecUserEntity> userList = criteria.list();
		
		return userList;
	}
}
