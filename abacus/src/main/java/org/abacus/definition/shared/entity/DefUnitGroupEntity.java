package org.abacus.definition.shared.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.abacus.common.shared.entity.DynamicEntity;
import org.abacus.organization.shared.entity.OrganizationEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "def_unit_group")
@SuppressWarnings("serial")
public class DefUnitGroupEntity extends DynamicEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id", nullable = false)
	private OrganizationEntity organization;
	
	@Column(name = "code", nullable = false)
	private String code;

	@Column(name = "name", nullable = false)
	private String name;

	@OneToMany(mappedBy = "unitGroup", fetch = FetchType.LAZY)
	@Fetch(FetchMode.SELECT)
	private Set<DefUnitCodeEntity> unitCodeList;
	
	public DefUnitGroupEntity(){
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public OrganizationEntity getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationEntity organization) {
		this.organization = organization;
	}

	public Set<DefUnitCodeEntity> getUnitCodeList() {
		return unitCodeList;
	}

	public void setUnitCodeList(Set<DefUnitCodeEntity> unitCodeList) {
		this.unitCodeList = unitCodeList;
	}

}
