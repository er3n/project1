package org.abacus.transaction.shared.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.abacus.common.shared.entity.DynamicEntity;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.entity.DefTaskEntity;
import org.abacus.organization.shared.entity.FiscalPeriodEntity;
import org.abacus.organization.shared.entity.OrganizationEntity;
import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "tra_document")
@SuppressWarnings("serial")
public class TraDocumentEntity extends DynamicEntity {

	@Column(name = "doc_no", nullable = false)
	private String docNo;

	@Temporal(TemporalType.DATE)
	@Column(name = "doc_date", nullable = false)
	private Date docDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id", nullable = false)
	private OrganizationEntity organization;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "type_id", nullable = false)
	private EnumList.DefTypeEnum typeEnum;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "task_id", nullable = false)
	private DefTaskEntity task;

	@Column(name = "tr_state_document", nullable = false)
	@Range(min=-1, max=+1)
	private Integer trStateDocument;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fiscal_period_id", nullable = false)
	private FiscalPeriodEntity fiscalPeriod;
	
	@Column(name = "doc_note", nullable = true)
	private String docNote;

	public TraDocumentEntity() {
	}

	public String getDocNo() {
		return docNo;
	}

	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}

	public Date getDocDate() {
		return docDate;
	}

	public void setDocDate(Date docDate) {
		this.docDate = docDate;
	}

	public OrganizationEntity getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationEntity organization) {
		this.organization = organization;
	}

	public EnumList.DefTypeEnum getTypeEnum() {
		return typeEnum;
	}

	public void setTypeEnum(EnumList.DefTypeEnum typeEnum) {
		this.typeEnum = typeEnum;
	}

	public DefTaskEntity getTask() {
		return task;
	}

	public void setTask(DefTaskEntity task) {
		this.task = task;
	}

	public FiscalPeriodEntity getFiscalPeriod() {
		return fiscalPeriod;
	}

	public void setFiscalPeriod(FiscalPeriodEntity fiscalPeriod) {
		this.fiscalPeriod = fiscalPeriod;
	}

	public String getDocNote() {
		return docNote;
	}

	public void setDocNote(String docNote) {
		this.docNote = docNote;
	}

	public Integer getTrStateDocument() {
		return trStateDocument;
	}

	public void setTrStateDocument(Integer trStateDocument) {
		this.trStateDocument = trStateDocument;
	}

}
