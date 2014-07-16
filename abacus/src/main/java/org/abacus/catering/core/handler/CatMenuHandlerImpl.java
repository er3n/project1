
package org.abacus.catering.core.handler;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.abacus.catering.core.persistance.DefMenuDao;
import org.abacus.catering.core.persistance.repository.MealFilterRepository;
import org.abacus.catering.core.persistance.repository.MenuItemRepository;
import org.abacus.catering.core.persistance.repository.MenuRepository;
import org.abacus.catering.core.util.CatMenuItemToMenuMaterialConverter;
import org.abacus.catering.shared.NoMenuItemSelectedException;
import org.abacus.catering.shared.entity.CatMealFilterEntity;
import org.abacus.catering.shared.entity.CatMenuEntity;
import org.abacus.catering.shared.entity.CatMenuItemEntity;
import org.abacus.catering.shared.event.ConfirmMenuEvent;
import org.abacus.catering.shared.event.CreateMenuEvent;
import org.abacus.catering.shared.event.CreateMenuPeriviewEvent;
import org.abacus.catering.shared.event.MenuConfirmedEvent;
import org.abacus.catering.shared.event.MenuCreatedEvent;
import org.abacus.catering.shared.event.MenuPeriviewEvent;
import org.abacus.catering.shared.event.MenuUpdatedEvent;
import org.abacus.catering.shared.event.ReadMenuEvent;
import org.abacus.catering.shared.event.RequestReadMenuEvent;
import org.abacus.catering.shared.event.UpdateMenuEvent;
import org.abacus.catering.shared.holder.CatMenuSearchCriteria;
import org.abacus.catering.shared.holder.DailyMenuDetail;
import org.abacus.catering.shared.holder.MenuMaterialHolder;
import org.abacus.catering.shared.holder.MenuSummary;
import org.abacus.common.shared.AbcBusinessException;
import org.abacus.definition.core.persistance.repository.DefTaskRepository;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.entity.DefTaskEntity;
import org.abacus.organization.shared.entity.DepartmentEntity;
import org.abacus.organization.shared.entity.FiscalYearEntity;
import org.abacus.transaction.core.handler.TraTransactionHandler;
import org.abacus.transaction.shared.entity.StkDetailEntity;
import org.abacus.transaction.shared.entity.StkDocumentEntity;
import org.abacus.transaction.shared.event.CreateDetailEvent;
import org.abacus.transaction.shared.event.CreateDocumentEvent;
import org.abacus.transaction.shared.event.DocumentCreatedEvent;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service(value = "catMenuHandler")
public class CatMenuHandlerImpl implements CatMenuHandler {

	@Autowired
	private DefMenuDao menuDao;

	@Autowired
	private MenuRepository menuRepository;

	@Autowired
	private MenuItemRepository menuItemRepository;

	@Autowired
	private DefTaskRepository taskRepository;

	@Autowired
	private MealFilterRepository mealFilterRepo;

	@Autowired
	private TraTransactionHandler<StkDocumentEntity, StkDetailEntity> stkTransactionHandler;

	@Autowired
	private CatMenuItemToMenuMaterialConverter catMenuItemToMenuMaterialConverter;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public MenuSummary findMenuSummary(CatMenuSearchCriteria searchCriteria) {
		MenuSummary sum = new MenuSummary();
		if (searchCriteria.getFiscalYear()==null){
			return sum;
		}

		List<CatMenuEntity> menuList = menuDao.findMenuList(searchCriteria);
		List<DailyMenuDetail> dailyMenuDetails = this.createBlankDays(searchCriteria);
		sum.setDailyMenuDetails(dailyMenuDetails);

		for (CatMenuEntity menu : menuList) {
			DailyMenuDetail key = new DailyMenuDetail(menu.getMenuDate());
			int menuDetailIndex = Collections.binarySearch(dailyMenuDetails, key);
			dailyMenuDetails.get(menuDetailIndex).putMenu(menu);

		}

		List<CatMealFilterEntity> meals = mealFilterRepo.getMealFilterList(searchCriteria.getFiscalYear().getId());
		sum.setMeals(meals);

		return sum;
	}

