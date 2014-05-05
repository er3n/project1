package org.abacus.organization.shared.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.abacus.common.shared.entity.StaticEntity;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.constant.SelectionEnum;

@Entity
@SuppressWarnings("serial")
@Table(name = "org_organization")
public class OrganizationEntity extends StaticEntity {

	@Column(name = "name")
	private String name;

	@Enumerated(EnumType.STRING)
	@Column(name = "level_enum", nullable = false)
	private EnumList.OrgOrganizationLevelEnum level = EnumList.OrgOrganizationLevelEnum.L1;

	@Transient
	private SelectionEnum transientLevel;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private OrganizationEntity parent;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public EnumList.OrgOrganizationLevelEnum getLevel() {
		return level;
	}

	public void setLevel(EnumList.OrgOrganizationLevelEnum level) {
		this.level = level;
	}

	public OrganizationEntity getParent() {
		return parent;
	}

	public void setParent(OrganizationEntity parent) {
		this.parent = parent;
	}

	public SelectionEnum getTransientLevel() {
		return new SelectionEnum(level);
	}

	public void setTransientLevel(SelectionEnum l) {
		this.level = EnumList.OrgOrganizationLevelEnum.valueOf(l.name());
	}

}
