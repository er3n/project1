package org.abacus.report.shared.holder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.abacus.definition.shared.entity.DefItemEntity;
import org.abacus.definition.shared.entity.DefTaskEntity;
import org.abacus.organization.shared.entity.DepartmentEntity;
import org.abacus.organization.shared.entity.FiscalYearEntity;
import org.abacus.organization.shared.entity.OrganizationEntity;

@SuppressWarnings("serial")
public class ReportSearchCriteria implements Serializable {

	private OrganizationEntity organization; 
	
	private FiscalYearEntity fiscalYear;
	
	private Date docStartDate;

	private Date docEndDate;

	private DefTaskEntity docTask;
	
	private DepartmentEntity detailDepartment;
	
	private DefItemEntity detailItem;
	
	private BigDecimal detailCount;
	
	public ReportSearchCriteria() {

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

	public OrganizationEntity getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationEntity organization) {
		this.organization = organization;
	}

	public FiscalYearEntity getFiscalYear() {
		return fiscalYear;
	}

	public void setFiscalYear(FiscalYearEntity fiscalYear) {
		this.fiscalYear = fiscalYear;
	}

}
