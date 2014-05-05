package org.abacus.organization.core.handler;

import java.io.Serializable;
import java.util.List;

import org.abacus.organization.shared.entity.OrganizationEntity;

public interface CompanyHandler extends Serializable{

	List<OrganizationEntity> findByCompany(String company);
	
	OrganizationEntity saveCompanyEntity(OrganizationEntity entity);
	
	void deleteCompanyEntity(OrganizationEntity entity);

	OrganizationEntity findParentCompany(OrganizationEntity child);

}
