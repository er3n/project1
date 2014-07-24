package org.abacus.report.core.handler;

import java.util.List;

import org.abacus.report.core.persistance.repository.RepPivotRepository;
import org.abacus.report.shared.entity.RepPivotEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("serial")
@Service("repPivotHandler")
public class RepPivotHandlerImpl implements RepPivotHandler {

	@Autowired
	private RepPivotRepository pivotRepo;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public List<RepPivotEntity> findReport(String organizationId){
		return pivotRepo.findReport(organizationId);
	}

	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public RepPivotEntity save(RepPivotEntity pivotEntity){
		return pivotRepo.save(pivotEntity);
	}

}




	