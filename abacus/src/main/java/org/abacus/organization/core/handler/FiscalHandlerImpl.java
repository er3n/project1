package org.abacus.organization.core.handler;

import java.util.Date;
import java.util.List;

import org.abacus.common.shared.AbcBusinessException;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.organization.core.persistance.FiscalDao;
import org.abacus.organization.core.persistance.repository.FiscalPeriodRepository;
import org.abacus.organization.core.persistance.repository.FiscalYearRepository;
import org.abacus.organization.shared.entity.FiscalPeriodEntity;
import org.abacus.organization.shared.entity.FiscalYearEntity;
import org.abacus.organization.shared.entity.OrganizationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("serial")
@Service("fiscalHandler")
public class FiscalHandlerImpl implements FiscalHandler {

	@Autowired
	private FiscalDao fiscalDao;
	
	@Autowired
	FiscalYearRepository fiscalYearRepo;
	
	@Autowired
	FiscalPeriodRepository fiscalPeriodRepo;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
	public FiscalYearEntity getFiscalYear(String id) throws AbcBusinessException{
		return fiscalDao.getFiscalYear(id);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
	public List<FiscalYearEntity> findFiscalYearList(OrganizationEntity organization) throws AbcBusinessException{
		return fiscalDao.findFiscalYearList(organization.getId());
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
	public List<FiscalPeriodEntity> findFiscalPeriodList(FiscalYearEntity fiscalYear) throws AbcBusinessException{
		return fiscalDao.findFiscalPeriodList(fiscalYear.getId());
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
	public FiscalPeriodEntity getFiscalPeriod(String id) throws AbcBusinessException{
		return fiscalDao.getFiscalPeriod(id);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
	public FiscalPeriodEntity findFiscalPeriod(FiscalYearEntity fiscalYear, Date docDate, EnumList.DefTypeEnum docTypeEnum) throws AbcBusinessException{
		return fiscalDao.findFiscalPeriod(fiscalYear, docDate, docTypeEnum);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public FiscalYearEntity saveFiscalYearEntity(FiscalYearEntity entity) {
		entity = fiscalYearRepo.save(entity);
		return entity;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public void deleteFiscalYearEntity(FiscalYearEntity entity) {
		fiscalYearRepo.delete(entity);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public FiscalPeriodEntity saveFiscalPeriodEntity(FiscalPeriodEntity entity) {
		entity = fiscalPeriodRepo.save(entity);
		return entity;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public void deleteFiscalPeriodEntity(FiscalPeriodEntity entity) {
		fiscalPeriodRepo.delete(entity);
	}

}
