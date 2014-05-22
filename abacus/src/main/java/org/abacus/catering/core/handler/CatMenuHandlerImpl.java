package org.abacus.catering.core.handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.abacus.catering.core.persistance.DefMenuDao;
import org.abacus.catering.shared.entity.CatMealFilterEntity;
import org.abacus.catering.shared.entity.CatMenuEntity;
import org.abacus.catering.shared.holder.CatMenuSearchCriteria;
import org.abacus.catering.shared.holder.DailyMenuDetail;
import org.abacus.catering.shared.holder.MenuSummary;
import org.joda.time.DateTime;
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

		List<DailyMenuDetail> dailyMenuDetails = this.createBlankDays(searchCriteria);
		sum.setDailyMenuDetails(dailyMenuDetails);
		
		for(CatMenuEntity menu : menuList){
			DailyMenuDetail key = new DailyMenuDetail(menu.getMenuDate());
			int menuDetailIndex = Collections.binarySearch(dailyMenuDetails, key);
			dailyMenuDetails.get(menuDetailIndex).putMenu(menu);
			
		}
		
	
		
		List<CatMealFilterEntity> meals = menuDao.findMealList(searchCriteria);
		sum.setMeals(meals);
		
		return sum;
	}
	
	private List<DailyMenuDetail>  createBlankDays(CatMenuSearchCriteria searchCriteria){
		List<DailyMenuDetail> menuDetails = new ArrayList<>();
		Date startDate = searchCriteria.getStartDate();
		Date endDate = searchCriteria.getEndDate();
		for(Date currDate = startDate; currDate.before(endDate) || currDate.equals(endDate); currDate = new DateTime(currDate).plusDays(1).toDate()){
			menuDetails.add(new DailyMenuDetail(currDate));
		}
		
		Collections.sort(menuDetails);
		return menuDetails;
	}
	
	public static void main(String[] args) {
		System.out.println(TimeZone.getDefault());
	}

}
