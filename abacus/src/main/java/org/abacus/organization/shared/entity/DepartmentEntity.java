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
	@JoinColumn(name = "company_id")
	private CompanyEntity company;

	@Column(name = "code", nullable = false)
	private String code;

	@Column(name = "name", nullable = false)
	private String name;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "group_enum", nullable = false)
	private EnumList.OrgDepartmentGroupEnum group; 

	@Transient
	private SelectionEnum transientGroup; 

	public CompanyEntity getCompany() {
		return company;
	}

	public void setCompany(CompanyEntity company) {
		this.company = company;
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

	public EnumList.OrgDepartmentGroupEnum getGroup() {
		return group;
	}

	public void setGroup(EnumList.OrgDepartmentGroupEnum group) {
		this.group = group;
	}

	public SelectionEnum getTransientGroup() {
		return new SelectionEnum(group);
	}

	public void setTransientGroup(SelectionEnum g) {
		this.group = EnumList.OrgDepartmentGroupEnum.valueOf(g.name());
	}

	
}
