package org.abacus.user.shared.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.abacus.common.shared.entity.DynamicEntity;
import org.abacus.organization.shared.entity.DepartmentEntity;
import org.hibernate.annotations.Type;

@Entity
@SuppressWarnings("serial")
@Table(name = "sec_user_department")
public class SecUserDepartmentEntity extends DynamicEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private SecUserEntity user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department_id", nullable = false)
	private DepartmentEntity department;

	@Column(name = "auth_input", nullable = false)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean authInput = false;
	
	@Column(name = "auth_output", nullable = false)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean authOutput = false;
	

	public SecUserEntity getUser() {
		return user;
	}

	public void setUser(SecUserEntity user) {
		this.user = user;
	}

	public DepartmentEntity getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentEntity department) {
		this.department = department;
	}

	public Boolean getAuthInput() {
		return authInput;
	}

	public void setAuthInput(Boolean authInput) {
		this.authInput = authInput;
	}

	public Boolean getAuthOutput() {
		return authOutput;
	}

	public void setAuthOutput(Boolean authOutput) {
		this.authOutput = authOutput;
	}

}
