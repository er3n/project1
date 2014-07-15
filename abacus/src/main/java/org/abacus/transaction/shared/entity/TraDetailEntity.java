package org.abacus.transaction.shared.entity;

import java.math.BigDecimal;
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
import org.abacus.definition.shared.entity.DefUnitCodeEntity;
import org.abacus.organization.shared.entity.FiscalYearEntity;
import org.hibernate.validator.constraints.Range;

@SuppressWarnings("serial")
@MappedSuperclass
public abstract class TraDetailEntity<D extends TraDetailEntity<D>> extends DynamicEntity {

	@Enumerated(EnumType.STRING)
	@Column(name = "resource", nullable = false, length = 30)
	private EnumList.DefTypeGroupEnum resource;

	@Temporal(TemporalType.DATE)
	@Column(name = "due_date", nullable = false)
	private Date dueDate;

	@Column(name = "tr_state_detail", nullable = false)
	@Range(min = -1, max = +1)
	private Integer trStateDetail;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id", nullable = false)
	private DefItemEntity item;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_unit_id", nullable = true)
	private DefUnitCodeEntity itemUnit;

	@Column(name = "item_detail_count", nullable = false, precision = 10, scale = 3)
	private BigDecimal itemDetailCount;

	@Column(name = "base_detail_count", nullable = false, precision = 10, scale = 3)
	private BigDecimal baseDetailCount;

	@Column(name = "unit_detail_price", nullable = false, precision = 12, scale = 2)
	private BigDecimal unitDetailPrice;

	@Column(name = "base_detail_amount", nullable = false, precision = 12, scale = 2)
	private BigDecimal baseDetailAmount;

	@Column(name = "det_note", nullable = true)
	private String detNote;

	@Column(name = "ref_detail_id", nullable = true)
	private Long refDetailId;

	@Transient
	private D point;

	@Transient
	private EnumList.EntityStatus entityStatus;

	public TraDetailEntity() {
	}

	public abstract TraDocumentEntity getDocument();

	public abstract void setDocument(TraDocumentEntity document);

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public DefItemEntity getItem() {
		return item;
	}

	public void setItem(DefItemEntity item) {
		this.item = item;
	}

	public DefUnitCodeEntity getItemUnit() {
		return itemUnit;
	}

	public void setItemUnit(DefUnitCodeEntity itemUnit) {
		this.itemUnit = itemUnit;
	}

	public BigDecimal getItemDetailCount() {
		return itemDetailCount;
	}

	public void setItemDetailCount(BigDecimal itemDetailCount) {
		this.itemDetailCount = itemDetailCount;
	}

	public BigDecimal getBaseDetailCount() {
		return baseDetailCount;
	}

	public void setBaseDetailCount(BigDecimal baseDetailCount) {
		this.baseDetailCount = baseDetailCount;
	}

	public BigDecimal getBaseDetailAmount() {
		return baseDetailAmount;
	}

	public void setBaseDetailAmount(BigDecimal baseDetailAmount) {
		this.baseDetailAmount = baseDetailAmount;
	}

	public Integer getTrStateDetail() {
		return trStateDetail;
	}

	public void setTrStateDetail(Integer trStateDetail) {
		this.trStateDetail = trStateDetail;
	}

	public String getDetNote() {
		return detNote;
	}

	public void setDetNote(String detNote) {
		this.detNote = detNote;
	}

	public Long getRefDetailId() {
		return refDetailId;
	}

	public void setRefDetailId(Long refDetailId) {
		this.refDetailId = refDetailId;
	}

	public String getTrStateSign() {
		return (trStateDetail.intValue() > 0 ? "(+)" : (trStateDetail.intValue() < 0 ? "(_)" : "(»)"));
	}

	public D getPoint() {
		return point;
	}

	public void setPoint() {
		try {
			this.point = (D) this.clone();
		} catch (CloneNotSupportedException e) {
			this.point = null;
			e.printStackTrace();
		}
	}

	public BigDecimal getUnitDetailPrice() {
		return unitDetailPrice;
	}

	public void setUnitDetailPrice(BigDecimal unitDetailPrice) {
		this.unitDetailPrice = unitDetailPrice;
	}

	public EnumList.EntityStatus getEntityStatus() {
		return entityStatus;
	}

	public void setEntityStatus(EnumList.EntityStatus entityStatus) {
		this.entityStatus = entityStatus;
	}

	public EnumList.DefTypeGroupEnum getResource() {
		return resource;
	}

	public void setResource(EnumList.DefTypeGroupEnum resource) {
		this.resource = resource;
	}

}
