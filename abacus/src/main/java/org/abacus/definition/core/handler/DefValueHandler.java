package org.abacus.definition.core.handler;

import java.io.Serializable;
import java.util.List;

import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.entity.DefValueEntity;

public interface DefValueHandler extends Serializable{

	List<DefValueEntity> getValueList(String organizationId, EnumList.DefTypeEnum typeEnum);

	List<DefValueEntity> getValueList(String organizationId, EnumList.DefTypeEnum typeEnum, EnumList.DefTypeEnum itemEnum);

	DefValueEntity saveValueEntity(DefValueEntity entity);
	
	void deleteValueEntity(DefValueEntity entity);
	
	void refreshTypeLevel(String organizationId, EnumList.DefTypeEnum typeEnum);
	
}
