package org.abacus.test;

import static org.junit.Assert.assertTrue;

import org.abacus.test.fixture.TransactionFixture;
import org.abacus.transaction.core.handler.TraTransactionHandler;
import org.abacus.transaction.core.persistance.repository.StkDocumentRepository;
import org.abacus.transaction.shared.entity.StkDocumentEntity;
import org.abacus.transaction.shared.event.DocumentCreatedEvent;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={//
		"classpath*:/appcontext/persistence-context.xml",//
		"classpath*:/appcontext/main-context.xml",//
		"classpath*:/appcontext/cache-context.xml"})//
@TransactionConfiguration(defaultRollback = true)
public class TestTransactionHandler {
	
	@Autowired
	@Qualifier("stkTransactionHandler")
	private TraTransactionHandler stkTransaction;
	
	@Autowired
	@Qualifier("finTransactionHandler")
	private TraTransactionHandler finTransaction;
	
	@Autowired
	private TransactionFixture transactionFixture;
	
	@Autowired
	private StkDocumentRepository documentRepository;
	
	private String user = "admin";
	
	private String organization = "#";
	
	@Before
	public void init(){
		
	}
		 
	@Test
	public void newStkTransaction(){
		
		DocumentCreatedEvent createdEvent = stkTransaction.newDocument(transactionFixture.newDocument(user,organization));
		
		Long documentId = createdEvent.getDocument().getId();
		
		StkDocumentEntity document = documentRepository.findOne(documentId);
		 
		assertTrue("Docment can not created",document != null);
		 
	}


}
