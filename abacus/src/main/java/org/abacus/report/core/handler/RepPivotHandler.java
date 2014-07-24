package org.abacus.report.core.handler;

import java.io.Serializable;
import java.util.List;

import org.abacus.report.shared.entity.RepPivotEntity;

public interface RepPivotHandler extends Serializable{

	public List<RepPivotEntity> findReport(String organizationId);

	public RepPivotEntity save(RepPivotEntity pivotEntity);
	
}
