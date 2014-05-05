package org.abacus.test;

import javax.persistence.EnumType;

import org.abacus.common.web.SessionInfoHelper;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.organization.core.handler.OrganizationHandler;
import org.abacus.organization.shared.entity.OrganizationEntity;
import org.abacus.user.core.persistance.repository.UserRepository;
import org.abacus.user.shared.UserExistsInGroupException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={//
		"classpath*:/appcontext/persistence-context.xml",//
		"classpath*:/appcontext/main-context.xml",//
		"classpath*:/appcontext/cache-context.xml"})//
@TransactionConfiguration(defaultRollback = true)
public class TestHibernateConfiguration {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrganizationHandler organizationHandler;

	@Autowired
	private SessionInfoHelper SessionInfoHelper;

	@Test
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	@Rollback(value=false)
	public void testAddNewUser(){
		
//		SecUserEntity entity = new SecUserEntity();
//		
//		entity.setActive(true);
//		OrganizationEntity organizationEntity = new OrganizationEntity();
//		organizationEntity.setId("01");
//		entity.setOrganizationEntity(organizationEntity );
//		entity.setId("ADMIN");
//		
//		entity.setPassword("wqwe");
//		
//		userRepository.save(entity);
		
	}
	
	@Test
	@Rollback(false)
	public void testRemoveGroup() throws UserExistsInGroupException{
//		groupHandler.removeGroup(1l);
	}
	
	@Test
	@Rollback(false)
	public void testSpringAbstrationCache(){
//		secGroupHandler.allAuthorities();
//		secGroupHandler.allAuthorities();
	}

	@Test
	@Rollback(value=false)
	public void findParentOrganization(){
		OrganizationEntity child = new OrganizationEntity();
		child.setId("01.01.01");
		child.setLevel(EnumList.OrgOrganizationLevelEnum.L2);
		
		OrganizationEntity parent = organizationHandler.findParentOrganization(child);
		System.out.println("findParentOrganization: "+parent);
	}
	
	@Test
	@Rollback(value=false)
	public void findParentOrganizationWithLevel(){
		OrganizationEntity org = organizationHandler.findOne("01.01.01.01");
		OrganizationEntity root = organizationHandler.findOrganizationWithLevel(org, EnumList.OrgOrganizationLevelEnum.L1);
		System.out.println("rootOrganization: "+root);
	}
}
