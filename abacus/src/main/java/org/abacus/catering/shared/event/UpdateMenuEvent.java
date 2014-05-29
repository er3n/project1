package org.abacus.catering.shared.event;

import org.abacus.catering.shared.entity.CatMenuEntity;
import org.abacus.common.shared.event.UpdateEvent;

public class UpdateMenuEvent extends UpdateEvent {

	private CatMenuEntity menu;
	private String username;

	public UpdateMenuEvent(CatMenuEntity menu, String username) {
		this.menu = menu;
		this.username = username;
	}

	public CatMenuEntity getMenu() {
		return menu;
	}

	public void setMenu(CatMenuEntity menu) {
		this.menu = menu;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
