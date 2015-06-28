package org.abacus.test;

import org.abacus.transaction.core.handler.TraIntegrationHandler;
import org.abacus.transaction.shared.UnableToCreateDetailException;
import org.abacus.transaction.shared.entity.FinDocumentEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {//
		"classpath*:/appcontext/persistence-context.xml",//
		"classpath*:/appcontext/main-context.xml",//
		"classpath*:/appcontext/cache-context.xml" })
//
@TransactionConfiguration(defaultRollback = true)
public class TestIntegration {

	@Autowired
	@Qualifier("traIntegrationHandler")
	private TraIntegrationHandler traIntegrationHandler;

	@Before
	public void init() {

	}
	
	@Test
	public void testTransferStkDetails() throws UnableToCreateDetailException{
		
		FinDocumentEntity finDoc = traIntegrationHandler.createFinFromStk(1835L);
		System.out.println("ID:"+finDoc.getId()+" No:"+finDoc.getDocNo());
		
	}

}
