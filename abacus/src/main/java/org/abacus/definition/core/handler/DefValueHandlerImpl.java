package org.abacus.definition.core.handler;

import java.util.List;

import org.abacus.definition.core.persistance.repository.DefValueRepository;
import org.abacus.definition.shared.entity.DefValueEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("defValueHandler")
public class DefValueHandlerImpl implements DefValueHandler {

	@Autowired
	private DefValueRepository defValueRepository;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
	public List<DefValueEntity> getValueList(String typ){
		return defValueRepository.findTypeValues(typ); 
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public void saveOrUpdateEntity(DefValueEntity entity) {
		defValueRepository.save(entity);
//		if (entity.getId()==null || entity.getIsNew()) {
//			defValueRepository.save(entity);
//		} else {
//			defValueRepository.update(entity);
//		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public void deleteEntity(DefValueEntity entity) {
		defValueRepository.delete(entity);
	}

}
