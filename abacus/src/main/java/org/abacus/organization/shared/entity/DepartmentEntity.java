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

import org.abacus.common.shared.entity.DynamicEntity;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.constant.SelectionEnum;

@Entity
@SuppressWarnings("serial")
@Table(name = "org_department")
public class DepartmentEntity extends DynamicEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id", nullable = false)
	private OrganizationEntity organization;

	@Column(name = "code", nullable = false)
	private String code;

	@Column(name = "name", nullable = false)
	private String name;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "group_enum", nullable = false)
	private EnumList.OrgDepartmentGroupEnum departmentGroup; 

	@Transient
	private SelectionEnum transientGroup; 

	public OrganizationEntity getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationEntity organization) {
		this.organization = organization;
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

	public EnumList.OrgDepartmentGroupEnum getDepartmentGroup() {
		return departmentGroup;
	}

	public void setDepartmentGroup(EnumList.OrgDepartmentGroupEnum group) {
		this.departmentGroup = group;
	}

	public SelectionEnum getTransientGroup() {
		return new SelectionEnum(departmentGroup);
	}

	public void setTransientGroup(SelectionEnum g) {
		this.departmentGroup = EnumList.OrgDepartmentGroupEnum.valueOf(g.name());
	}

	
}
