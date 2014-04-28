package org.abacus.definition.core.handler;

import java.util.List;

import org.abacus.definition.core.persistance.repository.DefTypeRepository;
import org.abacus.definition.shared.entity.DefTypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("defTypeHandler")
public class DefTypeHandlerImpl implements DefTypeHandler {

	@Autowired
	private DefTypeRepository defTypeRepository;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
	public List<DefTypeEntity> getTypeList(){
		return defTypeRepository.findAllOrderById();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public void saveOrUpdateEntity(DefTypeEntity entity) {
		defTypeRepository.save(entity);
//		if (entity.getId()==null || entity.getIsNew()) {
//			defTypeRepository.save(entity);
//		} else {
//			defTypeRepository.update(entity);
//		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public void deleteEntity(DefTypeEntity entity) {
		defTypeRepository.delete(entity);
	}
	
}
