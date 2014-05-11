package org.abacus.definition.core.handler;

import java.io.Serializable;
import java.util.List;

import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.entity.DefParamEntity;

public interface DefParamHandler extends Serializable{

	List<DefParamEntity> getParamList(EnumList.DefTypeEnum typeEnum);

	DefParamEntity saveParamEntity(DefParamEntity entity);
	
	void deleteParamEntity(DefParamEntity entity);
	
}
