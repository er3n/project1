package org.abacus.transaction.shared.holder;

import java.io.Serializable;
import java.util.Date;

import org.abacus.definition.shared.constant.EnumList;
import org.abacus.organization.shared.entity.DepartmentEntity;

@SuppressWarnings("serial")
public class TraDocumentSearchCriteria implements Serializable {

	private Long documentId;

	private String docNo;

	private Date docStartDate;

	private Date docEndDate;

	private DepartmentEntity department;

	private DepartmentEntity departmentOpp;

	private EnumList.DefTypeEnum docType;

	public TraDocumentSearchCriteria() {

	}

	public TraDocumentSearchCriteria(Long documentId) {
		this.documentId = documentId;
	}

	public String getDocNo() {
		return docNo;
	}

	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}

	public Date getDocStartDate() {
		return docStartDate;
	}

	public void setDocStartDate(Date docStartDate) {
		this.docStartDate = docStartDate;
	}

	public Date getDocEndDate() {
		return docEndDate;
	}

	public void setDocEndDate(Date docEndDate) {
		this.docEndDate = docEndDate;
	}

	public Long getDocumentId() {
		return documentId;
	}

	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}

	public EnumList.DefTypeEnum getDocType() {
		return docType;
	}

	public void setDocType(EnumList.DefTypeEnum docType) {
		this.docType = docType;
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
