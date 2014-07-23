package org.abacus.report.shared.holder;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("serial")
public class SqlDataHolder implements Serializable {

	private List<Map<String, Object>> sqlDataList; 
	private Set<String> sqlColumnSet;
	
	public SqlDataHolder(List<Map<String, Object>> sqlDataList, Set<String> sqlColumnSet){
		this.sqlDataList = sqlDataList;
		this.sqlColumnSet = sqlColumnSet;	
	}
	
	public List<Map<String, Object>> getSqlDataList() {
		return sqlDataList;
	}
	public void setSqlDataList(List<Map<String, Object>> sqlDataList) {
		this.sqlDataList = sqlDataList;
	}
	public Set<String> getSqlColumnSet() {
		return sqlColumnSet;
	}
	public void setSqlColumnSet(Set<String> sqlColumnSet) {
		this.sqlColumnSet = sqlColumnSet;
	} 

}
