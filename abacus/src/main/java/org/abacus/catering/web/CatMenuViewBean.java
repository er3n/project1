package org.abacus.catering.web;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.persistence.EnumType;

import org.abacus.catering.core.handler.CatMenuHandler;
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
import org.abacus.common.web.JsfDialogHelper;
import org.abacus.common.web.JsfMessageHelper;
import org.abacus.common.web.SessionInfoHelper;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.entity.DefItemEntity;
import org.abacus.definition.shared.holder.ItemSearchCriteria;
import org.abacus.definition.web.model.ItemDataModel;
import org.abacus.organization.shared.entity.OrganizationEntity;
import org.joda.time.MutableDateTime;
import org.springframework.util.CollectionUtils;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CatMenuViewBean implements Serializable {

	@ManagedProperty(value = "#{jsfMessageHelper}")
	private JsfMessageHelper jsfMessageHelper;

	@ManagedProperty(value = "#{sessionInfoHelper}")
	private SessionInfoHelper sessionInfoHelper;

	@ManagedProperty(value = "#{catMenuHandler}")
	private CatMenuHandler menuHandler;

	@ManagedProperty(value = "#{jsfDialogHelper}")
	private JsfDialogHelper jsfDialogHelper;

	private CatMenuSearchCriteria searchCriteria;

	private MenuSummary menuSummary;

	private List<CatMealFilterEntity> meals;

	private CatMenuEntity selectedMenu;

	private DefItemEntity selectedItem;

	private ItemDataModel menuItems;

	@PostConstruct
	private void init() {
		this.searchCriteria = new CatMenuSearchCriteria();
		this.searchCriteria.setPeriod(EnumList.CatMenuPeriod.WEEKLY);
		searchCriteria.setDate(Calendar.getInstance().getTime());
		this.initMenuSummary();

		menuItems = new ItemDataModel(new ItemSearchCriteria(sessionInfoHelper.currentOrganizationId(), EnumList.DefTypeEnum.ITM_SR_ST, EnumList.DefItemClassEnum.STK_P));;
	}
	
	public void initCancelMenu(CatMealFilterEntity mealFilterEntity, DailyMenuDetail dailyMenu){
		this.initUpdateMenu(mealFilterEntity, dailyMenu);
	}
	
	public void cancelMenu(){
		this.selectedMenu.setMenuStatus(EnumList.MenuStatusEnum.CANCEL);
		this.updateMenu();
	}
	
	public void nextMenu(){
		Date selectedDate = searchCriteria.getDate();
		MutableDateTime mdt = new MutableDateTime(selectedDate);
		
		
		if(searchCriteria.getPeriod().equals(EnumList.CatMenuPeriod.MOUNTHLY)){
			mdt.addMonths(1);
		}else{
			mdt.addWeeks(1);
		}
			
		selectedDate =  mdt.toDate();
		searchCriteria.setDate(selectedDate);
		
		this.menuDateSelected();
	}
	
	public void prevMenu(){
		Date selectedDate = searchCriteria.getDate();
		MutableDateTime mdt = new MutableDateTime(selectedDate);
		
		
		if(searchCriteria.getPeriod().equals(EnumList.CatMenuPeriod.MOUNTHLY)){
			mdt.addMonths(-1);
		}else{
			mdt.addWeeks(-1);
		}
			
		selectedDate =  mdt.toDate();
		searchCriteria.setDate(selectedDate);
		
		this.menuDateSelected();
	}

	public void addItemToMenu() {

		if (selectedItem == null) {
			return;
		}

		Set<CatMenuItemEntity> menuItemSet = selectedMenu.getMenuItemSet();

		CatMenuItemEntity menuItem = new CatMenuItemEntity();
		menuItem.setMenu(selectedMenu);
		menuItem.setItem(selectedItem);

		if (CollectionUtils.isEmpty(menuItemSet)) {
			menuItemSet = new HashSet<>();
			selectedMenu.setMenuItemSet(menuItemSet);
		}

		boolean isItemExistsInMenu = !this.validateAddItemToMenu(menuItemSet);
		if (!isItemExistsInMenu) {
			menuItemSet.add(menuItem);
		} else {
			jsfMessageHelper.addError("itemExistsInMenu");
		}

	}

	private boolean validateAddItemToMenu(Set<CatMenuItemEntity> menuItemSet) {

		for (CatMenuItemEntity item : menuItemSet) {
			if (item.getItem().getId().equals(selectedItem.getId())) {
				return false;
			}
		}

		return true;
	}

	public void initMenuSummary() {
		this.menuSummary = menuHandler.findMenuSummary(this.searchCriteria);
		this.meals = menuSummary.getMeals();
	}

	public void initCreateMenu(CatMealFilterEntity mealFilterEntity, DailyMenuDetail dailyMenu) {
		OrganizationEntity organization = sessionInfoHelper.currentOrganization();
		Integer expectedCountPrepare = mealFilterEntity.getCountPrepare();

		selectedMenu = new CatMenuEntity();
		selectedMenu.setOrganization(organization);
		selectedMenu.setCountPrepare(expectedCountPrepare);
		selectedMenu.setMeal(mealFilterEntity.getMeal());
		selectedMenu.setMenuDate(dailyMenu.getDate());
		selectedMenu.setMenuStatus(EnumList.MenuStatusEnum.WAIT);
		selectedMenu.setMenuItemSet(new HashSet<CatMenuItemEntity>());
	}

	public void initUpdateMenu(CatMealFilterEntity mealFilterEntity, DailyMenuDetail dailyMenu) {
		CatMenuEntity menu = dailyMenu.getMenuMap().get(mealFilterEntity.getMeal().getCode());
		ReadMenuEvent readMenuEvent = menuHandler.findMenu(new RequestReadMenuEvent(menu.getId()));
		this.selectedMenu = readMenuEvent.getMenu();
	}

	public void removeMenuItemFromMenu(CatMenuItemEntity item) {
		this.selectedMenu.getMenuItemSet().remove(item);
	}

	public boolean isMealActiveAtGivenDate(CatMealFilterEntity filter, Date date) {
		if (filter.getDateStart().before(date) && filter.getDateFinish().after(date)) {
			return true;
		}
		return false;
	}

	public void menuDateSelected() {
		searchCriteria.refreshDate();
		this.initMenuSummary();
	}

	public void saveMenu() {
		String username = sessionInfoHelper.currentUserName();
		MenuCreatedEvent menuCreatedEvent = menuHandler.newMenu(new CreateMenuEvent(this.selectedMenu, username));
		this.selectedMenu = menuCreatedEvent.getMenu();
		this.initMenuSummary();
		jsfMessageHelper.addInfo("craeteSuccessful");
	}

	public void updateMenu() {
		String username = sessionInfoHelper.currentUserName();
		MenuUpdatedEvent updateMenuEvent = menuHandler.updateMenu(new UpdateMenuEvent(this.selectedMenu, username));
		this.selectedMenu = updateMenuEvent.getMenu();
		this.initMenuSummary();
		jsfMessageHelper.addInfo("updateSuccesssful");
	}

	public JsfMessageHelper getJsfMessageHelper() {
		return jsfMessageHelper;
	}

	public void setJsfMessageHelper(JsfMessageHelper jsfMessageHelper) {
		this.jsfMessageHelper = jsfMessageHelper;
	}

	public SessionInfoHelper getSessionInfoHelper() {
		return sessionInfoHelper;
	}

	public void setSessionInfoHelper(SessionInfoHelper sessionInfoHelper) {
		this.sessionInfoHelper = sessionInfoHelper;
	}

	public CatMenuSearchCriteria getSearchCriteria() {
		return searchCriteria;
	}

	public void setSearchCriteria(CatMenuSearchCriteria searchCriteria) {
		this.searchCriteria = searchCriteria;
	}

	public CatMenuHandler getMenuHandler() {
		return menuHandler;
	}

	public void setMenuHandler(CatMenuHandler menuHandler) {
		this.menuHandler = menuHandler;
	}

	public MenuSummary getMenuSummary() {
		return menuSummary;
	}

	public void setMenuSummary(MenuSummary menuSummary) {
		this.menuSummary = menuSummary;
	}

	public List<CatMealFilterEntity> getMeals() {
		return meals;
	}

	public void setMeals(List<CatMealFilterEntity> meals) {
		this.meals = meals;
	}

	public JsfDialogHelper getJsfDialogHelper() {
		return jsfDialogHelper;
	}

	public void setJsfDialogHelper(JsfDialogHelper jsfDialogHelper) {
		this.jsfDialogHelper = jsfDialogHelper;
	}

	public CatMenuEntity getSelectedMenu() {
		return selectedMenu;
	}

	public void setSelectedMenu(CatMenuEntity selectedMenu) {
		this.selectedMenu = selectedMenu;
	}

	public DefItemEntity getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(DefItemEntity selectedItem) {
		this.selectedItem = selectedItem;
	}

	public ItemDataModel getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(ItemDataModel menuItems) {
		this.menuItems = menuItems;
	}
	
	

}