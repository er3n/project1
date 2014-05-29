package org.abacus.catering.shared.event;

import org.abacus.catering.shared.entity.CatMenuEntity;
import org.abacus.common.shared.event.UpdatedEvent;

public class MenuUpdatedEvent extends UpdatedEvent {

	private CatMenuEntity menu;

	public MenuUpdatedEvent(CatMenuEntity menu) {
		this.menu = menu;
	}

	public CatMenuEntity getMenu() {
		return menu;
	}

	public void setMenu(CatMenuEntity menu) {
		this.menu = menu;
	}

}
