package org.abacus.definition.core.handler;

import java.io.Serializable;
import java.util.List;

import org.abacus.definition.shared.entity.DefStateEntity;

public interface DefStateHandler extends Serializable{

	List<DefStateEntity> getStateList(String typeId);

	DefStateEntity saveStateEntity(DefStateEntity entity);
	
	void deleteStateEntity(DefStateEntity entity);
	
}
