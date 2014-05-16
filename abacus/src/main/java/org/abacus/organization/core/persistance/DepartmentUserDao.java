package org.abacus.organization.core.persistance;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.abacus.user.shared.entity.SecUserDepartmentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@SuppressWarnings("serial")
@Service
public class DepartmentUserDao implements Serializable {

	@PersistenceContext
	private EntityManager em;


	@Autowired
	private DepartmentUserDao departmentUserRepository;
	
	public List<SecUserDepartmentEntity> findByOrganization(String organizationId){
		List<SecUserDepartmentEntity> list = departmentUserRepository.findByOrganization(organizationId);
		
		return list;
	}
	
}
