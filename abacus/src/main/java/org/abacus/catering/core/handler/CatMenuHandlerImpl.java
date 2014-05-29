package org.abacus.catering.core.handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.abacus.catering.core.persistance.DefMenuDao;
import org.abacus.catering.core.persistance.repository.MenuItemRepository;
import org.abacus.catering.core.persistance.repository.MenuRepository;
import org.abacus.catering.shared.entity.CatMealFilterEntity;
import org.abacus.catering.shared.entity.CatMenuEntity;
import org.abacus.catering.shared.entity.CatMenuItemEntity;
import org.abacus.catering.shared.event.CreateMenuEvent;
import org.abacus.catering.shared.event.MenuCreatedEvent;
import org.abacus.catering.shared.event.MenuUpdatedEvent;
import org.abacus.catering.shared.event.ReadMenuEvent;
import org.abacus.catering.shared.event.RequestReadMenuEvent;
import org.abacus.catering.shared.event.UpdateMenuEvent;
import org.abacus.catering.shared.holder.CatMenuSearchCriteria;
import org.abacus.catering.shared.holder.DailyMenuDetail;
import org.abacus.catering.shared.holder.MenuSummary;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service(value="catMenuHandler")
public class CatMenuHandlerImpl implements CatMenuHandler {

	@Autowired
	private DefMenuDao menuDao;
	
	@Autowired
	private MenuRepository menuRepository;
	
	@Autowired
	private MenuItemRepository menuItemRepository;
	
	@Override
	@Transactional(readOnly=true,propagation=Propagation.SUPPORTS)
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
	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public MenuCreatedEvent newMenu(CreateMenuEvent createMenuEvent) {
		
		CatMenuEntity menu = createMenuEvent.getMenu();
		String userCreated = createMenuEvent.getUsername();
		Set<CatMenuItemEntity> menuItemSet = menu.getMenuItemSet();
		
		
		menu.createHook(userCreated);
		menu = menuRepository.save(menu);
		
		if(!CollectionUtils.isEmpty(menuItemSet)){
			
			for(CatMenuItemEntity item : menuItemSet){
				item.createHook(userCreated);
			}
			menuItemRepository.save(menuItemSet);
		}
		
		
		return new MenuCreatedEvent(menu);
	}
	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public MenuUpdatedEvent updateMenu(UpdateMenuEvent updateMenuEvent) {
		
		CatMenuEntity menu = updateMenuEvent.getMenu();
		String userUpdated = updateMenuEvent.getUsername();
		Set<CatMenuItemEntity> menuItemSet = menu.getMenuItemSet();
		
		menu.updateHook(userUpdated);
		menu = menuRepository.save(menu);
		
		menuItemRepository.delete(menu.getId());
		if(!CollectionUtils.isEmpty(menuItemSet)){
			
			for(CatMenuItemEntity item : menuItemSet){
				item.createHook(userUpdated);
				item.setId(null);
				item.setVersion(null);
			}
			menuItemRepository.save(menuItemSet);
		}
				
		return new MenuUpdatedEvent(menu);
	}

	@Override
	@Transactional(readOnly=true,propagation=Propagation.SUPPORTS)
	public ReadMenuEvent findMenu(RequestReadMenuEvent requestReadMenuEvent) {
		if(requestReadMenuEvent.getMenuId() != null){
			CatMenuEntity menu = menuRepository.findOne(requestReadMenuEvent.getMenuId());
			return new ReadMenuEvent(menu);
		}
		return null;
	}

}
