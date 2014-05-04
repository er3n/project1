package org.abacus.organization.core.handler;

import java.io.Serializable;
import java.util.List;

import org.abacus.definition.shared.constant.EnumList;
import org.abacus.organization.shared.entity.DepartmentEntity;

public interface DepartmentHandler extends Serializable{

	List<DepartmentEntity> findByCompanyAndGroup(String companyId, EnumList.OrgDepartmentGroupEnum groupEnum);

	DepartmentEntity saveDepartmentEntity(DepartmentEntity entity);
	
	void deleteDepartmentEntity(DepartmentEntity entity);
	
}
