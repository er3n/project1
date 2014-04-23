package org.abacus.user.shared.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.abacus.common.shared.entity.DynamicEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@SuppressWarnings("serial")
@Table(name = "role")
public class RoleEntity extends DynamicEntity {

	@Column(name = "name", nullable = false)
	private String name;

	@OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
	@Fetch(FetchMode.SELECT)
	private List<RoleAuthorityEntity> authorities;
 
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<RoleAuthorityEntity> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<RoleAuthorityEntity> authorities) {
		this.authorities = authorities;
	}

}
