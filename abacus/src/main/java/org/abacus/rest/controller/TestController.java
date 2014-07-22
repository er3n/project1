package org.abacus.rest.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/service")
public class TestController {

	@RequestMapping(value = "/holder", method = RequestMethod.GET,headers="Accept=application/json")
	public List<Holder> holders(){
		List<Holder> holders = new ArrayList<>();
		for(int i = 0; i< 10 ; i++){
			Holder holder = new Holder();
			BigDecimal iB = BigDecimal.valueOf(i);
			holder.setCalisanSayisi(Long.valueOf(i + 10));
			holder.setEnBuyukHarcama(iB);
			holder.setFinansalYil(new Date());
			holder.setGelir(BigDecimal.valueOf(i + 25));
			holder.setGider(BigDecimal.valueOf(250 -i));
			holder.setSirket("Adiyaman Ticaret");
			holders.add(holder);
		}
		
		return holders;
	}
	
}
