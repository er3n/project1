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
import org.abacus.transaction.shared.event.DetailCreatedEvent;
import org.abacus.transaction.shared.event.DocumentCreatedEvent;
import org.abacus.transaction.shared.event.ReadDetailEvent;
import org.abacus.transaction.shared.event.ReadDocumentEvent;
import org.abacus.transaction.shared.event.RequestReadDetailEvent;
import org.abacus.transaction.shared.event.RequestReadDocumentEvent;
import org.abacus.transaction.shared.holder.TraDocumentSearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public abstract class TraTransactionSupport<T extends TraDocumentEntity, D extends TraDetailEntity<D>>  implements TraTransactionHandler<T, D>  {

	@Autowired
	protected FiscalDao fiscalDao;
	
	protected abstract TraTransactionDao<T,D> getTransactionDao();
	
	protected abstract TraDocumentRepository<T> getDocumentRepository();

	protected abstract TraDetailRepository<D> getDetailRepository();

	
	protected void setDetailSavePoint(List<D> detailList){
		for (D dtl : detailList) {
			dtl.savePoint();
		}
	}
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ReadDetailEvent<D> readDetailList(RequestReadDetailEvent<D> event) {
		List<D> detailList = getDetailRepository().findByDocumentId(event.getDocumentId());
		setDetailSavePoint(detailList);
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
		
		document = getDocumentRepository().save(document);

		return new DocumentCreatedEvent<T>(document);
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
			detail.setUnitDetailPrice(detail.getBaseDetailAmount().divide(detail.getItemDetailCount(), EnumList.RoundScale.ACC.getValue(), RoundingMode.HALF_EVEN));
		} else { //Fin Defaults
			detail.setBaseDetailCount(detail.getItemDetailCount());
			detail.setUnitDetailPrice(detail.getBaseDetailAmount().divide(detail.getItemDetailCount(), EnumList.RoundScale.ACC.getValue(), RoundingMode.HALF_EVEN));
			detail.setLotDetailDate(detail.getDocument().getDocDate());
		}
		
		detail.createHook(user);
		
		detail = getDetailRepository().save(detail);
		detail.savePoint();
		
		return new DetailCreatedEvent<D>(detail);
	}

	
}
