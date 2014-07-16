package org.abacus.organization.core.handler;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.abacus.common.shared.AbcBusinessException;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.organization.shared.entity.FiscalPeriodEntity;
import org.abacus.organization.shared.entity.FiscalYearEntity;

public interface FiscalHandler extends Serializable{

	FiscalYearEntity getFiscalYear(String id) throws AbcBusinessException;

	List<FiscalYearEntity> findFiscalYearList(String organizationId) throws AbcBusinessException;

	FiscalYearEntity saveFiscalYearEntity(FiscalYearEntity entity);
	
	void deleteFiscalYearEntity(FiscalYearEntity entity);
	

	
	FiscalPeriodEntity getFiscalPeriod(String id) throws AbcBusinessException;

	List<FiscalPeriodEntity> findFiscalPeriodList(String fiscalYearId) throws AbcBusinessException;
	
	FiscalPeriodEntity saveFiscalPeriodEntity(FiscalPeriodEntity entity);
	
	void deleteFiscalPeriodEntity(FiscalPeriodEntity entity);
	
	FiscalPeriodEntity findFiscalPeriod(String fiscalYearId, Date docDate, EnumList.DefTypeEnum docTypeEnum) throws AbcBusinessException;


}
