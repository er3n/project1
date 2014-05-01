package org.abacus.user.shared.event;

import java.util.List;

import org.abacus.common.shared.event.ReadEvent;
import org.abacus.user.shared.entity.SecAuthorityEntity;

public class ReadAuthoritiesEvent extends ReadEvent {

	private List<SecAuthorityEntity> groupAuthorities;

	public ReadAuthoritiesEvent(List<SecAuthorityEntity> groupAuthorities) {
		this.groupAuthorities = groupAuthorities;
	}

	public List<SecAuthorityEntity> getGroupAuthorities() {
		return groupAuthorities;
	}

	public void setGroupAuthorities(List<SecAuthorityEntity> groupAuthorities) {
		this.groupAuthorities = groupAuthorities;
	}

}
