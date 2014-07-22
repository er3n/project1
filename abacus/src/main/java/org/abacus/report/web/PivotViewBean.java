package org.abacus.report.web;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.abacus.rest.controller.Holder;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

@SuppressWarnings("serial")
@ManagedBean
@RequestScoped
public class PivotViewBean {

	private String jsonResult;

	private String sirket;

	@PostConstruct
	public void init() {

	}

	public void find() {
		jsonResult = dummyData();
	}

	public String dummyData() {
		List<Holder> holders = holders();

		ObjectMapper mapper = new ObjectMapper();

		String result = null;
		try {
			result = mapper.writeValueAsString(holders);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(result.toString());
		return result.toString();
	}

	private List<Holder> holders() {
		List<Holder> holders = new ArrayList<>();
		Random randomGenerator = new Random();
		int tutar, yil;

		if (sirket.equals("1")) {

			for (int i = 0; i < 20; i++) {
				tutar = randomGenerator.nextInt(10000) * randomGenerator.nextInt(10);
				yil = randomGenerator.nextInt(10);
				Holder holder = new Holder();
				holder.setFinansalYil(2010 + yil);
				holder.setTutar(new BigDecimal(tutar));
				holder.setIslem("Gelir");
				holder.setSirket("Adiyaman Ticaret");
				holders.add(holder);

				tutar = randomGenerator.nextInt(10000) * randomGenerator.nextInt(8) * (-1);
				yil = randomGenerator.nextInt(10);
				holder = new Holder();
				holder.setFinansalYil(2010 + yil);
				holder.setTutar(new BigDecimal(tutar));
				holder.setIslem("Gider");
				holder.setSirket("Adiyaman Ticaret");
				holders.add(holder);
			}
		}

		if (sirket.equals("2")) {

			for (int i = 0; i < 20; i++) {
				tutar = randomGenerator.nextInt(10000) * randomGenerator.nextInt(10);
				yil = randomGenerator.nextInt(10);
				Holder holder = new Holder();
				holder.setFinansalYil(2010 + yil);
				holder.setTutar(new BigDecimal(tutar));
				holder.setIslem("Gelir");
				holder.setSirket("Dedeman Otel");
				holders.add(holder);

				tutar = randomGenerator.nextInt(10000) * randomGenerator.nextInt(8) * (-1);
				yil = randomGenerator.nextInt(10);
				holder = new Holder();
				holder.setFinansalYil(2010 + yil);
				holder.setTutar(new BigDecimal(tutar));
				holder.setIslem("Gider");
				holder.setSirket("Dedeman Otel");
				holders.add(holder);
			}
		}

		return holders;
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
