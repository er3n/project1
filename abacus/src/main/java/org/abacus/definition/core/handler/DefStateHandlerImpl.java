package org.abacus.definition.core.handler;

import java.util.List;

import org.abacus.definition.core.persistance.repository.DefStateRepository;
import org.abacus.definition.shared.entity.DefStateEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("defStateHandler")
public class DefStateHandlerImpl implements DefStateHandler {

	@Autowired
	private DefStateRepository defStateRepository;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
	public List<DefStateEntity> getStateList(String typeId){
		List<DefStateEntity> list = defStateRepository.getStateList(typeId);
		return list;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public DefStateEntity saveStateEntity(DefStateEntity entity) {
		entity.setId(entity.getType().getId()+"_"+entity.getCode());
		return defStateRepository.save(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public void deleteStateEntity(DefStateEntity entity) {
		defStateRepository.delete(entity);
	}
	
}
