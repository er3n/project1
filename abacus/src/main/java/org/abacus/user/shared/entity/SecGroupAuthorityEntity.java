package org.abacus.user.shared.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.abacus.common.shared.entity.DynamicEntity;

@Entity
@SuppressWarnings("serial")
@Table(name = "sec_group_authority")
public class SecGroupAuthorityEntity extends DynamicEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "group_id", nullable = false)
	private SecGroupEntity group;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "authority_id", nullable = false)
	private SecAuthorityEntity authority;

	public SecGroupEntity getGroup() {
		return group;
	}

	public void setGroup(SecGroupEntity group) {
		this.group = group;
	}

	public SecAuthorityEntity getAuthority() {
		return authority;
	}

	public void setAuthority(SecAuthorityEntity authority) {
		this.authority = authority;
	}

}
