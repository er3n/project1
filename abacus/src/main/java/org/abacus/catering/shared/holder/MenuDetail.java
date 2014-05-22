package org.abacus.catering.shared.holder;

import org.abacus.catering.shared.entity.CatMenuEntity;

public class MenuDetail {

	private CatMenuEntity menu;

	public MenuDetail(CatMenuEntity menu) {
		this.menu = menu;
	}

	public CatMenuEntity getMenu() {
		return menu;
	}

	public void setMenu(CatMenuEntity menu) {
		this.menu = menu;
	}

}
