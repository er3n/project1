package org.abacus.organization.core.handler;

import java.util.ArrayList;
import java.util.List;

import org.abacus.definition.shared.constant.EnumList;
import org.abacus.organization.core.persistance.DepartmentDao;
import org.abacus.organization.core.persistance.repository.DepartmentRepository;
import org.abacus.organization.shared.entity.DepartmentEntity;
import org.abacus.user.core.persistance.repository.UserDepartmentRepository;
import org.abacus.user.shared.entity.SecUserDepartmentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("serial")
@Service("departmentHandler")
public class DepartmentHandlerImpl implements DepartmentHandler {

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private UserDepartmentRepository userDepartmentRepository;

	@Autowired
	private DepartmentDao departmentDao;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
	public List<DepartmentEntity> findUserDepartmentList(String username, EnumList.OrgDepartmentGroupEnum depGroup){
		List<DepartmentEntity> depList = new ArrayList<>();
		List<SecUserDepartmentEntity> userDepList = userDepartmentRepository.findUserDepartmentList(username);
		for (SecUserDepartmentEntity ent : userDepList) {
			if (ent.getDepartment().getDepartmentGroup().equals(depGroup)){
				depList.add(ent.getDepartment());
			}
		}
		return depList;
	}
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
	public List<DepartmentEntity> findByOrganizationAndGroup(String organizationId, EnumList.OrgDepartmentGroupEnum groupEnum) {
		List<DepartmentEntity> list = departmentRepository.findByOrganizationAndGroup(organizationId, groupEnum);
		return list;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public DepartmentEntity saveDepartmentEntity(DepartmentEntity entity) {
		List<SecUserDepartmentEntity> list = new ArrayList<>();
		if (entity.getDepartmentUserList()!=null){
			for (SecUserDepartmentEntity user : entity.getDepartmentUserList()) {
				user.setId(null);
				list.add(user);
			}
		}
		entity = departmentRepository.save(entity);
		userDepartmentRepository.deleteDepartmentUsers(entity.getId());
		userDepartmentRepository.save(list);
		return entity;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public void deleteDepartmentEntity(DepartmentEntity entity) {
		departmentRepository.delete(entity);
	}
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
	public DepartmentEntity getDepartmentEntity(Long id){
		return departmentDao.findDepartment(id); 
	}

	public DepartmentRepository getDepartmentRepository() {
		return departmentRepository;
	}

	public void setDepartmentRepository(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}

	public UserDepartmentRepository getUserDepartmentRepository() {
		return userDepartmentRepository;
	}

	public void setUserDepartmentRepository(
			UserDepartmentRepository userDepartmentRepository) {
		this.userDepartmentRepository = userDepartmentRepository;
	}

	public DepartmentDao getDepartmentDao() {
		return departmentDao;
	}

	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}

}




	