package org.abacus.definition.shared.event;

import java.util.Set;

import org.abacus.common.shared.event.UpdatedEvent;
import org.abacus.definition.shared.entity.DefItemEntity;
import org.abacus.definition.shared.entity.DefUnitCodeEntity;
import org.abacus.organization.shared.entity.OrganizationEntity;

public class UpdateItemEvent extends UpdatedEvent {

	private DefItemEntity item;
	private String userUpdated;
	private OrganizationEntity organization;
	private Set<DefUnitCodeEntity> unitCodeSet;

	public UpdateItemEvent(DefItemEntity item, String userUpdated, OrganizationEntity organization) {
		this.item = item;
		this.userUpdated = userUpdated;
		this.organization = organization;
	}

	public UpdateItemEvent(DefItemEntity item, Set<DefUnitCodeEntity> unitCodeSet, String userUpdated, OrganizationEntity organization) {
		this.item = item;
		this.unitCodeSet = unitCodeSet;
		this.userUpdated = userUpdated;
		this.organization = organization;
	}

	public DefItemEntity getItem() {
		return item;
	}

	public void setItem(DefItemEntity item) {
		this.item = item;
	}

	public String getUserUpdated() {
		return userUpdated;
	}

	public void setUserUpdated(String userUpdated) {
		this.userUpdated = userUpdated;
	}

	public OrganizationEntity getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationEntity organization) {
		this.organization = organization;
	}

	public Set<DefUnitCodeEntity> getUnitCodeSet() {
		return unitCodeSet;
	}

	public void setUnitCodeSet(Set<DefUnitCodeEntity> unitCodeSet) {
		this.unitCodeSet = unitCodeSet;
	}

}
