package org.abacus.organization.core.handler;

import java.io.Serializable;
import java.util.List;

import org.abacus.definition.shared.constant.EnumList;
import org.abacus.organization.shared.entity.DepartmentEntity;
import org.abacus.organization.shared.entity.OrganizationEntity;

public interface DepartmentHandler extends Serializable{

	List<DepartmentEntity> findByOrganizationAndGroup(OrganizationEntity organization, EnumList.OrgDepartmentGroupEnum groupEnum);

	DepartmentEntity saveDepartmentEntity(DepartmentEntity entity);
	
	void deleteDepartmentEntity(DepartmentEntity entity);

	DepartmentEntity getDepartmentEntity(Long id);

	List<DepartmentEntity> findUserDepartmentListOrgLike(String username, EnumList.OrgDepartmentGroupEnum depGroup, OrganizationEntity organizationId);
	List<DepartmentEntity> findUserDepartmentListOrgOnly(String username, EnumList.OrgDepartmentGroupEnum depGroup, OrganizationEntity organizationId);
	
}
