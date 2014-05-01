package org.abacus.definition.core.handler;

import java.util.List;

import org.abacus.definition.core.persistance.DefValueDao;
import org.abacus.definition.core.persistance.repository.DefValueRepository;
import org.abacus.definition.shared.entity.DefValueEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("serial")
@Service("defValueHandler")
public class DefValueHandlerImpl implements DefValueHandler {

	@Autowired
	private DefValueDao defValueDao;

	@Autowired
	private DefValueRepository defValueRepo;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=true)
	public List<DefValueEntity> getValueList(String typ){
		return defValueRepo.findTypeValueList(typ); 
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public DefValueEntity saveOrUpdateEntity(DefValueEntity entity) {
		System.out.println(entity.getVersion());
		entity = defValueDao.save(entity);
		System.out.println(entity.getVersion());
		return entity;	
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public void deleteEntity(DefValueEntity entity) {
		defValueDao.delete(entity);
	}

}
