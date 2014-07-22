package org.abacus.rest.controller;

import java.util.List;
import java.util.Map;

import org.abacus.report.jasper.JasperReportHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service")
public class SqlDataController {

	@Autowired
	private JasperReportHandler jasperReportHandler;

//	http://localhost:8080/abacus/app/rest/service/sqlDataGet
	@RequestMapping(value = "/sqlDataGet", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Map<String, Object>> sqlDataGet() {
		List<Map<String, Object>> sqlData = jasperReportHandler.getSqlData("select * from def_type");
		return sqlData;
	}

//	http://localhost:8080/abacus/app/rest/service/sqlDataPut?sqlText=select * from def_type
	@RequestMapping(value = "/sqlDataPut", method = RequestMethod.PUT, headers = "Accept=application/json")
	public List<Map<String, Object>> sqlDataPut(@RequestParam("sqlText") String sqlText) {
		List<Map<String, Object>> sqlData = jasperReportHandler.getSqlData(sqlText);
		return sqlData;
	}
}
