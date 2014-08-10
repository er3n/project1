package org.abacus.catering.core.handler;

import java.util.Date;
import java.util.List;

import org.abacus.catering.shared.entity.CatMenuEntity;
import org.abacus.catering.shared.entity.CatMenuInfoEntity;
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
import org.abacus.catering.shared.holder.MenuSummary;
import org.abacus.common.shared.AbcBusinessException;
import org.abacus.organization.shared.entity.FiscalYearEntity;

public interface CatMenuHandler {

	MenuSummary findMenuSummary(CatMenuSearchCriteria searchCriteria);

	MenuCreatedEvent newMenu(CreateMenuEvent createMenuEvent);

	MenuUpdatedEvent updateMenu(UpdateMenuEvent updateMenuEvent);

	ReadMenuEvent findMenu(RequestReadMenuEvent requestReadMenuEvent);
	
	MenuConfirmedEvent confirmMenu(ConfirmMenuEvent confirmMenuEvent) throws AbcBusinessException;

	MenuPeriviewEvent createMenuPreview(CreateMenuPeriviewEvent createEvent);
	
	List<CatMenuInfoEntity> getMenuInfoList(FiscalYearEntity fiscalYear);

	CatMenuInfoEntity saveMenuInfoEntity(CatMenuInfoEntity entity);
	
	void deleteMenuInfoEntity(CatMenuInfoEntity entity);

	List<CatMenuEntity> getMenuListForFinace(String fiscalYearId, Date menuDate);
	
}
