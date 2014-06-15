package org.abacus.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import org.abacus.common.shared.AbcBusinessException;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.organization.core.persistance.FiscalDao;
import org.abacus.organization.shared.entity.FiscalPeriodEntity;
import org.abacus.organization.shared.entity.FiscalYearEntity;
import org.abacus.test.fixture.TransactionFixture;
import org.abacus.transaction.core.handler.TraTransactionHandler;
import org.abacus.transaction.core.persistance.repository.StkDetailRepository;
import org.abacus.transaction.core.persistance.repository.StkDetailTrackRepository;
import org.abacus.transaction.core.persistance.repository.StkDocumentRepository;
import org.abacus.transaction.shared.UnableToCreateDetailException;
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
@ContextConfiguration(locations = {//
		"classpath*:/appcontext/persistence-context.xml",//
		"classpath*:/appcontext/main-context.xml",//
		"classpath*:/appcontext/cache-context.xml" })
//
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
	
	@Autowired
	private StkDetailTrackRepository detailTrackRepository;

	@Autowired
	private FiscalDao fiscalDao;

	private String user = "admin";

	private String organization = "#";

	@Before
	public void init() {

	}

	private DocumentCreatedEvent newStkTransaction(EnumList.DefTypeEnum documentType) {
		DocumentCreatedEvent createdEvent = stkTransaction.newDocument(transactionFixture.newDocument(user, organization, documentType));
		return createdEvent;
	}

	@Test
	public void testNewStkTransaction() {

		DocumentCreatedEvent createdEvent = this.newStkTransaction(EnumList.DefTypeEnum.STK_IO_I);

		Long documentId = createdEvent.getDocument().getId();

		StkDocumentEntity document = documentRepository.findOne(documentId);

		assertNotNull(document);

	}

	@Test
	public void testNewStkDetailInput() throws UnableToCreateDetailException {

		TraDocumentEntity document = this.newStkTransaction(EnumList.DefTypeEnum.STK_IO_I).getDocument();

		document = documentRepository.findWithFetch(document.getId());

		CreateDetailEvent createDetailEvent = transactionFixture.newDetail(document, user,new BigDecimal(3000));

		DetailCreatedEvent event = stkTransaction.newDetail(createDetailEvent);

		Long detailId = event.getDetail().getId();

		StkDetailEntity detail = detailRepository.findOne(detailId);

		assertNotNull(detail);

	}

	@Test
	public void testNewStkDetailOutput() throws UnableToCreateDetailException {
		//INPUT
		TraDocumentEntity inDocument = this.newStkTransaction(EnumList.DefTypeEnum.STK_IO_I).getDocument();
		inDocument = documentRepository.findWithFetch(inDocument.getId());

		for (int i = 0; i < 3; i++) {
			CreateDetailEvent createDetailEventIn = transactionFixture.newDetail(inDocument, user, new BigDecimal(3000));
			DetailCreatedEvent eventIn = stkTransaction.newDetail(createDetailEventIn);
		}

		//OUTPUT
		TraDocumentEntity outDocument = this.newStkTransaction(EnumList.DefTypeEnum.STK_IO_O).getDocument();
		outDocument = documentRepository.findWithFetch(outDocument.getId());
		
		CreateDetailEvent createDetailEventOut1 = transactionFixture.newDetail(outDocument, user, new BigDecimal(5000));
		DetailCreatedEvent eventOut1 = stkTransaction.newDetail(createDetailEventOut1);

		CreateDetailEvent createDetailEventOut2 = transactionFixture.newDetail(outDocument, user, new BigDecimal(1200));
		DetailCreatedEvent eventOut2 = stkTransaction.newDetail(createDetailEventOut2);

		//TEST
		StkDetailEntity outDetail = (StkDetailEntity) eventOut2.getDetail(); 
		BigDecimal restCount = detailTrackRepository.currentItemCount(outDetail.getItem().getId(), outDetail.getDepartment().getId(),outDetail.getFiscalYear().getId());
		
		BigDecimal testCount = new BigDecimal(2.8).setScale(EnumList.RoundScale.STK.getValue(), RoundingMode.HALF_EVEN);
		boolean result = restCount.compareTo(testCount) == 0;
		assertTrue(result);
		
	}
	@Test
	public void testTransferStkDetails() throws UnableToCreateDetailException{
		
		//INPUT
		TraDocumentEntity inDocument = this.newStkTransaction(EnumList.DefTypeEnum.STK_IO_I).getDocument();
		inDocument = documentRepository.findWithFetch(inDocument.getId());

		for (int i = 0; i < 3; i++) {
			CreateDetailEvent createDetailEventIn = transactionFixture.newDetail(inDocument, user, new BigDecimal(3000));
			DetailCreatedEvent eventIn = stkTransaction.newDetail(createDetailEventIn);
		}
		
		//TRANSFER
		TraDocumentEntity transferDocument = this.newStkTransaction(EnumList.DefTypeEnum.STK_TT_T).getDocument();
		transferDocument = documentRepository.findWithFetch(transferDocument.getId());
		
		CreateDetailEvent createDetailEventTransfer = transactionFixture.newTransfer(transferDocument, user, new BigDecimal(15000));
		
		stkTransaction.newDetail(createDetailEventTransfer);
		
		System.out.println("Bitti");
		
	}

	@Test
	public void testFiscalPeriod() throws AbcBusinessException{
		FiscalYearEntity fiscalYear = fiscalDao.findFiscalYear("#.#:2014");
		Date now = new Date();
		FiscalPeriodEntity period = fiscalDao.findFiscalPeriod(fiscalYear, now, EnumList.DefTypeEnum.STK_IO_I);
		System.out.println(period.getId());
	}
	
}
