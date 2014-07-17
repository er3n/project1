package org.abacus.transaction.shared.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.abacus.organization.shared.entity.DepartmentEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OrderBy;

@Entity
@Table(name = "tra_detail")
@SuppressWarnings("serial")
public class ReqDetailEntity extends TraDetailEntity<ReqDetailEntity> {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "document_req_id", nullable = true)
	private ReqDocumentEntity document;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "document_stk_id", nullable = true)
	private StkDocumentEntity stkDocument;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department_id", nullable = true)
	private DepartmentEntity department;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department_opp_id", nullable = true)
	private DepartmentEntity departmentOpp;

	@OneToMany(mappedBy = "detail", fetch = FetchType.LAZY)
	@Fetch(FetchMode.SELECT)
	@OrderBy(clause = "unitOfferPrice desc")
	private Set<ReqDetailOfferEntity> offerSet;

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

	public Set<ReqDetailOfferEntity> getOfferSet() {
		return offerSet;
	}

	public void setOfferSet(Set<ReqDetailOfferEntity> offerSet) {
		this.offerSet = offerSet;
	}

	public StkDocumentEntity getStkDocument() {
		return stkDocument;
	}

	public void setStkDocument(StkDocumentEntity stkDocument) {
		this.stkDocument = stkDocument;
	}

}
