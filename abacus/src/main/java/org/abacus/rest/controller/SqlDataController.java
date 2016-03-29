package org.abacus.rest.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.abacus.report.core.handler.SqlQueryHandler;
import org.abacus.report.shared.holder.SqlDataHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//security-context
@RestController
@RequestMapping("/service")
public class SqlDataController {

	@Autowired
	private SqlQueryHandler sqlQueryHandler;

//	http://localhost:8080/abacus/app/rest/service/sqlDataGet
	@RequestMapping(value = "/sqlDataGet", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Map<String, Object>> sqlDataGet() {
		SqlDataHolder sqlData = sqlQueryHandler.getSqlData("select * from def_type");
		return sqlData.getSqlDataList();
	}

//	http://localhost:8080/abacus/app/rest/service/sqlDataPut?sqlText=select * from def_type
	@RequestMapping(value = "/sqlDataPut", method = RequestMethod.PUT, headers = "Accept=application/json")
	public List<Map<String, Object>> sqlDataPut(@RequestParam("sqlText") String sqlText) {
		SqlDataHolder sqlData = sqlQueryHandler.getSqlData(sqlText);
		return sqlData.getSqlDataList();
	}

//	http://localhost:8080/abacus/app/rest/service/sqlFieldGet
	@RequestMapping(value = "/sqlFieldGet", method = RequestMethod.GET, headers = "Accept=application/json")
	public Set<String> sqlFieldGet() {
		SqlDataHolder sqlData = sqlQueryHandler.getSqlData("select * from def_type");
		return sqlData.getSqlFieldSet();
	}

//	http://localhost:8080/abacus/app/rest/service/sqlFieldPut?sqlText=select * from def_type
	@RequestMapping(value = "/sqlFieldPut", method = RequestMethod.PUT, headers = "Accept=application/json")
	public Set<String> sqlFieldPut(@RequestParam("sqlText") String sqlText) {
		SqlDataHolder sqlData = sqlQueryHandler.getSqlData(sqlText);
		return sqlData.getSqlFieldSet();
	}
	
}
