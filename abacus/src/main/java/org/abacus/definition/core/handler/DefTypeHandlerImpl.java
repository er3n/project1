package org.abacus.definition.core.handler;

import java.util.List;

import org.abacus.common.web.SessionInfoHelper;
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

	@Autowired
	private SessionInfoHelper sessionInfoHelper;		

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
	public List<DefTypeEntity> getTypeList(String groupEnum){
		System.out.println("ViewBean Handler User:"+sessionInfoHelper.currentUserName());
		System.out.println("ViewBean Handler Comp:"+sessionInfoHelper.currentCompany());
		
		return defTypeRepository.getTypeList(groupEnum);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public DefTypeEntity saveTypeEntity(DefTypeEntity entity) {
		return defTypeRepository.save(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public void deleteTypeEntity(DefTypeEntity entity) {
		defTypeRepository.delete(entity);
	}
	
}
