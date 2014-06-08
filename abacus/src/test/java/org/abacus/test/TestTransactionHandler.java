package org.abacus.test;

import static org.junit.Assert.*;

import org.abacus.test.fixture.TransactionFixture;
import org.abacus.transaction.core.handler.TraTransactionHandler;
import org.abacus.transaction.core.persistance.repository.StkDetailRepository;
import org.abacus.transaction.core.persistance.repository.StkDocumentRepository;
import org.abacus.transaction.shared.entity.StkDetailEntity;
import org.abacus.transaction.shared.entity.StkDocumentEntity;
import org.abacus.transaction.shared.entity.TraDocumentEntity;
import org.abacus.transaction.shared.event.CreateDetailEvent;
import org.abacus.transaction.shared.event.DetailCreatedEvent;
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
	
	@Autowired
	private StkDetailRepository detailRepository;
	
	private String user = "admin";
	
	private String organization = "#";
	
	@Before
	public void init(){
		
	}
	
	private DocumentCreatedEvent newStkTransaction(){
		DocumentCreatedEvent createdEvent = stkTransaction.newDocument(transactionFixture.newDocument(user,organization));
		return createdEvent;
	}
		 
	@Test
	public void testNewStkTransaction(){
		
		DocumentCreatedEvent createdEvent = this.newStkTransaction();
		
		Long documentId = createdEvent.getDocument().getId();
		
		StkDocumentEntity document = documentRepository.findOne(documentId);
		 
		assertNotNull(document);
		 
	}
	
	@Test
	public void testNewStkDetail(){
		
		TraDocumentEntity document = this.newStkTransaction().getDocument();
		
		document = documentRepository.findWithFetch(document.getId());
		
		CreateDetailEvent createDetailEvent = transactionFixture.newDetail(document,user);
		
		DetailCreatedEvent event = stkTransaction.newDetail(createDetailEvent);
		
		Long detailId = event.getDetail().getId();
		
		StkDetailEntity detail = detailRepository.findOne(detailId);
		
		assertNotNull(detail);
		
	}


}
