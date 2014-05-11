package org.abacus.definition.core.handler;

import java.util.List;

import org.abacus.definition.core.persistance.repository.DefParamAnswerRepository;
import org.abacus.definition.shared.entity.DefParamAnswerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("defParamAnswerHandler")
public class DefParamAnswerHandlerImpl implements DefParamAnswerHandler {

	@Autowired
	private DefParamAnswerRepository defParamAnswerRepository;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
	public List<DefParamAnswerEntity> getParamAnswerList(String paramId, String organizationId){
		List<DefParamAnswerEntity> list = defParamAnswerRepository.getParamAnswerList(paramId, organizationId);
		return list;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public DefParamAnswerEntity saveParamAnswerEntity(DefParamAnswerEntity entity) {
		return defParamAnswerRepository.save(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public void deleteParamAnswerEntity(DefParamAnswerEntity entity) {
		defParamAnswerRepository.delete(entity);
	}
	
}
