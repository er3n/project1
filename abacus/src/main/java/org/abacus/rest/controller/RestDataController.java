package org.abacus.rest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.abacus.report.core.handler.SqlQueryHandler;
import org.abacus.report.shared.holder.SqlDataHolder;
import org.abacus.user.shared.entity.SecUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//security-context
@RestController
@RequestMapping("/data")	// http://localhost:8088/abacus/app/rest/data
public class RestDataController {
	
	@Autowired
	private SqlQueryHandler sqlQueryHandler;
	
	//  http://localhost:8088/?/app/rest/data/getTakip/tester:f5d1278e8109edd94e1e4197e04873b9/00/2016-03
	// /getTakip/root:MD5/00.01.001/201601
	@RequestMapping(value = "/getTakip/{loginId}/{organizationId}/{periodId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Map<String, Object>> getTakip(
			@PathVariable("loginId") String loginId, 
			@PathVariable("organizationId") String organizationId, 	//IL.IC.OKL
			@PathVariable("periodId")String periodId				//YYYY-MM
			) {
    	List<Map<String, Object>> list = new ArrayList<>();
    	Map<String, Object> map = new HashMap<>();
		if (loginId==null||organizationId==null||periodId==null||loginId.split(":").length!=2){
	    	map.put("wrong param","u:p/il.ic.okl/yyyy-mm");
	    	list.add(map);
	    	return list;			
		}
	    SecUserEntity user = sqlQueryHandler.getUser(loginId.split(":")[0], loginId.split(":")[1]);
	    if (user==null){
	    	map.put("u:p not found",loginId);
	    	list.add(map);
	    	return list;
	    }
	    
		StringBuffer s =new StringBuffer();
		s.append("select f.organization_id kurum,");
		s.append("       p.code personel,");
		s.append("       to_char(m.menu_date,'YYYY-MM') donem,");
		s.append("       m.menu_date tarih,");
		s.append("       z.code izin,");
		s.append("       i.unit_item_count miktar");
		s.append("  from cat_menu m, cat_menu_item i, org_fiscal_year f, def_item p, def_item z");
		s.append(" where f.organization_id = '"+organizationId+"'");
		s.append("	 and to_char(m.menu_date,'YYYY-MM') = '"+periodId+"'");
		s.append("   and i.menu_id = m.id");
		s.append("   and f.id = m.fiscal_year_id");
		s.append("   and p.id = m.person_id");
		s.append("   and z.id = i.item_id");
		
		SqlDataHolder sqlData = sqlQueryHandler.getSqlData(s.toString());
		List<Map<String, Object>> dataList = sqlData.getSqlDataList();
		return dataList;
	}
	
}
