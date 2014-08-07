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

	@Column(name = "batch_detail_no", nullable = true, length=30)
	private String batchDetailNo;
	
	//ref:stk:transferIn stk:transferOut u bilir
	@Column(name = "ref_stk_detail_id", nullable = true)
	private Long refStkDetailId;

	public StkDetailEntity() {
	}
	
	public StkDetailEntity(ReqDetailEntity sourceDet,StkDocumentEntity stkDoc){
		ReqDetailEntity temReqDetail = new ReqDetailEntity();
		BeanUtils.copyProperties(sourceDet, temReqDetail);
		temReqDetail.setDocument(null);
		BeanUtils.copyProperties(temReqDetail, this);
		this.setDocument(stkDoc);
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

	public Long getRefStkDetailId() {
		return refStkDetailId;
	}

	public void setRefStkDetailId(Long refStkDetailId) {
		this.refStkDetailId = refStkDetailId;
	}

}
