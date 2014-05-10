package org.abacus.definition.shared.event;

import java.util.List;

import org.abacus.definition.shared.entity.DefItemEntity;

public class ReadItemEvent {

	private List<DefItemEntity> itemList;
	private DefItemEntity item;
	private Integer totalCount;

	public ReadItemEvent(List<DefItemEntity> itemList, Integer totalCount) {
		this.itemList = itemList;
		this.totalCount = totalCount;
	}

	public ReadItemEvent(DefItemEntity item) {
		this.item = item;
	}

	public List<DefItemEntity> getItemList() {
		return itemList;
	}

	public void setItemList(List<DefItemEntity> itemList) {
		this.itemList = itemList;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public DefItemEntity getItem() {
		return item;
	}

	public void setItem(DefItemEntity item) {
		this.item = item;
	}

}
