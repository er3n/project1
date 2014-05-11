package org.abacus.definition.core.handler;

import java.io.Serializable;
import java.util.List;

import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.entity.DefParamAnswerEntity;

public interface DefParamAnswerHandler extends Serializable{

	List<DefParamAnswerEntity> getParamAnswerList(EnumList.DefParameterEnum paramAnswerEnum, String organizationId);

	DefParamAnswerEntity saveParamAnswerEntity(DefParamAnswerEntity entity);
	
	void deleteParamAnswerEntity(DefParamAnswerEntity entity);
	
}
