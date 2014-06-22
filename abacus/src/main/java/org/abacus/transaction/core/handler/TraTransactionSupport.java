package org.abacus.transaction.core.handler;

import java.math.BigDecimal;
import java.util.List;

import org.abacus.definition.shared.constant.EnumList;
import org.abacus.organization.core.persistance.FiscalDao;
import org.abacus.organization.shared.entity.FiscalPeriodEntity;
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
import org.abacus.transaction.shared.event.CancelDocumentEvent;
import org.abacus.transaction.shared.event.CreateDetailEvent;
import org.abacus.transaction.shared.event.CreateDocumentEvent;
import org.abacus.transaction.shared.event.DeleteDetailEvent;
import org.abacus.transaction.shared.event.DeleteDocumentEvent;
import org.abacus.transaction.shared.event.DetailCreatedEvent;
import org.abacus.transaction.shared.event.DetailDeletedEvent;
import org.abacus.transaction.shared.event.DetailUpdatedEvent;
import org.abacus.transaction.shared.event.DocumentCanceledEvent;
import org.abacus.transaction.shared.event.DocumentCreatedEvent;
import org.abacus.transaction.shared.event.DocumentDeletedEvent;
import org.abacus.transaction.shared.event.DocumentUpdatedEvent;
import org.abacus.transaction.shared.event.ReadDocumentEvent;
import org.abacus.transaction.shared.event.RequestReadDocumentEvent;
import org.abacus.transaction.shared.event.UpdateDetailEvent;
import org.abacus.transaction.shared.event.UpdateDocumentEvent;
import org.abacus.transaction.shared.holder.TraDocumentSearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public abstract class TraTransactionSupport<T extends TraDocumentEntity, D extends TraDetailEntity>  implements TraTransactionHandler<T, D>  {

	

	@Autowired
	protected FiscalDao fiscalDao;

    @Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ReadDocumentEvent<T> readDocument(RequestReadDocumentEvent<T> event) {
		TraDocumentSearchCriteria documentSearchCriteria = event.getDocumentSearchCriteria();
		String username = event.getOrganization();
		String fiscalYearId = event.getFiscalYearId();
		List<T> documentList = getTransactionDao().readDocument(documentSearchCriteria,username,fiscalYearId);
		return new ReadDocumentEvent<T>(documentList);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public DocumentCreatedEvent<T> newDocument(CreateDocumentEvent<T> event) {

		T document = event.getDocument();
		String user = event.getUser();
		String organizationStr = event.getOrganization();

		document.setOrganization(new OrganizationEntity(organizationStr));
		document.createHook(user);
		document.setTrStateDocument(EnumList.TraState.INP.value());
		document.setTypeEnum(document.getTask().getType().getTypeEnum());
		
		FiscalPeriodEntity fiscalPeriod = fiscalDao.findFiscalPeriod(event.getFiscalYear(), document.getDocDate(), document.getTypeEnum());
		document.setFiscalPeriod(fiscalPeriod);
		
		document = getTransactionDao().documentSave(document);

		return new DocumentCreatedEvent<T>(document);
	}

	@Override
	public DocumentUpdatedEvent<T> updateDocument(UpdateDocumentEvent<T> event) throws UnableToUpdateDocumentExpception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DocumentDeletedEvent<T> deleteDocument(DeleteDocumentEvent<T> event) throws UnableToDeleteDocumentException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public DocumentCanceledEvent cancelDocument(CancelDocumentEvent cancelDocumentEvent){
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public DetailCreatedEvent<D> newDetail(CreateDetailEvent<D> event) throws UnableToCreateDetailException {
		D detail = event.getDetail();
		TraDocumentEntity document = detail.getDocument();

		String user = event.getUser();
		
		FiscalYearEntity fiscalYear =  document.getFiscalPeriod().getFiscalYear();
		detail.setFiscalYear(fiscalYear);
		if (detail.getItem().getType().getId().equals(EnumList.DefTypeEnum.ITM_SR_ST.name())){
			BigDecimal baseDetailCount = detail.getItemDetailCount().multiply(detail.getItemUnit().getRatio());
			detail.setBaseDetailCount(baseDetailCount);
		} else { //Fin Defaults
			detail.setItemDetailCount(BigDecimal.ONE);
			detail.setBaseDetailCount(BigDecimal.ONE);
			detail.setLotDetailDate(detail.getDocument().getDocDate());
		}
		
		detail.createHook(user);
		
		detail = getTransactionDao().detailSave(detail);
		
		return new DetailCreatedEvent<D>(detail);
	}

	@Override
	public DetailUpdatedEvent<D> updateDetail(UpdateDetailEvent<D> evet) throws UnableToUpdateDetailException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DetailDeletedEvent<D> deleteDetail(DeleteDetailEvent<D> event) throws UnableToDeleteDetailException {
		// TODO Auto-generated method stub
		return null;
	}
	
	protected abstract TransactionDao<T,D> getTransactionDao();

}
