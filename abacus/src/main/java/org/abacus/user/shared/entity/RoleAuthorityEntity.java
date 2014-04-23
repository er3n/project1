package org.abacus.user.shared.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.abacus.common.shared.entity.DynamicEntity;

@Entity
@SuppressWarnings("serial")
@Table(name = "role_authority")
public class RoleAuthorityEntity extends DynamicEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id")
	private RoleEntity role;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "authority_id")
	private AuthorityEntity authority;

	public RoleEntity getRole() {
		return role;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
	}

	public AuthorityEntity getAuthority() {
		return authority;
	}

	public void setAuthority(AuthorityEntity authority) {
		this.authority = authority;
	}
}
