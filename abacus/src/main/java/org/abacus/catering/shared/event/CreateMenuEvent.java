package org.abacus.catering.shared.event;

import org.abacus.catering.shared.entity.CatMenuEntity;
import org.abacus.common.shared.event.CreatedEvent;

public class CreateMenuEvent extends CreatedEvent {

	private String username;
	private CatMenuEntity menu;

	public CreateMenuEvent(CatMenuEntity menu, String username) {
		this.menu = menu;
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public CatMenuEntity getMenu() {
		return menu;
	}

	public void setMenu(CatMenuEntity menu) {
		this.menu = menu;
	}

}
