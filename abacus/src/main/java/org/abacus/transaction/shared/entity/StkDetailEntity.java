package org.abacus.transaction.shared.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.abacus.organization.shared.entity.DepartmentEntity;

@Entity
@Table(name = "stk_detail")
@SuppressWarnings("serial")
public class StkDetailEntity extends TraDetailEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "document_id", nullable = false)
	private StkDocumentEntity document;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department_opp_id", nullable = true)
	private DepartmentEntity departmentOpp;

	@Column(name = "batch_detail_no", nullable = true)
	private String batchDetailNo;
	
	//irsaliye den faturaya
	@Column(name = "ref_fin_detail_id", nullable = true)
	private Long refFinDetailId;
		
		
	public StkDetailEntity() {
	}

	@Override
	public TraDocumentEntity getDocument() {
		return document;
	}

	@Override
	public void setDocument(TraDocumentEntity document) {
		this.document = (StkDocumentEntity) document;
	}

	public DepartmentEntity getDepartmentOpp() {
		return departmentOpp;
	}

	public void setDepartmentOpp(DepartmentEntity departmentOpp) {
		this.departmentOpp = departmentOpp;
	}

	public String getBatchDetailNo() {
		return batchDetailNo;
	}

	public void setBatchDetailNo(String batchDetailNo) {
		this.batchDetailNo = batchDetailNo;
	}

	public Long getRefFinDetailId() {
		return refFinDetailId;
	}

	public void setRefFinDetailId(Long refFinDetailId) {
		this.refFinDetailId = refFinDetailId;
	}
}
