package org.abacus.transaction.core.handler;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.abacus.definition.shared.constant.EnumList;
import org.abacus.organization.core.persistance.FiscalDao;
import org.abacus.organization.shared.entity.FiscalPeriodEntity;
import org.abacus.organization.shared.entity.FiscalYearEntity;
import org.abacus.organization.shared.entity.OrganizationEntity;
import org.abacus.transaction.core.persistance.TraTransactionDao;
import org.abacus.transaction.core.persistance.repository.TraDetailRepository;
import org.abacus.transaction.core.persistance.repository.TraDocumentRepository;
import org.abacus.transaction.shared.UnableToCreateDetailException;
import org.abacus.transaction.shared.entity.TraDetailEntity;
import org.abacus.transaction.shared.entity.TraDocumentEntity;
import org.abacus.transaction.shared.event.CreateDetailEvent;
import org.abacus.transaction.shared.event.CreateDocumentEvent;
import org.abacus.transaction.shared.event.DeleteDetailEvent;
import org.abacus.transaction.shared.event.DetailCreatedEvent;
import org.abacus.transaction.shared.event.DocumentCreatedEvent;
import org.abacus.transaction.shared.event.DocumentUpdatedEvent;
import org.abacus.transaction.shared.event.ReadDetailEvent;
import org.abacus.transaction.shared.event.ReadDocumentEvent;
import org.abacus.transaction.shared.event.RequestReadDetailEvent;
import org.abacus.transaction.shared.event.RequestReadDocumentEvent;
import org.abacus.transaction.shared.event.TraBulkUpdateEvent;
import org.abacus.transaction.shared.event.TraBulkUpdatedEvent;
import org.abacus.transaction.shared.event.UpdateDetailEvent;
import org.abacus.transaction.shared.event.UpdateDocumentEvent;
import org.abacus.transaction.shared.holder.TraDocumentSearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

public abstract class TraTransactionSupport<T extends TraDocumentEntity, D extends TraDetailEntity<D>>  implements TraTransactionHandler<T, D>  {

	@Autowired
	protected FiscalDao fiscalDao;
	
	protected abstract TraTransactionDao<T,D> getTransactionDao();
	
	protected abstract TraDocumentRepository<T> getDocumentRepository();

	protected abstract TraDetailRepository<D> getDetailRepository();

	
	protected void savePointDetailList(List<D> detailList){
		for (D dtl : detailList) {
			dtl.savePoint();
		}
	}
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ReadDetailEvent<D> readDetailList(RequestReadDetailEvent<D> event) {
		List<D> detailList = getDetailRepository().findByDocumentId(event.getDocumentId());
		savePointDetailList(detailList);
		return new ReadDetailEvent<D>(detailList);
	}
	
