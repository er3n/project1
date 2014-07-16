package org.abacus.organization.core.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.abacus.definition.shared.constant.EnumList;
import org.abacus.organization.core.persistance.repository.FiscalYearRepository;
import org.abacus.organization.shared.entity.FiscalYearEntity;
import org.abacus.organization.shared.entity.OrganizationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
public class OrganizationUtils {

	@Autowired
	private FiscalYearRepository fiscalYearRepository;

	public static OrganizationEntity findRootOrganization(OrganizationEntity child) {
		OrganizationEntity orgEntity = findLevelOrganization(child, EnumList.OrgOrganizationLevelEnum.L0);
		return orgEntity;
	}

	public static OrganizationEntity findCompanyOrganization(OrganizationEntity child) {
		OrganizationEntity orgEntity = findLevelOrganization(child, EnumList.OrgOrganizationLevelEnum.L1);
		return orgEntity;
	}

	public static OrganizationEntity findLevelOrganization(OrganizationEntity child, EnumList.OrgOrganizationLevelEnum level) {
		int currentLevelIndex = child.getLevel().ordinal();
		int requestLevelIndex = level.ordinal();
		if (requestLevelIndex > currentLevelIndex) {
			return null;
		}
		OrganizationEntity orgEntity = child;
		while (requestLevelIndex < currentLevelIndex) {
			orgEntity = orgEntity.getParent();
			requestLevelIndex++;
		}
		return orgEntity;
	}

	public static List<OrganizationEntity> getParentList(OrganizationEntity orgEntity){
		List<OrganizationEntity> list = new ArrayList<>();
		list.add(orgEntity);
		int currentLevelIndex = orgEntity.getLevel().ordinal();
		int requestLevelIndex = 0;
		while (requestLevelIndex < currentLevelIndex) {
			orgEntity = orgEntity.getParent();
			list.add(orgEntity);
			requestLevelIndex++;
		}
		return list;
	}
	
	public Set<FiscalYearEntity> findFiscalYearSet(OrganizationEntity defaultOrganization) {
		
		if (defaultOrganization == null) {
			return null;
		}
		
		Set<FiscalYearEntity> fiscalYearSet = fiscalYearRepository.findFiscalYearSet(defaultOrganization.getId());
		
		return fiscalYearSet;
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public FiscalYearEntity findDefaultFiscalYear(OrganizationEntity defaultOrganization) {

		OrganizationEntity companyOrganization = findCompanyOrganization(defaultOrganization);

		if (companyOrganization == null) {
			return null;
		}

		Set<FiscalYearEntity> fiscalYearSet = this.findFiscalYearSet(companyOrganization);

		FiscalYearEntity fiscalYear = findDefaultFiscalYear(fiscalYearSet);

		return fiscalYear;
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public FiscalYearEntity findDefaultFiscalYear(Set<FiscalYearEntity> fiscalYearSet) {

		if (CollectionUtils.isEmpty(fiscalYearSet)) {
			return null;
		}

		FiscalYearEntity defaultFiscalYear = fiscalYearSet.iterator().next();
		Date now = Calendar.getInstance().getTime();
		for (FiscalYearEntity fiscalYear : fiscalYearSet) {
			if (fiscalYear.getDateStart().before(now) && fiscalYear.getDateFinish().after(now)) {
				defaultFiscalYear = fiscalYear;
			}
		}

		return defaultFiscalYear;
	}

}