	private List<DailyMenuDetail> createBlankDays(CatMenuSearchCriteria searchCriteria) {
		List<DailyMenuDetail> menuDetails = new ArrayList<>();
		Date startDate = searchCriteria.getStartDate();
		Date endDate = searchCriteria.getEndDate();
		for (Date currDate = startDate; currDate.before(endDate) || currDate.equals(endDate); currDate = new DateTime(currDate).plusDays(1).toDate()) {
			menuDetails.add(new DailyMenuDetail(currDate));
		}

		Collections.sort(menuDetails);
		return menuDetails;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public MenuCreatedEvent newMenu(CreateMenuEvent createMenuEvent) {

		CatMenuEntity menu = createMenuEvent.getMenu();
		String userCreated = createMenuEvent.getUsername();
		Set<CatMenuItemEntity> menuItemSet = menu.getMenuItemSet();

		menu.createHook(userCreated);
		menu = menuRepository.save(menu);

		if (!CollectionUtils.isEmpty(menuItemSet)) {

			for (CatMenuItemEntity item : menuItemSet) {
				item.createHook(userCreated);
			}
			menuItemRepository.save(menuItemSet);
		}

		return new MenuCreatedEvent(menu);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public MenuUpdatedEvent updateMenu(UpdateMenuEvent updateMenuEvent) {

		CatMenuEntity menu = updateMenuEvent.getMenu();
		String userUpdated = updateMenuEvent.getUsername();
		Set<CatMenuItemEntity> menuItemSet = menu.getMenuItemSet();

		menu.updateHook(userUpdated);
		menu = menuRepository.save(menu);

		menuItemRepository.delete(menu.getId());
		if (!CollectionUtils.isEmpty(menuItemSet)) {

			for (CatMenuItemEntity item : menuItemSet) {
				item.createHook(userUpdated);
				item.setId(null);
				item.setVersion(null);
			}
			menuItemRepository.save(menuItemSet);
		}

		return new MenuUpdatedEvent(menu);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public ReadMenuEvent findMenu(RequestReadMenuEvent requestReadMenuEvent) {
		if (requestReadMenuEvent.getMenuId() != null) {
			CatMenuEntity menu = menuRepository.findOne(requestReadMenuEvent.getMenuId());
			return new ReadMenuEvent(menu);
		}
		return null;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public MenuPeriviewEvent createMenuPreview(CreateMenuPeriviewEvent createEvent) {

		CatMenuEntity menu = createEvent.getMenu();
		
		Set<CatMenuItemEntity> menuItemSet = menu.getMenuItemSet();
		DepartmentEntity department = createEvent.getDepartmentEntity();
		
		FiscalYearEntity fiscalYear = createEvent.getFiscalYear();

		if (CollectionUtils.isEmpty(menuItemSet)) {
			throw new NoMenuItemSelectedException();
		}

		StkDocumentEntity document = new StkDocumentEntity();
		DefTaskEntity inputTask = taskRepository.getTaskRepo(fiscalYear.getOrganization().getRootOrganization().getId(), EnumList.DefTypeEnum.STK_IO_O.name());

		document.setDocDate(Calendar.getInstance().getTime());
		document.setTask(inputTask);
		document.setDocNo("M_" + menu.getId());

		Collection<MenuMaterialHolder> menuMaterialSet = catMenuItemToMenuMaterialConverter.convert(menu, menuItemSet);
		
		List<StkDetailEntity> details = new ArrayList<StkDetailEntity>();
		for (MenuMaterialHolder material : menuMaterialSet) {

			StkDetailEntity detail = new StkDetailEntity();

			detail.setBatchDetailNo("MD_" + menu.getId());
			detail.setDepartment(department);
			detail.setDocument(document);
			detail.setItem(material.getItem());
			detail.setItemUnit(material.getUnit());
			detail.setItemDetailCount(material.getCountSpend());
			detail.setBaseDetailAmount(BigDecimal.ZERO);
			detail.setDueDate(Calendar.getInstance().getTime());
			
			details.add(detail);

		}

		return new MenuPeriviewEvent(document,details);
	}
	

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public MenuConfirmedEvent confirmMenu(ConfirmMenuEvent confirmMenuEvent) throws AbcBusinessException {

		StkDocumentEntity document = confirmMenuEvent.getDocument();
		List<StkDetailEntity> details = confirmMenuEvent.getDetails();
		String user = confirmMenuEvent.getUser();
		CatMenuEntity menu = confirmMenuEvent.getMenu();
		FiscalYearEntity fiscalYear = confirmMenuEvent.getFiscalYear();
		
		DocumentCreatedEvent<StkDocumentEntity> documentCreatedEvent = stkTransactionHandler.newDocument(new CreateDocumentEvent<StkDocumentEntity>(document, user, fiscalYear.getOrganization(), fiscalYear));
		document = documentCreatedEvent.getDocument();

		for (StkDetailEntity detail : details) {
			detail.setDocument(document);
			stkTransactionHandler.newDetail(new CreateDetailEvent<StkDetailEntity>(detail, user));
		}

		menu.setMenuStatus(EnumList.MenuStatusEnum.DONE);
		menu.setDocument((StkDocumentEntity) document);
		MenuUpdatedEvent mue = this.updateMenu(new UpdateMenuEvent(menu, user));
		menu = mue.getMenu();

		return new MenuConfirmedEvent(document);
	}

	public DefMenuDao getMenuDao() {
		return menuDao;
	}

	public void setMenuDao(DefMenuDao menuDao) {
		this.menuDao = menuDao;
	}

	public MenuRepository getMenuRepository() {
		return menuRepository;
	}

	public void setMenuRepository(MenuRepository menuRepository) {
		this.menuRepository = menuRepository;
	}

	public MenuItemRepository getMenuItemRepository() {
		return menuItemRepository;
	}

	public void setMenuItemRepository(MenuItemRepository menuItemRepository) {
		this.menuItemRepository = menuItemRepository;
	}

	public DefTaskRepository getTaskRepository() {
		return taskRepository;
	}

	public void setTaskRepository(DefTaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	public TraTransactionHandler<StkDocumentEntity, StkDetailEntity> getStkTransactionHandler() {
		return stkTransactionHandler;
	}

	public void setStkTransactionHandler(TraTransactionHandler<StkDocumentEntity, StkDetailEntity> stkTransactionHandler) {
		this.stkTransactionHandler = stkTransactionHandler;
	}

}
