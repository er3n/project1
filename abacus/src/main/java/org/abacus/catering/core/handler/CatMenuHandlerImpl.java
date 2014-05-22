package org.abacus.catering.core.handler;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.abacus.catering.core.persistance.DefMenuDao;
import org.abacus.catering.shared.entity.CatMealFilterEntity;
import org.abacus.catering.shared.entity.CatMenuEntity;
import org.abacus.catering.shared.holder.CatMenuSearchCriteria;
import org.abacus.catering.shared.holder.MenuDetail;
import org.abacus.catering.shared.holder.MenuSummary;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value="catMenuHandler")
public class CatMenuHandlerImpl implements CatMenuHandler {

	@Autowired
	private DefMenuDao menuDao;

	@Override
	@Transactional(readOnly=false,propagation=Propagation.SUPPORTS)
	public MenuSummary findMenuSummary(CatMenuSearchCriteria searchCriteria) {	
		List<CatMenuEntity> menuList = menuDao.findMenuList(searchCriteria);
		
		MenuSummary sum = new MenuSummary();
		Map<String, MenuDetail> menuDetailMap = new HashMap<String,MenuDetail>();
		sum.setMenuDetailMap(menuDetailMap);
		for(CatMenuEntity menuEntity : menuList){
			String key = String.valueOf(menuEntity.getMenuDate().getTime());
			MenuDetail value = new MenuDetail(menuEntity);
			menuDetailMap.put(key, value);
		}
		
		List<CatMealFilterEntity> meals = menuDao.findMealList(searchCriteria);
		sum.setMeals(meals);
		
		return sum;
	}
	
	public static void main(String[] args) {
		LocalDate date = new LocalDate(new Date());
		LocalDate weekStart = date.dayOfWeek().withMinimumValue();
		LocalDate weekEnd = date.dayOfWeek().withMaximumValue();

		System.out.println(weekStart);
		System.out.println(weekEnd);
	}



}
