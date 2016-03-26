package org.abacus.transaction.core.handler;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.abacus.definition.shared.constant.EnumList;
import org.abacus.organization.core.persistance.FiscalDao;
import org.abacus.organization.core.util.OrganizationUtils;
import org.abacus.organization.shared.entity.FiscalPeriodEntity;
import org.abacus.organization.shared.entity.FiscalYearEntity;
import org.abacus.organization.shared.entity.OrganizationEntity;
import org.abacus.transaction.core.persistance.TraTransactionDao;
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
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public abstract class TraTransactionSupport<T extends TraDocumentEntity, D extends TraDetailEntity> implements TraTransactionHandler<T, D> {

	@Autowired
	protected FiscalDao fiscalDao;
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ReadDocumentEvent<T> readDocumentList(TraTransactionDao<T, D> dao, RequestReadDocumentEvent<T> event) {
		TraDocumentSearchCriteria documentSearchCriteria = event.getDocumentSearchCriteria();
		String organizationId = event.getOrganization()==null?null:event.getOrganization().getId();
		String fiscalYearId2 = event.getFiscalYear2()==null?null:event.getFiscalYear2().getId();
		List<T> documentList = dao.readTraDocument(documentSearchCriteria, organizationId, fiscalYearId2);
		return new ReadDocumentEvent<T>(documentList);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public DocumentCreatedEvent<T> newDocumentSupport(CrudRepository<T,Long> repo, CreateDocumentEvent<T> event) {

		T document = event.getDocument();
		String user = event.getUser();
		OrganizationEntity organization = event.getOrganization();

		document.setOrganization(organization);
		document.createHook(user);
		document.setTypeEnum(document.getTask().getType().getTypeEnum());
		
		//Proje yada Sirket FiscalPeriod
		FiscalPeriodEntity fiscalPeriod2 = fiscalDao.findFiscalPeriod(event.getFiscalYear2(), document.getDocDate(), document.getTypeEnum());
		document.setFiscalPeriod2(fiscalPeriod2);

		if (event.getFiscalYear2().getOrganization().getLevel().equals(EnumList.OrgOrganizationLevelEnum.L1)){
			document.setFiscalPeriod1(fiscalPeriod2);
		} else {
			//Sirket FiscalPeriod
			OrganizationEntity orgCompany =  event.getFiscalYear2().getOrganization(); //OrganizationUtils.findLevelOrganization(event.getFiscalYear2().getOrganization(), EnumList.OrgOrganizationLevelEnum.L1);
			FiscalYearEntity fiscalCompany = fiscalDao.getFiscalYear(orgCompany.getId(), document.getDocDate());
			FiscalPeriodEntity fiscalPeriod1 = fiscalDao.findFiscalPeriod(fiscalCompany, document.getDocDate(), document.getTypeEnum());
			document.setFiscalPeriod1(fiscalPeriod1);
		}

		document = repo.save(document);

		return new DocumentCreatedEvent<T>(document);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public DetailCreatedEvent<D> newDetailSupport(CrudRepository<D,Long> repo, CreateDetailEvent<D> event) throws UnableToCreateDetailException {
		D detail = event.getDetail();
		TraDocumentEntity document = detail.getDocument();
		String user = event.getUser();
		
		if (document.getTypeEnum().name().startsWith(EnumList.DefTypeGroupEnum.STK.name()) && detail.getItem().getType().getId().equals(EnumList.DefTypeEnum.ITM_SR_ST.name())){
			//Stok Fisi, Stok Itemi ise 
			BigDecimal baseDetailCount = detail.getItemDetailCount().multiply(detail.getItemUnit().getRatio());
			detail.setBaseDetailCount(baseDetailCount);
			if (detail.getTrStateDetail().compareTo(1)==0){
				detail.setUnitDetailPrice(detail.getBaseDetailAmount().divide(detail.getItemDetailCount(), EnumList.RoundScale.ACC.getValue(), RoundingMode.HALF_EVEN));
			} else {//Cikis Islemlerinde Fiyat ve SKT girilmez 
				detail.setBaseDetailAmount(BigDecimal.ZERO);
				detail.setUnitDetailPrice(BigDecimal.ZERO);
				detail.setDueDate(document.getDocDate());
			}
		}else if(document.getTypeEnum().name().startsWith(EnumList.DefTypeGroupEnum.REQ.name())){
			detail.setBaseDetailCount(BigDecimal.ZERO);
			detail.setUnitDetailPrice(BigDecimal.ZERO);
			detail.setBaseDetailAmount(BigDecimal.ZERO);
			detail.setDueDate(document.getDocDate());
		}
		else { //Fin Defaults
			if (detail.getItemDetailCount()==null || detail.getItemDetailCount().compareTo(BigDecimal.ZERO)==0){
				detail.setItemDetailCount(BigDecimal.ONE);
			}
			if (detail.getBaseDetailCount()==null){
				detail.setBaseDetailCount(BigDecimal.ZERO);
			}
			detail.setUnitDetailPrice(detail.getBaseDetailAmount().divide(detail.getItemDetailCount(), EnumList.RoundScale.ACC.getValue(), RoundingMode.HALF_EVEN));
			detail.setDueDate(document.getDocDate());
		}
		
		if (detail.getResource()==null){
			detail.setResource(document.getTypeEnum().getTypeGroupEnum());
		}
		detail.createHook(user);
		
		detail = repo.save(detail);
		
		return new DetailCreatedEvent<D>(detail);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public TraBulkUpdatedEvent<T,D> bulkUpdate(TraTransactionHandler<T, D> handler,  TraBulkUpdateEvent<T, D> bulkUpdateEvent){
		
		T document = bulkUpdateEvent.getDocument();
		List<D> detailList = bulkUpdateEvent.getDetailList();
		String user = bulkUpdateEvent.getUser();
		OrganizationEntity organization = bulkUpdateEvent.getOrganization();
		FiscalYearEntity fiscalYear2 = bulkUpdateEvent.getFiscalYear2();
		
		if(document.getId() == null){
			DocumentCreatedEvent<T> documentCreatedEvent = handler.newDocument(new CreateDocumentEvent<T>(document, user, organization, fiscalYear2));
			document = documentCreatedEvent.getDocument();
		}else{
			DocumentUpdatedEvent<T> documentUpdatedEvent = handler.updateDocument(new UpdateDocumentEvent<T>(document, user));
			document = documentUpdatedEvent.getDocument();
		}
		
		if(!CollectionUtils.isEmpty(detailList)){
			
			for(D detail : detailList){
				
				if(detail.getEntityStatus() == null){
					continue;
				}
				
				if(detail.getEntityStatus().equals(EnumList.EntityStatus.NEW)){
					detail.setDocument(document);
					handler.newDetail(new CreateDetailEvent<D>(detail, user));
				}else if(detail.getEntityStatus().equals(EnumList.EntityStatus.UPDATE)){
					handler.updateDetail(new UpdateDetailEvent<D>(detail, user));
				}else if(detail.getEntityStatus().equals(EnumList.EntityStatus.DELETE)){
					handler.deleteDetail(new DeleteDetailEvent<D>(detail));
				}			
			}
			
		}
		
		return new TraBulkUpdatedEvent<T,D>(document, detailList);
	}

	
	public ReadDetailEvent<D> readPRDetailList(RequestReadDetailEvent<D> event) {
		return null;
	}

}
