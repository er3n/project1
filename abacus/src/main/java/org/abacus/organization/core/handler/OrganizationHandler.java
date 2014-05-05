package org.abacus.organization.core.handler;

import java.io.Serializable;
import java.util.List;

import org.abacus.organization.shared.entity.OrganizationEntity;

public interface OrganizationHandler extends Serializable{

	List<OrganizationEntity> findByOrganization(String company);
	
	OrganizationEntity saveOrganizationEntity(OrganizationEntity entity);
	
	void deleteOrganizationEntity(OrganizationEntity entity);

	OrganizationEntity findParentCompany(OrganizationEntity child);

}
