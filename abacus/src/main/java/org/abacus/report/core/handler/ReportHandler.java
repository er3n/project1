package org.abacus.report.core.handler;

import java.io.Serializable;
import java.util.List;

import org.abacus.transaction.shared.entity.StkDetailEntity;

public interface ReportHandler extends Serializable{
	
	List<StkDetailEntity> getStkState(String fiscalYearId);
		
}
