package org.abacus.definition.core.handler;

import java.io.Serializable;
import java.util.List;

import org.abacus.definition.shared.entity.DefTypeEntity;

public interface DefTypeHandler extends Serializable{

	List<DefTypeEntity> getTypeList(String groupEnum);

	DefTypeEntity saveTypeEntity(DefTypeEntity entity);
	
	void deleteTypeEntity(DefTypeEntity entity);

}
