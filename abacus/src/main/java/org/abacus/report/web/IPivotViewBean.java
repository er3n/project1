package org.abacus.report.web;

public interface IPivotViewBean {

	public void findPivotData();
	
	public String getPivotRows();

	public String getPivotCols();

	public String getPivotVals();
	
	public String getJsonResult();	
	
}
