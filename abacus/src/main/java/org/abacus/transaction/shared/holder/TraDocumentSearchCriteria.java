package org.abacus.transaction.shared.holder;

import java.io.Serializable;
import java.util.Date;

import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.entity.DefTaskEntity;
import org.abacus.organization.shared.entity.DepartmentEntity;

@SuppressWarnings("serial")
public class TraDocumentSearchCriteria implements Serializable {

	private Long documentId;

	private String docNo;

	private Date docStartDate;

	private Date docEndDate;

	private DepartmentEntity department;

	private DepartmentEntity departmentOpp;

	private DefTaskEntity docTask;

	private EnumList.DefTypeEnum docType;

	private EnumList.RequestStatus requestStatus;
	
	private String bsSelection = "0";

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

	public DefTaskEntity getDocTask() {
		return docTask;
	}

	public void setDocTask(DefTaskEntity docTask) {
		this.docTask = docTask;
	}

	public EnumList.RequestStatus getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(EnumList.RequestStatus requestStatus) {
		this.requestStatus = requestStatus;
	}

	/**
	 * @return the bsSelection
	 */
	public String getBsSelection() {
		return bsSelection;
	}

	/**
	 * @param bsSelection the bsSelection to set
	 */
	public void setBsSelection(String bsSelection) {
		this.bsSelection = bsSelection;
	}

}
