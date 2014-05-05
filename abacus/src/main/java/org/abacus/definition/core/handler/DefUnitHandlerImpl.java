package org.abacus.definition.core.handler;

import java.util.List;

import org.abacus.definition.core.persistance.repository.DefUnitCodeRepository;
import org.abacus.definition.core.persistance.repository.DefUnitGroupRepository;
import org.abacus.definition.shared.entity.DefUnitCodeEntity;
import org.abacus.definition.shared.entity.DefUnitGroupEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("serial")
@Service("defUnitHandler")
public class DefUnitHandlerImpl implements DefUnitHandler {

	@Autowired
	private DefUnitGroupRepository unitGroupRepo;

	@Autowired
	private DefUnitCodeRepository unitCodeRepo;



	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
	public List<DefUnitGroupEntity> getUnitGroupList(String organizationId){
		return unitGroupRepo.getUnitGroupList(organizationId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public DefUnitGroupEntity saveUnitGroupEntity(DefUnitGroupEntity entity){
		entity = unitGroupRepo.save(entity);
		return entity;	
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public void deleteUnitGroupEntity(DefUnitGroupEntity entity){
		unitGroupRepo.delete(entity);
	}

	//
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
	public List<DefUnitCodeEntity> getUnitCodeList(Long unitGroupId){
		return unitCodeRepo.getUnitCodeList(unitGroupId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public DefUnitCodeEntity saveUnitCodeEntity(DefUnitCodeEntity entity){
		entity = unitCodeRepo.save(entity);
		return entity;	
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public void deleteUnitCodeEntity(DefUnitCodeEntity entity){
		unitCodeRepo.delete(entity);
	}
	
}
