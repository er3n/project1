package org.abacus.organization.core.handler;

import java.util.List;

import org.abacus.organization.core.persistance.repository.DepartmentRepository;
import org.abacus.organization.shared.entity.DepartmentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("departmentHandler")
public class DepartmentHandlerImpl implements DepartmentHandler {

	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
	public List<DepartmentEntity> findByCompany(String company) {
		List<DepartmentEntity> list = departmentRepository.findByCompany(company);
		return list;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public DepartmentEntity saveDepartmentEntity(DepartmentEntity entity) {
		return departmentRepository.save(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public void deleteDepartmentEntity(DepartmentEntity entity) {
		departmentRepository.delete(entity);
	}
	
}
