package org.abacus.report.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("serial")
@ManagedBean
@RequestScoped
public class PivotTestViewBean {

	private String jsonResult;

	private String sirket;

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
		Map<String, Object> row = null;
		Random randomGenerator = new Random();
		int tutar, yil;
		if (sirket.equals("1")) {

			for (int i = 0; i < 20; i++) {
				row = new HashMap<String, Object>();
				tutar = randomGenerator.nextInt(10000) * randomGenerator.nextInt(10);
				yil = randomGenerator.nextInt(10);
				row.put("Yil",2010 + yil);
				row.put("Tutar",new BigDecimal(tutar));
				row.put("Islem","Gelir");
				row.put("Sirket","Adiyaman Ticaret");
				listMap.add(row);

				row = new HashMap<String, Object>();
				tutar = randomGenerator.nextInt(10000) * randomGenerator.nextInt(8) * (-1);
				yil = randomGenerator.nextInt(10);
				row.put("Yil",2010 + yil);
				row.put("Tutar",new BigDecimal(tutar));
				row.put("Islem","Gider");
				row.put("Sirket","Adiyaman Ticaret");
				listMap.add(row);
			}
		}

		if (sirket.equals("2")) {

			for (int i = 0; i < 20; i++) {
				row = new HashMap<String, Object>();
				tutar = randomGenerator.nextInt(10000) * randomGenerator.nextInt(10);
				yil = randomGenerator.nextInt(10);
				row.put("Yil",2010 + yil);
				row.put("Tutar",new BigDecimal(tutar));
				row.put("Islem","Gelir");
				row.put("Sirket","Dedeman Otel");
				listMap.add(row);

				row = new HashMap<String, Object>();
				tutar = randomGenerator.nextInt(10000) * randomGenerator.nextInt(8) * (-1);
				yil = randomGenerator.nextInt(10);
				row.put("Yil",2010 + yil);
				row.put("Tutar",new BigDecimal(tutar));
				row.put("Islem","Gider");
				row.put("Sirket","Dedeman Otel");
				listMap.add(row);
			}
		}
		return listMap;
	}

	public String getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(String jsonResult) {
		this.jsonResult = jsonResult;
	}

	public String getSirket() {
		return sirket;
	}

	public void setSirket(String sirket) {
		this.sirket = sirket;
	}

}
