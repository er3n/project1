package org.abacus.definition.core.handler;

import java.io.Serializable;
import java.util.List;

import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.entity.DefReferenceEntity;

public interface DefReferenceHandler extends Serializable{

	List<DefReferenceEntity> getReferenceList(String organizationId, EnumList.DefTypeEnum typeEnum);

	DefReferenceEntity saveReferenceEntity(DefReferenceEntity entity);
	
	void deleteReferenceEntity(DefReferenceEntity entity);
	
}
