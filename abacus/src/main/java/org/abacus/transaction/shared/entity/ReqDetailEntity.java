package org.abacus.transaction.shared.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.abacus.catering.shared.entity.CatMenuItemEntity;
import org.abacus.organization.shared.entity.DepartmentEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.util.CollectionUtils;

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

	@OneToMany(mappedBy = "detail", fetch = FetchType.LAZY)
	@Fetch(FetchMode.SELECT)
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

}
