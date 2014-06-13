package org.abacus.organization.core.handler;

import java.io.Serializable;
import java.util.List;

import org.abacus.common.shared.AbcBusinessException;
import org.abacus.organization.shared.entity.OrganizationEntity;

public interface OrganizationHandler extends Serializable{

	OrganizationEntity findOne(String organizationId);
	
	List<OrganizationEntity> findByOrganization(String organization);
	
	OrganizationEntity saveOrganizationEntity(OrganizationEntity entity) throws AbcBusinessException;
	
	void deleteOrganizationEntity(OrganizationEntity entity);

	OrganizationEntity findParentOrganization(OrganizationEntity child);

}
