package org.abacus.report.shared.holder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.abacus.definition.shared.entity.DefItemEntity;
import org.abacus.definition.shared.entity.DefTaskEntity;
import org.abacus.organization.shared.entity.DepartmentEntity;

@SuppressWarnings("serial")
public class ReportSearchCriteria implements Serializable {

	private Long documentId;

	private String docNo;

	private Date docStartDate;

	private Date docEndDate;

	private DefTaskEntity docTask;
	
	private DepartmentEntity detailDepartment;
	
	private DefItemEntity detailItem;
	
	private BigDecimal detailCount;
	
	public ReportSearchCriteria() {

	}

	public ReportSearchCriteria(Long documentId) {
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

	public DefTaskEntity getDocTask() {
		return docTask;
	}

	public void setDocTask(DefTaskEntity docTask) {
		this.docTask = docTask;
	}

	public DepartmentEntity getDetailDepartment() {
		return detailDepartment;
	}

	public void setDetailDepartment(DepartmentEntity detailDepartment) {
		this.detailDepartment = detailDepartment;
	}

	public DefItemEntity getDetailItem() {
		return detailItem;
	}

	public void setDetailItem(DefItemEntity detailItem) {
		this.detailItem = detailItem;
	}

	public BigDecimal getDetailCount() {
		return detailCount;
	}

	public void setDetailCount(BigDecimal detailCount) {
		this.detailCount = detailCount;
	}

}
