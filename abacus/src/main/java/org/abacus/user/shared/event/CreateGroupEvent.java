package org.abacus.user.shared.event;

import java.util.List;

import org.abacus.common.shared.event.CreatedEvent;
import org.abacus.user.shared.entity.SecAuthorityEntity;
import org.abacus.user.shared.entity.SecGroupEntity;

public class CreateGroupEvent extends CreatedEvent {

	private SecGroupEntity group;
	private List<SecAuthorityEntity> authorities;
	private String userCreated;

	public CreateGroupEvent(SecGroupEntity group,
			List<SecAuthorityEntity> authorities, String userCreated) {
		this.group = group;
		this.authorities = authorities;
		this.userCreated = userCreated;
	}

	public SecGroupEntity getGroup() {
		return group;
	}

	public void setGroup(SecGroupEntity group) {
		this.group = group;
	}

	public List<SecAuthorityEntity> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<SecAuthorityEntity> authorities) {
		this.authorities = authorities;
	}

	public String getUserCreated() {
		return userCreated;
	}

	public void setUserCreated(String userCreated) {
		this.userCreated = userCreated;
	}

}
