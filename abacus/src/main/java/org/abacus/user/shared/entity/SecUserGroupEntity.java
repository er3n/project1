package org.abacus.user.shared.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.abacus.common.shared.entity.DynamicEntity;

@Entity
@SuppressWarnings("serial")
@Table(name = "sec_user_group")
public class SecUserGroupEntity extends DynamicEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private SecUserEntity user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "group_id", nullable = false)
	private SecGroupEntity group;

	public SecUserEntity getUser() {
		return user;
	}

	public void setUser(SecUserEntity user) {
		this.user = user;
	}

	public SecGroupEntity getGroup() {
		return group;
	}

	public void setGroup(SecGroupEntity group) {
		this.group = group;
	}

	

}
