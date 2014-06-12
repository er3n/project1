package org.abacus.transaction.core.handler;

import java.math.BigDecimal;
import java.util.List;

import org.abacus.definition.shared.constant.EnumList;
import org.abacus.organization.shared.entity.FiscalYearEntity;
import org.abacus.organization.shared.entity.OrganizationEntity;
import org.abacus.transaction.core.persistance.TransactionDao;
import org.abacus.transaction.shared.UnableToCreateDetailException;
import org.abacus.transaction.shared.UnableToDeleteDetailException;
import org.abacus.transaction.shared.UnableToDeleteDocumentException;
import org.abacus.transaction.shared.UnableToUpdateDetailException;
import org.abacus.transaction.shared.UnableToUpdateDocumentExpception;
import org.abacus.transaction.shared.entity.TraDetailEntity;
import org.abacus.transaction.shared.entity.TraDocumentEntity;
import org.abacus.transaction.shared.event.CreateDetailEvent;
import org.abacus.transaction.shared.event.CreateDocumentEvent;
import org.abacus.transaction.shared.event.DeleteDetailEvent;
import org.abacus.transaction.shared.event.DeleteDocumentEvent;
import org.abacus.transaction.shared.event.DetailCreatedEvent;
import org.abacus.transaction.shared.event.DetailDeletedEvent;
import org.abacus.transaction.shared.event.DetailUpdatedEvent;
import org.abacus.transaction.shared.event.DocumentCreatedEvent;
import org.abacus.transaction.shared.event.DocumentDeletedEvent;
import org.abacus.transaction.shared.event.DocumentUpdatedEvent;
import org.abacus.transaction.shared.event.ReadDetailEvent;
import org.abacus.transaction.shared.event.ReadDocumentEvent;
import org.abacus.transaction.shared.event.RequestReadDetailEvent;
import org.abacus.transaction.shared.event.RequestReadDocumentEvent;
import org.abacus.transaction.shared.event.UpdateDetailEvent;
import org.abacus.transaction.shared.event.UpdateDocumentEvent;
import org.abacus.transaction.shared.holder.TraDocumentSearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public abstract class TraTransactionSupport implements TraTransactionHandler {

	@Autowired
	protected TransactionDao transactionDao;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ReadDocumentEvent readDocument(RequestReadDocumentEvent event) {
		TraDocumentSearchCriteria documentSearchCriteria = event.getDocumentSearchCriteria();
		String username = event.getOrganization();
		String fiscalYearId = event.getFiscalYearId();
		
		List<TraDocumentEntity> documentList = transactionDao.readDocument(documentSearchCriteria,username,fiscalYearId);
		
		return new ReadDocumentEvent(documentList);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public DocumentCreatedEvent newDocument(CreateDocumentEvent event) {

		TraDocumentEntity document = event.getDocument();
		String user = event.getUser();
		String organizationStr = event.getOrganization();

		document.setOrganization(new OrganizationEntity(organizationStr));
		document.createHook(user);
		document.setTrStateDocument(EnumList.TraState.INP.value());
		document.setTypeEnum(document.getTask().getType().getTypeEnum());

		document = transactionDao.save(document);

		return new DocumentCreatedEvent(document);
	}

	@Override
	public DocumentUpdatedEvent updateDocument(UpdateDocumentEvent event) throws UnableToUpdateDocumentExpception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DocumentDeletedEvent deleteDocument(DeleteDocumentEvent event) throws UnableToDeleteDocumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReadDetailEvent readDetail(RequestReadDetailEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public DetailCreatedEvent newDetail(CreateDetailEvent event) throws UnableToCreateDetailException {
		TraDetailEntity detail = event.getDetail();
		TraDocumentEntity document = detail.getDocument();

		String user = event.getUser();
		
		FiscalYearEntity fiscalYear =  document.getFiscalPeriod().getFiscalYear();
		detail.setFiscalYear(fiscalYear);

		BigDecimal baseDetailCount = detail.getItemDetailCount().multiply(detail.getItemUnit().getRatio());
		detail.setBaseDetailCount(baseDetailCount);
		
		detail.createHook(user);
		
		detail = transactionDao.save(detail);
		
		return new DetailCreatedEvent(detail);
	}

	@Override
	public DetailUpdatedEvent updateDetail(UpdateDetailEvent evet) throws UnableToUpdateDetailException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DetailDeletedEvent deleteDetail(DeleteDetailEvent event) throws UnableToDeleteDetailException {
		// TODO Auto-generated method stub
		return null;
	}

}
