package org.abacus.definition.shared.event;

import java.util.Set;

import org.abacus.common.shared.event.CreatedEvent;
import org.abacus.definition.shared.entity.DefItemEntity;
import org.abacus.definition.shared.entity.DefUnitCodeEntity;

public class CreateItemEvent extends CreatedEvent {

	private DefItemEntity item;
	private String createdUser;
	private Set<DefUnitCodeEntity> unitCodeSet;

	public CreateItemEvent(DefItemEntity item, Set<DefUnitCodeEntity> unitCodeSet, String createdUser) {
		this.item = item;
		this.createdUser = createdUser;
		this.unitCodeSet = unitCodeSet;
	}

	public DefItemEntity getItem() {
		return item;
	}

	public void setItem(DefItemEntity item) {
		this.item = item;
	}

	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public Set<DefUnitCodeEntity> getUnitCodeSet() {
		return unitCodeSet;
	}

	public void setUnitCodeSet(Set<DefUnitCodeEntity> unitCodeSet) {
		this.unitCodeSet = unitCodeSet;
	}

}
