package org.abacus.transaction.shared.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.abacus.organization.shared.entity.DepartmentEntity;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name = "tra_detail")
@SuppressWarnings("serial")
public class StkDetailEntity extends TraDetailEntity<StkDetailEntity> {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "document_stk_id", nullable = true)
	private StkDocumentEntity document;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department_id", nullable = true)
	private DepartmentEntity department;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department_opp_id", nullable = true)
	private DepartmentEntity departmentOpp;

	@Column(name = "batch_detail_no", nullable = true)
	private String batchDetailNo;
	
	//irsaliye den faturaya
//	@Column(name = "ref_fin_detail_id", nullable = true)
//	private Long refFinDetailId;
		
	public StkDetailEntity() {
	}
	
	public StkDetailEntity(TraDetailEntity<?> sourceDet,StkDocumentEntity stkDoc){
		TraDocumentEntity thisDocument = sourceDet.getDocument();
		sourceDet.setDocument(null);
		
		BeanUtils.copyProperties(sourceDet, this);
		this.setDocument(stkDoc);
		
		sourceDet.setDocument(thisDocument);
	}

	@Override
	public StkDocumentEntity getDocument() {
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

	public DepartmentEntity getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentEntity department) {
		this.department = department;
	}

}
