package org.abacus.user.shared.event;

import java.util.List;

import org.abacus.common.shared.event.ReadEvent;
import org.abacus.user.shared.entity.SecGroupEntity;

public class ReadGroupsEvent extends ReadEvent {

	private List<SecGroupEntity> groupList;

	public ReadGroupsEvent(List<SecGroupEntity> groupList) {
		this.groupList = groupList;
	}

	public List<SecGroupEntity> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<SecGroupEntity> groupList) {
		this.groupList = groupList;
	}

}
