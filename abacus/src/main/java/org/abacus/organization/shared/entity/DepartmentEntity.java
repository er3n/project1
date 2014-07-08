package org.abacus.organization.shared.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.abacus.common.shared.entity.DynamicEntity;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.user.shared.entity.SecUserDepartmentEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
	@Column(name = "group_enum", nullable = false, length=30)
	private EnumList.OrgDepartmentGroupEnum departmentGroup; 

	@OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
	@Fetch(FetchMode.SELECT)
	private Set<SecUserDepartmentEntity> departmentUserList;
	
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

	public Set<SecUserDepartmentEntity> getDepartmentUserList() {
		return departmentUserList;
	}

	public void setDepartmentUserList(
			Set<SecUserDepartmentEntity> departmentUserList) {
		this.departmentUserList = departmentUserList;
	}

}
