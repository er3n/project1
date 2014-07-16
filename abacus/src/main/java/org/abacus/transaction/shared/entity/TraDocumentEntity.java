package org.abacus.transaction.shared.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.abacus.common.shared.entity.DynamicEntity;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.entity.DefItemEntity;
import org.abacus.definition.shared.entity.DefTaskEntity;
import org.abacus.organization.shared.entity.FiscalPeriodEntity;
import org.abacus.organization.shared.entity.OrganizationEntity;

@SuppressWarnings("serial")
@MappedSuperclass
public abstract class TraDocumentEntity extends DynamicEntity {

	@Column(name = "doc_no", nullable = false, length=30)
	private String docNo;

	@Temporal(TemporalType.DATE)
	@Column(name = "doc_date", nullable = false)
	private Date docDate;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "organization_id", nullable = false)
	private OrganizationEntity organization;

	@Enumerated(EnumType.STRING)
	@Column(name = "type_id", nullable = false, length = 30)
	private EnumList.DefTypeEnum typeEnum;

	// For HQL
	@Column(name = "type_id", updatable = false, insertable = false)
	private String typeStr;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "task_id", nullable = false)
	private DefTaskEntity task;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fiscal_period1_id", nullable = false)
	private FiscalPeriodEntity fiscalPeriod1;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fiscal_period2_id", nullable = false)
	private FiscalPeriodEntity fiscalPeriod2;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id", nullable = true)
	private DefItemEntity item;

	@Column(name = "doc_note", nullable = true)
	private String docNote;

	@Temporal(TemporalType.DATE)
	@Column(name = "due_date", nullable = true)
	private Date dueDate;

	@Transient
	private EnumList.EntityStatus entityStatus;

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

	public FiscalPeriodEntity getFiscalPeriod1() {
		return fiscalPeriod1;
	}

	public void setFiscalPeriod1(FiscalPeriodEntity fiscalPeriod1) {
		this.fiscalPeriod1 = fiscalPeriod1;
	}

	public FiscalPeriodEntity getFiscalPeriod2() {
		return fiscalPeriod2;
	}

	public void setFiscalPeriod2(FiscalPeriodEntity fiscalPeriod2) {
		this.fiscalPeriod2 = fiscalPeriod2;
	}

	public String getDocNote() {
		return docNote;
	}

	public void setDocNote(String docNote) {
		this.docNote = docNote;
	}

	public DefItemEntity getItem() {
		return item;
	}

	public void setItem(DefItemEntity item) {
		this.item = item;
	}

	private String getTypeStr() {
		return typeStr;
	}

	private void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}

	public EnumList.EntityStatus getEntityStatus() {
		return entityStatus;
	}

	public void setEntityStatus(EnumList.EntityStatus entityStatus) {
		this.entityStatus = entityStatus;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

}
