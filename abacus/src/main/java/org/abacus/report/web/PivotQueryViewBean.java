package org.abacus.report.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.abacus.common.web.JsfMessageHelper;
import org.abacus.common.web.SessionInfoHelper;
import org.abacus.report.core.handler.SqlQueryHandler;
import org.abacus.report.shared.holder.SqlDataHolder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PivotQueryViewBean {

	@ManagedProperty(value = "#{sqlQueryHandler}")
	private SqlQueryHandler sqlQueryHandler;
	
	@ManagedProperty(value = "#{jsfMessageHelper}")
	private JsfMessageHelper jsfMessageHelper;

	@ManagedProperty(value = "#{sessionInfoHelper}")
	private SessionInfoHelper sessionInfoHelper;

	private List<String> pivotRowSet = new ArrayList<String>();
	private List<String> pivotColSet = new ArrayList<String>();
	private List<String> pivotValSet = new ArrayList<String>();

	private String sqlText = "select * from def_value";
	private String jsonResult;
	private String sqlField;
	private Set<String> sqlFieldSet;

	@PostConstruct
	public void init() {
	}

	public void find() {
		jsonResult = null;
		jsonResult = getJsonData();
	}

	public void refresh() {
	}

	public String getJsonData() {
		List<Map<String, Object>> listMap = getData();
		ObjectMapper mapper = new ObjectMapper();
		String result = null;
			try {
				result = mapper.writeValueAsString(listMap);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//		System.out.println(result.toString());
		return result.toString();
	}

	private List<Map<String, Object>> getData() {
		SqlDataHolder sqlDataHolder = sqlQueryHandler.getSqlData(sqlText);
		this.sqlFieldSet = sqlDataHolder.getSqlFieldSet();
		return sqlDataHolder.getSqlDataList();
	}

	public void addToRow(){
		if (sqlField==null && !sqlField.equals("")){
			return;
		}
		pivotColSet.remove(sqlField);
		pivotValSet.remove(sqlField);
		if (pivotRowSet.contains(sqlField)){
			pivotRowSet.remove(sqlField);
		} else {
			pivotRowSet.add(sqlField);
		}
	}

	public void addToCol(){
		if (sqlField==null && !sqlField.equals("")){
			return;
		}
		pivotRowSet.remove(sqlField);
		pivotValSet.remove(sqlField);
		if (pivotColSet.contains(sqlField)){
			pivotColSet.remove(sqlField);
		} else {
			pivotColSet.add(sqlField);
		}
	}

	public void addToVal(){
		if (sqlField==null && !sqlField.equals("")){
			return;
		}
		pivotColSet.remove(sqlField);
		pivotRowSet.remove(sqlField);
		if (pivotValSet.contains(sqlField)){
			pivotValSet.remove(sqlField);
		} else {
			pivotValSet.add(sqlField);
		}
	}

	public String getPivotFieldStr(List<String> set) {
		StringBuffer sb = new StringBuffer();
		for (String field : set) {
			sb.append("'"+field+"',");
		}
		return sb.toString();
	}
	
	public String getPivotRows() {
		return getPivotFieldStr(pivotRowSet);
	}

	public String getPivotCols() {
		return getPivotFieldStr(pivotColSet);
	}

	public String getPivotVals() {
		return getPivotFieldStr(pivotValSet);
	}
	
	public String getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(String jsonResult) {
		this.jsonResult = jsonResult;
	}

	public JsfMessageHelper getJsfMessageHelper() {
		return jsfMessageHelper;
	}

	public void setJsfMessageHelper(JsfMessageHelper jsfMessageHelper) {
		this.jsfMessageHelper = jsfMessageHelper;
	}

	public SessionInfoHelper getSessionInfoHelper() {
		return sessionInfoHelper;
	}

	public void setSessionInfoHelper(SessionInfoHelper sessionInfoHelper) {
		this.sessionInfoHelper = sessionInfoHelper;
	}

	public SqlQueryHandler getSqlQueryHandler() {
		return sqlQueryHandler;
	}

	public void setSqlQueryHandler(SqlQueryHandler sqlQueryHandler) {
		this.sqlQueryHandler = sqlQueryHandler;
	}

	public String getSqlField() {
		return sqlField;
	}

	public void setSqlField(String sqlField) {
		this.sqlField = sqlField;
	}

	public Set<String> getSqlFieldSet() {
		return sqlFieldSet;
	}

	public void setSqlFieldSet(Set<String> sqlFieldSet) {
		this.sqlFieldSet = sqlFieldSet;
	}

	public List<String> getPivotRowSet() {
		return pivotRowSet;
	}

	public void setPivotRowSet(List<String> pivotRowSet) {
		this.pivotRowSet = pivotRowSet;
	}

	public List<String> getPivotColSet() {
		return pivotColSet;
	}

	public void setPivotColSet(List<String> pivotColSet) {
		this.pivotColSet = pivotColSet;
	}

	public List<String> getPivotValSet() {
		return pivotValSet;
	}

	public void setPivotValSet(List<String> pivotValSet) {
		this.pivotValSet = pivotValSet;
	}

	public String getSqlText() {
		return sqlText;
	}

	public void setSqlText(String sqlText) {
		this.sqlText = sqlText;
	}

}
