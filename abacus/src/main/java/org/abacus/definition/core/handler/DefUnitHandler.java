package org.abacus.definition.core.handler;

import java.io.Serializable;
import java.util.List;

import org.abacus.definition.shared.entity.DefUnitCodeEntity;
import org.abacus.definition.shared.entity.DefUnitGroupEntity;

public interface DefUnitHandler extends Serializable{


	List<DefUnitGroupEntity> getUnitGroupList(String organizationId);

	DefUnitGroupEntity saveUnitGroupEntity(DefUnitGroupEntity entity);
	
	void deleteUnitGroupEntity(DefUnitGroupEntity entity);

	List<DefUnitCodeEntity> getUnitCodeList(Long unitGroupId);

	DefUnitCodeEntity saveUnitCodeEntity(DefUnitCodeEntity entity);
	
	void deleteUnitCodeEntity(DefUnitCodeEntity entity);
	
	
}
