package org.abacus.definition.core.handler;

import java.io.Serializable;
import java.util.List;

import org.abacus.definition.shared.entity.DefTaskEntity;

public interface DefTaskHandler extends Serializable{

	List<DefTaskEntity> getTaskList(String organizationId, String typeId);

	DefTaskEntity saveTaskEntity(DefTaskEntity entity);
	
	void deleteTaskEntity(DefTaskEntity entity);
	
}
