package org.abacus.definition.shared.event;

import java.util.List;

import org.abacus.definition.shared.entity.DefItemEntity;

public class ReadItemEvent {

	private List<DefItemEntity> itemList;

	public List<DefItemEntity> getItemList() {
		return itemList;
	}

	public void setItemList(List<DefItemEntity> itemList) {
		this.itemList = itemList;
	}

}
