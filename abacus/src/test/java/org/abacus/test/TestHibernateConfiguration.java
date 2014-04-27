package org.abacus.test;

import org.abacus.user.core.persistance.repository.UserRepository;
import org.abacus.user.shared.entity.CompanyEntity;
import org.abacus.user.shared.entity.SecUserEntity;
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
@ContextConfiguration(locations={"classpath*:/appcontext/persistence-context.xml"})
@TransactionConfiguration(defaultRollback = true)
public class TestHibernateConfiguration {

	@Autowired
	private UserRepository userRepository;
	
	@Test
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	@Rollback(value=false)
	public void testAddNewUser(){
		
		SecUserEntity entity = new SecUserEntity();
		
		entity.setActive(true);
		CompanyEntity companyEntity = new CompanyEntity();
		companyEntity.setId("01");
		entity.setCompanyEntity(companyEntity );
		entity.setId("ADMÝN");
		
		entity.setPassword("wqwe");
		
		userRepository.save(entity);
		
		
	}

}
