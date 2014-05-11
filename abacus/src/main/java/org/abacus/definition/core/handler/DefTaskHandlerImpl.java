package org.abacus.definition.core.handler;

import java.util.List;

import org.abacus.definition.core.persistance.repository.DefTaskRepository;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.entity.DefTaskEntity;
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
	public List<DefTaskEntity> getTaskList(String organizationId, EnumList.DefTypeEnum typeEnum){
		List<DefTaskEntity> list = defTaskRepository.getTaskList(organizationId, typeEnum.name());
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
