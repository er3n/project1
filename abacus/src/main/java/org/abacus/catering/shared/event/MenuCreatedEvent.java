package org.abacus.catering.shared.event;

import org.abacus.catering.shared.entity.CatMenuEntity;
import org.abacus.common.shared.event.CreatedEvent;

public class MenuCreatedEvent extends CreatedEvent {

	private CatMenuEntity menu;

	public MenuCreatedEvent(CatMenuEntity menu) {
		this.menu = menu;
	}

	public CatMenuEntity getMenu() {
		return menu;
	}

	public void setMenu(CatMenuEntity menu) {
		this.menu = menu;
	}

}
