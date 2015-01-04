package org.abacus.user.shared.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.abacus.common.shared.entity.DynamicEntity;
import org.abacus.organization.shared.entity.OrganizationEntity;

@Entity
@SuppressWarnings("serial")
@Table(name = "sec_user_organization")
public class SecUserOrganizationEntity extends DynamicEntity implements Comparable<SecUserOrganizationEntity> {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private SecUserEntity user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id", nullable = false)
	private OrganizationEntity organization;

	@Override
	public int compareTo(SecUserOrganizationEntity o) {
		return this.organization.getId().compareTo(o.organization.getId());
	}
	
	public SecUserEntity getUser() {
		return user;
	}

	public void setUser(SecUserEntity user) {
		this.user = user;
	}

	public OrganizationEntity getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationEntity organization) {
		this.organization = organization;
	}

}
