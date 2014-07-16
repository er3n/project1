package org.abacus.definition.core.handler;

import java.io.Serializable;
import java.util.List;

import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.entity.DefTaskEntity;
import org.abacus.organization.shared.entity.OrganizationEntity;

public interface DefTaskHandler extends Serializable{

	List<DefTaskEntity> getTaskList(OrganizationEntity organization, EnumList.DefTypeEnum typeEnum);

	List<DefTaskEntity> getTaskList1(String organizationId, EnumList.DefTypeEnum typeEnum);

	DefTaskEntity saveTaskEntity(DefTaskEntity entity);
	
	void deleteTaskEntity(DefTaskEntity entity);
	
}
