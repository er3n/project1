package org.abacus.definition.core.handler;

import java.util.List;

import org.abacus.definition.core.persistance.repository.DefReferenceRepository;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.entity.DefReferenceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("defReferenceHandler")
public class DefReferenceHandlerImpl implements DefReferenceHandler {

	@Autowired
	private DefReferenceRepository defReferenceRepository;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
	public List<DefReferenceEntity> getReferenceList(String organizationId, EnumList.DefTypeEnum typeEnum){
		List<DefReferenceEntity> list = defReferenceRepository.getReferenceList(organizationId, typeEnum.name());
		return list;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public DefReferenceEntity saveReferenceEntity(DefReferenceEntity entity) {
		return defReferenceRepository.save(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public void deleteReferenceEntity(DefReferenceEntity entity) {
		defReferenceRepository.delete(entity);
	}
	
}
