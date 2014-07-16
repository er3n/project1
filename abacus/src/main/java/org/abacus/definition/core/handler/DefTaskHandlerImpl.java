package org.abacus.definition.core.handler;

import java.util.List;

import org.abacus.definition.core.persistance.repository.DefTaskRepository;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.entity.DefTaskEntity;
import org.abacus.organization.shared.entity.OrganizationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("defTaskHandler")
public class DefTaskHandlerImpl implements DefTaskHandler {

	@Autowired
	private DefTaskRepository defTaskRepository;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
	public List<DefTaskEntity> getTaskList(OrganizationEntity organization, EnumList.DefTypeEnum typeEnum){
		List<DefTaskEntity> list = defTaskRepository.getTaskListRepo(organization.getRootOrganization().getId(), typeEnum.name());
		return list;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
	public List<DefTaskEntity> getTaskList1(String organizationId, EnumList.DefTypeEnum typeEnum){
		List<DefTaskEntity> list = defTaskRepository.getTaskListRepo(organizationId, typeEnum.name());
		return list;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public DefTaskEntity saveTaskEntity(DefTaskEntity entity) {
		return defTaskRepository.save(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public void deleteTaskEntity(DefTaskEntity entity) {
		defTaskRepository.delete(entity);
	}
	
}
