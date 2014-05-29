package org.abacus.catering.core.handler;

import org.abacus.catering.shared.event.CreateMenuEvent;
import org.abacus.catering.shared.event.MenuCreatedEvent;
import org.abacus.catering.shared.event.MenuUpdatedEvent;
import org.abacus.catering.shared.event.ReadMenuEvent;
import org.abacus.catering.shared.event.RequestReadMenuEvent;
import org.abacus.catering.shared.event.UpdateMenuEvent;
import org.abacus.catering.shared.holder.CatMenuSearchCriteria;
import org.abacus.catering.shared.holder.MenuSummary;

public interface CatMenuHandler {

	public MenuSummary findMenuSummary(CatMenuSearchCriteria searchCriteria);

	public MenuCreatedEvent newMenu(CreateMenuEvent createMenuEvent);

	MenuUpdatedEvent updateMenu(UpdateMenuEvent updateMenuEvent);

	public ReadMenuEvent findMenu(RequestReadMenuEvent requestReadMenuEvent);
	
}
