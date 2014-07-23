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
public class PivotBudgetViewBean {

	@ManagedProperty(value = "#{sqlQueryHandler}")
	private SqlQueryHandler sqlQueryHandler;
	
	@ManagedProperty(value = "#{jsfMessageHelper}")
	private JsfMessageHelper jsfMessageHelper;

	@ManagedProperty(value = "#{sessionInfoHelper}")
	private SessionInfoHelper sessionInfoHelper;
	
	private String selection;
	
	private String jsonResult;

	@PostConstruct
	public void init() {

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
		StringBuffer sb = new StringBuffer();
		sb.append("select doc.fiscal_year_id CalismaYili,");
		sb.append("		  det.fiscal_period_id CalismaDonemi,");
		sb.append("	      (CASE WHEN det.budget_rx='BUD_R' THEN 'Gelir' ELSE 'Gider' END) GelirGider,");
		sb.append("       det.budget_amount*(CASE WHEN det.budget_rx='BUD_R' THEN 1 ELSE -1 END) Tutar");
		sb.append("  from budget_detail det, budget_document doc");
		sb.append(" where det.document_id = doc.id");
		sb.append("   and det.budget_type = 'ESTIMATE'");

		String orgId = sessionInfoHelper.currentOrganization().getId()+":%";
		if (selection.equals("1")){
			orgId = sessionInfoHelper.currentOrganization().getRootOrganization().getId()+"%";
		}
		sb.append("   and doc.fiscal_year_id like '"+orgId+"'");
		listMap = sqlQueryHandler.getSqlData(sb.toString());
		return listMap;
	}

	public String getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(String jsonResult) {
		this.jsonResult = jsonResult;
	}

	public SqlQueryHandler getSqlQueryHandler() {
		return sqlQueryHandler;
	}

	public void setSqlQueryHandler(SqlQueryHandler sqlQueryHandler) {
		this.sqlQueryHandler = sqlQueryHandler;
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

	public String getSelection() {
		return selection;
	}

	public void setSelection(String selection) {
		this.selection = selection;
	}

}
