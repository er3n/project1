package org.abacus.report.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.abacus.common.web.JsfMessageHelper;
import org.abacus.common.web.SessionInfoHelper;
import org.abacus.report.core.handler.SqlQueryHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("serial")
@ManagedBean
@RequestScoped
public class PivotQueryViewBean {

	@ManagedProperty(value = "#{sqlQueryHandler}")
	private SqlQueryHandler sqlQueryHandler;
	
	@ManagedProperty(value = "#{jsfMessageHelper}")
	private JsfMessageHelper jsfMessageHelper;

	@ManagedProperty(value = "#{sessionInfoHelper}")
	private SessionInfoHelper sessionInfoHelper;

	private String pivotRows;
	private String pivotCols;
	private String pivotVals;

	private StringBuffer query;
	private String jsonResult;

	@PostConstruct
	public void init() {
		query = new StringBuffer();
		query.append("select doc.fiscal_year_id CalismaYili,");
		query.append("		  det.fiscal_period_id CalismaDonemi,");
		query.append("	      (CASE WHEN det.budget_rx='BUD_R' THEN 'Gelir' ELSE 'Gider' END) GelirGider,");
		query.append("       det.budget_amount*(CASE WHEN det.budget_rx='BUD_R' THEN 1 ELSE -1 END) Tutar");
		query.append("  from budget_detail det, budget_document doc");
		query.append(" where det.document_id = doc.id");
		query.append("   and det.budget_type = 'ESTIMATE'");
		String orgId = sessionInfoHelper.currentOrganization().getId()+":%";
		query.append("   and doc.fiscal_year_id like '"+orgId+"'");
	}

	public void find() {
		jsonResult = getJsonData();
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
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		listMap = sqlQueryHandler.getSqlData(query.toString());
		return listMap;
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

	public String getPivotRows() {
		return pivotRows;
	}

	public void setPivotRows(String pivotRows) {
		this.pivotRows = pivotRows;
	}

	public String getPivotCols() {
		return pivotCols;
	}

	public void setPivotCols(String pivotCols) {
		this.pivotCols = pivotCols;
	}

	public String getPivotVals() {
		return pivotVals;
	}

	public void setPivotVals(String pivotVals) {
		this.pivotVals = pivotVals;
	}

	public SqlQueryHandler getSqlQueryHandler() {
		return sqlQueryHandler;
	}

	public void setSqlQueryHandler(SqlQueryHandler sqlQueryHandler) {
		this.sqlQueryHandler = sqlQueryHandler;
	}

}
