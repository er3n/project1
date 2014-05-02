package org.abacus.organization.core.handler;

import java.util.List;

import org.abacus.organization.core.persistance.repository.CompanyRepository;
import org.abacus.organization.shared.entity.CompanyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("serial")
@Service("companyHandler")
public class CompanyHandlerImpl implements CompanyHandler {

	@Autowired
	private CompanyRepository companyRepository;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
	public List<CompanyEntity> findAllOrderById(){
		return companyRepository.findAllOrderById();
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
