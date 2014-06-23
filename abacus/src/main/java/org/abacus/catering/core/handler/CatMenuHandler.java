package org.abacus.catering.core.handler;

import org.abacus.catering.shared.event.ConfirmMenuEvent;
import org.abacus.catering.shared.event.CreateMenuEvent;
import org.abacus.catering.shared.event.MenuConfirmedEvent;
import org.abacus.catering.shared.event.MenuCreatedEvent;
import org.abacus.catering.shared.event.MenuUpdatedEvent;
import org.abacus.catering.shared.event.ReadMenuEvent;
import org.abacus.catering.shared.event.RequestReadMenuEvent;
import org.abacus.catering.shared.event.UpdateMenuEvent;
import org.abacus.catering.shared.holder.CatMenuSearchCriteria;
import org.abacus.catering.shared.holder.MenuSummary;
import org.abacus.common.shared.AbcBusinessException;

public interface CatMenuHandler {

	MenuSummary findMenuSummary(CatMenuSearchCriteria searchCriteria);

	MenuCreatedEvent newMenu(CreateMenuEvent createMenuEvent);

	MenuUpdatedEvent updateMenu(UpdateMenuEvent updateMenuEvent);

	ReadMenuEvent findMenu(RequestReadMenuEvent requestReadMenuEvent);
	
	MenuConfirmedEvent confirmMenu(ConfirmMenuEvent confirmMenuEvent) throws AbcBusinessException;
	
}