    @Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ReadDocumentEvent<T> readDocumentList(RequestReadDocumentEvent<T> event) {
		TraDocumentSearchCriteria documentSearchCriteria = event.getDocumentSearchCriteria();
		String username = event.getOrganization();
		String fiscalYearId = event.getFiscalYearId();
		List<T> documentList = getTransactionDao().readTraDocument(documentSearchCriteria,username,fiscalYearId);
		return new ReadDocumentEvent<T>(documentList);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public DocumentCreatedEvent<T> newDocument(CreateDocumentEvent<T> event) {

		T document = event.getDocument();
		String user = event.getUser();
		String organizationStr = event.getOrganization();

		document.setOrganization(new OrganizationEntity(organizationStr));
		document.createHook(user);
		document.setTypeEnum(document.getTask().getType().getTypeEnum());
		
		FiscalPeriodEntity fiscalPeriod = fiscalDao.findFiscalPeriod(event.getFiscalYear(), document.getDocDate(), document.getTypeEnum());
		document.setFiscalPeriod(fiscalPeriod);
		
		document = getDocumentRepository().save(document);

		return new DocumentCreatedEvent<T>(document);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public DetailCreatedEvent<D> newDetail(CreateDetailEvent<D> event) throws UnableToCreateDetailException {
		D detail = event.getDetail();
		TraDocumentEntity document = detail.getDocument();

		String user = event.getUser();
		
		FiscalYearEntity fiscalYear =  document.getFiscalPeriod().getFiscalYear();
		detail.setFiscalYear(fiscalYear);
		if (document.getTypeEnum().name().startsWith(EnumList.DefTypeGroupEnum.STK.name()) && detail.getItem().getType().getId().equals(EnumList.DefTypeEnum.ITM_SR_ST.name())){
			BigDecimal baseDetailCount = detail.getItemDetailCount().multiply(detail.getItemUnit().getRatio());
			detail.setBaseDetailCount(baseDetailCount);
			if (detail.getTrStateDetail().compareTo(1)==0){
				detail.setUnitDetailPrice(detail.getBaseDetailAmount().divide(detail.getItemDetailCount(), EnumList.RoundScale.ACC.getValue(), RoundingMode.HALF_EVEN));
			} else {//Cikis Islemlerinde Fiyat ve SKT girilmez 
				detail.setBaseDetailAmount(BigDecimal.ZERO);
				detail.setUnitDetailPrice(BigDecimal.ZERO);
				detail.setLotDetailDate(document.getDocDate());
			}
		}else if(document.getTypeEnum().name().startsWith(EnumList.DefTypeGroupEnum.REQ.name())){
			detail.setBaseDetailCount(detail.getItemDetailCount());
			detail.setUnitDetailPrice(BigDecimal.ZERO);
			detail.setBaseDetailAmount(BigDecimal.ZERO);
			detail.setLotDetailDate(document.getDocDate());
		}
		else { //Fin Defaults
			detail.setBaseDetailCount(detail.getItemDetailCount());
			detail.setUnitDetailPrice(detail.getBaseDetailAmount().divide(detail.getItemDetailCount(), EnumList.RoundScale.ACC.getValue(), RoundingMode.HALF_EVEN));
			detail.setLotDetailDate(document.getDocDate());
		}
		
		if (detail.getResource()==null){
			detail.setResource(document.getTypeEnum().getTypeGroupEnum());
		}
		detail.createHook(user);
		detail = getDetailRepository().save(detail);
		detail.savePoint();
		
		return new DetailCreatedEvent<D>(detail);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public TraBulkUpdatedEvent<T,D> bulkUpdate(TraBulkUpdateEvent<T, D> bulkUpdateEvent){
		
		T document = bulkUpdateEvent.getDocument();
		List<D> detailList = bulkUpdateEvent.getDetailList();
		String user = bulkUpdateEvent.getUser();
		String organization = bulkUpdateEvent.getOrganization();
		String fiscalYear = bulkUpdateEvent.getFiscalYear();
		
		if(document.getId() == null){
			DocumentCreatedEvent<T> documentCreatedEvent = newDocument(new CreateDocumentEvent<T>(document, user, organization, fiscalYear));
			document = documentCreatedEvent.getDocument();
		}else{
			DocumentUpdatedEvent<T> documentUpdatedEvent = updateDocument(new UpdateDocumentEvent<T>(document, user));
			document = documentUpdatedEvent.getDocument();
		}
		
		if(!CollectionUtils.isEmpty(detailList)){
			
			for(D detail : detailList){
				
				if(detail.getEntityStatus() == null){
					continue;
				}
				
				if(detail.getEntityStatus().equals(EnumList.EntityStatus.NEW)){
					detail.setDocument(document);
					newDetail(new CreateDetailEvent<D>(detail, user));
				}else if(detail.getEntityStatus().equals(EnumList.EntityStatus.UPDATE)){
					updateDetail(new UpdateDetailEvent<D>(detail, user));
				}else if(detail.getEntityStatus().equals(EnumList.EntityStatus.DELETE)){
					deleteDetail(new DeleteDetailEvent<D>(detail));
				}			
			}
			
		}
		
		return new TraBulkUpdatedEvent<T,D>(document, detailList);
	}

	
}
