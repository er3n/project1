package org.abacus.organization.core.handler;

import java.util.List;

import org.abacus.common.web.SessionInfoHelper;
import org.abacus.organization.core.persistance.CompanyDao;
import org.abacus.organization.core.persistance.repository.CompanyRepository;
import org.abacus.organization.shared.entity.OrganizationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("companyHandler")
public class CompanyHandlerImpl implements CompanyHandler {

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private CompanyDao companyDao;

	@Autowired
	private SessionInfoHelper sessionInfoHelper;		

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
	public List<OrganizationEntity> findByCompany(String company) {
		return companyRepository.findByCompany(company);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public OrganizationEntity saveCompanyEntity(OrganizationEntity entity) {
		OrganizationEntity parent = companyDao.findParentCompany(entity);
		entity.setParent(parent);
		return companyRepository.save(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public void deleteCompanyEntity(OrganizationEntity entity) {
		companyRepository.delete(entity);
	}
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
	public OrganizationEntity findParentCompany(OrganizationEntity child) {
		OrganizationEntity cmp = companyDao.findParentCompany(child);
		return cmp;
	}
	
}
