package org.abacus.catering.shared.event;

import org.abacus.catering.shared.entity.CatMenuEntity;
import org.abacus.common.shared.event.ReadEvent;

public class ReadMenuEvent extends ReadEvent {

	private CatMenuEntity menu;

	public ReadMenuEvent(CatMenuEntity menu) {
		this.menu = menu;
	}

	public CatMenuEntity getMenu() {
		return menu;
	}

	public void setMenu(CatMenuEntity menu) {
		this.menu = menu;
	}

}
