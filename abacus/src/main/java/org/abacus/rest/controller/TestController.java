package org.abacus.rest.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/service")
public class TestController {

	@RequestMapping(value = "/holder", method = RequestMethod.GET,headers="Accept=application/json")
	public List<Holder> holders(){
		List<Holder> holders = new ArrayList<>();
		Random randomGenerator = new Random();
		int tutar,yil;
		for(int i = 0; i< 20 ; i++){
			tutar = randomGenerator.nextInt(10000)*randomGenerator.nextInt(10);
			yil = randomGenerator.nextInt(10);
			Holder holder = new Holder();
			holder.setFinansalYil(2010+yil);
			holder.setTutar(new BigDecimal(tutar));
			holder.setIslem("Gelir");
			holder.setSirket("Adiyaman Ticaret");
			holders.add(holder);

			tutar = randomGenerator.nextInt(10000)*randomGenerator.nextInt(8)*(-1);
			yil = randomGenerator.nextInt(10);
			holder = new Holder();
			holder.setFinansalYil(2010+yil);
			holder.setTutar(new BigDecimal(tutar));
			holder.setIslem("Gider");
			holder.setSirket("Adiyaman Ticaret");
			holders.add(holder);
		}
		for(int i = 0; i< 20 ; i++){
			tutar = randomGenerator.nextInt(10000)*randomGenerator.nextInt(10);
			yil = randomGenerator.nextInt(10);
			Holder holder = new Holder();
			holder.setFinansalYil(2010+yil);
			holder.setTutar(new BigDecimal(tutar));
			holder.setIslem("Gelir");
			holder.setSirket("Dedeman Otel");
			holders.add(holder);

			tutar = randomGenerator.nextInt(10000)*randomGenerator.nextInt(8)*(-1);
			yil = randomGenerator.nextInt(10);
			holder = new Holder();
			holder.setFinansalYil(2010+yil);
			holder.setTutar(new BigDecimal(tutar));
			holder.setIslem("Gider");
			holder.setSirket("Dedeman Otel");
			holders.add(holder);
		}
		
		return holders;
	}
	
}
