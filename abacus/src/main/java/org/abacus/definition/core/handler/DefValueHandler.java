package org.abacus.definition.core.handler;

import java.io.Serializable;
import java.util.List;

import org.abacus.definition.shared.entity.DefValueEntity;

public interface DefValueHandler extends Serializable{

	List<DefValueEntity> getValueList(String typ);

	void saveOrUpdateEntity(DefValueEntity entity);
	
	void deleteEntity(DefValueEntity entity);
	
}
