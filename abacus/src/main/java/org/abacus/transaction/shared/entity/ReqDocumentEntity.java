package org.abacus.transaction.shared.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.abacus.definition.shared.constant.EnumList;
import org.abacus.organization.shared.entity.DepartmentEntity;

@Entity
@Table(name = "req_document")
@SuppressWarnings("serial")
public class ReqDocumentEntity extends TraDocumentEntity {

	@Enumerated(EnumType.STRING)
	@Column(name = "request_status", nullable = false, length=30)
	private EnumList.RequestStatus requestStatus;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "department_id", nullable = true)
	private DepartmentEntity department;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "department_opp_id", nullable = true)
	private DepartmentEntity departmentOpp;

	public EnumList.RequestStatus getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(EnumList.RequestStatus requestStatus) {
		this.requestStatus = requestStatus;
	}

	public DepartmentEntity getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentEntity department) {
		this.department = department;
	}

	public DepartmentEntity getDepartmentOpp() {
		return departmentOpp;
	}

	public void setDepartmentOpp(DepartmentEntity departmentOpp) {
		this.departmentOpp = departmentOpp;
	}

}
