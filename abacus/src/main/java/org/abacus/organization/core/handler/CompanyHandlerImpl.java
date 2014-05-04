package org.abacus.organization.core.handler;

import java.util.List;

import org.abacus.common.web.SessionInfoHelper;
import org.abacus.organization.core.persistance.repository.CompanyRepository;
import org.abacus.organization.shared.entity.CompanyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("companyHandler")
public class CompanyHandlerImpl implements CompanyHandler {

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private SessionInfoHelper sessionInfoHelper;		

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
	public List<CompanyEntity> findByCompany(String company) {
		return companyRepository.findByCompany(company);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public CompanyEntity saveCompanyEntity(CompanyEntity entity) {
		return companyRepository.save(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public void deleteCompanyEntity(CompanyEntity entity) {
		companyRepository.delete(entity);
	}
	
}
