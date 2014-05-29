package org.abacus.catering.shared.event;

import org.abacus.common.shared.event.RequestReadEvent;

public class RequestReadMenuEvent extends RequestReadEvent {

	private Long menuId;
	
	public RequestReadMenuEvent(Long menuId){
		this.menuId = menuId;
	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
	
	
	
}
