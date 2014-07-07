package org.abacus.transaction.shared.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.abacus.organization.shared.entity.DepartmentEntity;

@Entity
@Table(name = "tra_detail")
@SuppressWarnings("serial")
public class ReqDetailEntity extends TraDetailEntity<ReqDetailEntity> {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "document_req_id", nullable = true)
	private ReqDocumentEntity document;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department_id", nullable = true)
	private DepartmentEntity department;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department_opp_id", nullable = true)
	private DepartmentEntity departmentOpp;

	@Override
	public ReqDocumentEntity getDocument() {
		return document;
	}

	@Override
	public void setDocument(TraDocumentEntity document) {
		this.document = (ReqDocumentEntity) document;
	}

	public DepartmentEntity getDepartmentOpp() {
		return departmentOpp;
	}

	public void setDepartmentOpp(DepartmentEntity departmentOpp) {
		this.departmentOpp = departmentOpp;
	}

	public void setDocument(ReqDocumentEntity document) {
		this.document = document;
	}

	public DepartmentEntity getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentEntity department) {
		this.department = department;
	}

}
