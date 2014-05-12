package org.abacus.user.shared.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.abacus.common.shared.entity.DynamicEntity;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.organization.shared.entity.DepartmentEntity;
import org.abacus.organization.shared.entity.OrganizationEntity;

@Entity
@SuppressWarnings("serial")
@Table(name = "sec_user_department")
public class SecUserDepartmentEntity extends DynamicEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private SecUserEntity user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id", nullable = false)
	private OrganizationEntity organization;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department_id", nullable = false)
	private DepartmentEntity department;

	@Enumerated(EnumType.STRING)
	@Column(name = "department_auth", nullable = false)
	private EnumList.DepartmentAuthEnum departmentAuth;

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

	public DepartmentEntity getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentEntity department) {
		this.department = department;
	}

	public EnumList.DepartmentAuthEnum getDepartmentAuth() {
		return departmentAuth;
	}

	public void setDepartmentAuth(EnumList.DepartmentAuthEnum departmentAuth) {
		this.departmentAuth = departmentAuth;
	}
}
