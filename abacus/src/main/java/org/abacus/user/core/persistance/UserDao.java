package org.abacus.user.core.persistance;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.abacus.user.core.persistance.repository.GroupRepository;
import org.abacus.user.core.persistance.repository.UserRepository;
import org.abacus.user.shared.entity.SecGroupEntity;
import org.abacus.user.shared.entity.SecUserEntity;
import org.abacus.user.shared.holder.SearchUserCriteria;
import org.abacus.user.shared.holder.UserSearchResultRecordHolder;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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
		criteria.createAlias("u.groupMemberList", "gm");
		criteria.createAlias("gm.group", "g");
		
		if(StringUtils.hasText(searchUserCriteria.getUsername())){
			criteria.add(Restrictions.eq("u.id", searchUserCriteria.getUsername()));
		}
		
		if(StringUtils.hasText(searchUserCriteria.getCompany())){
			criteria.add(Restrictions.eqOrIsNull("u.companyEntity.id", searchUserCriteria.getCompany()));
		}
		
		if(!CollectionUtils.isEmpty(searchUserCriteria.getGroupList())){
			
			List<Long> groupListLong = new ArrayList<>();
			for(SecGroupEntity group : searchUserCriteria.getGroupList()){
				groupListLong.add(group.getId());
			}
			
			criteria.add(Restrictions.in("gm.group.id", groupListLong));

		}

		criteria.addOrder(Order.asc("u.id"));
		
		List<SecUserEntity> userList = criteria.list();
		
		return userList;
	}
}
