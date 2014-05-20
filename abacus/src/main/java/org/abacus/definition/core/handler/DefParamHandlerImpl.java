package org.abacus.definition.core.handler;

import java.util.List;

import org.abacus.definition.core.persistance.repository.DefParamRepository;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.entity.DefParamEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("defParamHandler")
public class DefParamHandlerImpl implements DefParamHandler {
 
	@Autowired
	private DefParamRepository defParamRepository;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
	public List<DefParamEntity> getParamList(EnumList.DefTypeEnum typeEnum){
		List<DefParamEntity> list = defParamRepository.getParamList(typeEnum.name());
		return list;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public DefParamEntity saveParamEntity(DefParamEntity entity) {
		entity.setId(entity.getType().getId()+"_"+entity.getCode());
		return defParamRepository.save(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public void deleteParamEntity(DefParamEntity entity) {
		defParamRepository.delete(entity);
	}
	
}
