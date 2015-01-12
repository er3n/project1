package org.abacus.organization.core.persistance;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.abacus.common.shared.AbcBusinessException;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.organization.core.persistance.repository.FiscalPeriodRepository;
import org.abacus.organization.core.persistance.repository.FiscalYearRepository;
import org.abacus.organization.shared.FiscalPeriodNotFoundException;
import org.abacus.organization.shared.FiscalPeriodNotOpenException;
import org.abacus.organization.shared.FiscalYearDocumentDateNotMatchedException;
import org.abacus.organization.shared.FiscalYearNotFoundException;
import org.abacus.organization.shared.entity.FiscalPeriodEntity;
import org.abacus.organization.shared.entity.FiscalYearEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@SuppressWarnings("serial")
@Service
public class FiscalDao implements Serializable {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private FiscalYearRepository fiscalYearRepository;

	@Autowired
	private FiscalPeriodRepository fiscalPeriodRepository;

	public FiscalYearEntity getFiscalYear(String id) throws AbcBusinessException {
		return fiscalYearRepository.findOne(id);
	}

	public FiscalYearEntity getFiscalYear(String organizationId, Date date) throws AbcBusinessException {
		List<FiscalYearEntity> fiscalList = findFiscalYearList(organizationId);
		for (FiscalYearEntity fis : fiscalList) {
			if (date.compareTo(fis.getDateStart())>=0 && date.compareTo(fis.getDateFinish())<=0){
				return fis;
			}
		}
		throw new FiscalYearNotFoundException(organizationId,date.toString());
	}
	
	public List<FiscalYearEntity> findFiscalYearList(String organizationId) throws AbcBusinessException {
		Set<FiscalYearEntity> fiscalSet = fiscalYearRepository.findFiscalYearSet(organizationId);
		List<FiscalYearEntity> fiscalList = new ArrayList<FiscalYearEntity>(fiscalSet);
		return fiscalList;
	}

	public List<FiscalPeriodEntity> findFiscalPeriodList(String fiscalYearId) throws AbcBusinessException {
		Set<FiscalPeriodEntity> fiscalPeriodSet = fiscalPeriodRepository.findFiscalPeriodSet(fiscalYearId);
		List<FiscalPeriodEntity> fiscalPeriodList = new ArrayList<FiscalPeriodEntity>(fiscalPeriodSet);
		return fiscalPeriodList;
	}

	public FiscalPeriodEntity getFiscalPeriod(String id) throws AbcBusinessException {
		return fiscalPeriodRepository.findOne(id);
	}

	public FiscalPeriodEntity findFiscalPeriod(FiscalYearEntity fiscalYearEntity, Date docDate, EnumList.DefTypeEnum docTypeEnum) throws AbcBusinessException {
		
		if (fiscalYearEntity==null){
			throw new FiscalYearNotFoundException("fiscalYearEntity=null");
		}
		
		if (docDate.compareTo(fiscalYearEntity.getDateStart())<0 || docDate.compareTo(fiscalYearEntity.getDateFinish())>0){
			throw new FiscalYearDocumentDateNotMatchedException(fiscalYearEntity.getId());
		}
		
		FiscalPeriodEntity fiscalPeriod = fiscalPeriodRepository.findFiscalPeriod(fiscalYearEntity.getId(), docDate);
		if (fiscalPeriod==null){
			throw new FiscalPeriodNotFoundException(fiscalYearEntity.getId()+"-"+docDate.toString());
		}
		if (docTypeEnum.name().startsWith(EnumList.DefTypeGroupEnum.ACC.name()) && !fiscalPeriod.getIsAccActive()){
			throw new FiscalPeriodNotOpenException(fiscalYearEntity.getId(), EnumList.DefTypeGroupEnum.ACC.name());
		}
		if (docTypeEnum.name().startsWith(EnumList.DefTypeGroupEnum.FIN.name()) && !fiscalPeriod.getIsFinActive()){
			throw new FiscalPeriodNotOpenException(fiscalYearEntity.getId(), EnumList.DefTypeGroupEnum.FIN.name());
		}
		if (docTypeEnum.name().startsWith(EnumList.DefTypeGroupEnum.STK.name()) && !fiscalPeriod.getIsStkActive()){
			throw new FiscalPeriodNotOpenException(fiscalYearEntity.getId(), EnumList.DefTypeGroupEnum.STK.name());
		}
		return fiscalPeriod;
	}	

}
