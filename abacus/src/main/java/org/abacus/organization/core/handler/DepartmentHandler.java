package org.abacus.organization.core.handler;

import java.io.Serializable;
import java.util.List;

import org.abacus.organization.shared.entity.DepartmentEntity;

public interface DepartmentHandler extends Serializable{

	List<DepartmentEntity> findByCompany(String company);

	DepartmentEntity saveDepartmentEntity(DepartmentEntity entity);
	
	void deleteDepartmentEntity(DepartmentEntity entity);
	
}
