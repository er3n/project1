package org.abacus.report.shared.holder;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("serial")
public class SqlDataHolder implements Serializable {

	private List<Map<String, Object>> sqlDataList; 
	private Set<String> sqlFieldSet;
	
	public SqlDataHolder(List<Map<String, Object>> sqlDataList, Set<String> sqlFieldSet){
		this.sqlDataList = sqlDataList;
		this.sqlFieldSet = sqlFieldSet;	
	}
	
	public List<Map<String, Object>> getSqlDataList() {
		return sqlDataList;
	}
	public void setSqlDataList(List<Map<String, Object>> sqlDataList) {
		this.sqlDataList = sqlDataList;
	}

	public Set<String> getSqlFieldSet() {
		return sqlFieldSet;
	}

	public void setSqlFieldSet(Set<String> sqlFieldSet) {
		this.sqlFieldSet = sqlFieldSet;
	}

}
