package org.abacus.organization.core.handler;

import java.util.Date;
import java.util.List;

import org.abacus.common.shared.AbcBusinessException;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.organization.core.persistance.FiscalDao;
import org.abacus.organization.shared.entity.FiscalPeriodEntity;
import org.abacus.organization.shared.entity.FiscalYearEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("serial")
@Service("fiscalHandler")
public class FiscalHandlerImpl implements FiscalHandler {

	@Autowired
	private FiscalDao fiscalDao;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
	public FiscalYearEntity getFiscalYear(String id) throws AbcBusinessException{
		return fiscalDao.getFiscalYear(id);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
	public List<FiscalYearEntity> findFiscalYearList(String organizationId) throws AbcBusinessException{
		return fiscalDao.findFiscalYearList(organizationId);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
	public FiscalPeriodEntity getFiscalPeriod(String id) throws AbcBusinessException{
		return fiscalDao.getFiscalPeriod(id);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
	public FiscalPeriodEntity findFiscalPeriod(String fiscalYearId, Date docDate, EnumList.DefTypeEnum docTypeEnum) throws AbcBusinessException{
		return fiscalDao.findFiscalPeriod(fiscalYearId, docDate, docTypeEnum);
	}

}
