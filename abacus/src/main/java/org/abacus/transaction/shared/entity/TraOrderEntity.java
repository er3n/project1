package org.abacus.transaction.shared.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.abacus.common.shared.entity.DynamicEntity;

@Entity
@Table(name = "tra_order")
@SuppressWarnings("serial")
public class TraOrderEntity extends DynamicEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "detail_id", nullable = false)
	private TraDetailEntity detail;
	
	@Column(name = "parent_order_id", nullable = false)
	private Long parentOrderId;

	@Column(name = "root_order_id", nullable = false)
	private Long rootOrderId;

	@Column(name = "root_detail_id", nullable = false)
	private Long rootDetailId;
	
	@Column(name = "base_order_count", nullable = false, precision = 10, scale = 3)
	private BigDecimal baseOrderCount;

	@Column(name = "base_used_count", nullable = false, precision = 10, scale = 3)
	private BigDecimal baseUsedCount;

	@Column(name = "batch_order_no", nullable = true)
	private String batchOrderNo;
	
	@Column(name = "unit_order_price", nullable = false, precision = 12, scale = 2)
	private BigDecimal unitOrderPrice;

	@Column(name = "base_order_price", nullable = false, precision = 12, scale = 2)
	private BigDecimal baseOrderPrice;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "lot_order_date", nullable = false)
	private Date lotOrderDate;

	public TraOrderEntity() {
	}

	public TraDetailEntity getDetail() {
		return detail;
	}

	public void setDetail(TraDetailEntity detail) {
		this.detail = detail;
	}

	public Long getParentOrderId() {
		return parentOrderId;
	}

	public void setParentOrderId(Long parentOrderId) {
		this.parentOrderId = parentOrderId;
	}

	public Long getRootOrderId() {
		return rootOrderId;
	}

	public void setRootOrderId(Long rootOrderId) {
		this.rootOrderId = rootOrderId;
	}

	public Long getRootDetailId() {
		return rootDetailId;
	}

	public void setRootDetailId(Long rootDetailId) {
		this.rootDetailId = rootDetailId;
	}

	public BigDecimal getBaseOrderCount() {
		return baseOrderCount;
	}

	public void setBaseOrderCount(BigDecimal baseOrderCount) {
		this.baseOrderCount = baseOrderCount;
	}

	public BigDecimal getBaseUsedCount() {
		return baseUsedCount;
	}

	public void setBaseUsedCount(BigDecimal baseUsedCount) {
		this.baseUsedCount = baseUsedCount;
	}

	public String getBatchOrderNo() {
		return batchOrderNo;
	}

	public void setBatchOrderNo(String batchOrderNo) {
		this.batchOrderNo = batchOrderNo;
	}

	public BigDecimal getUnitOrderPrice() {
		return unitOrderPrice;
	}

	public void setUnitOrderPrice(BigDecimal unitOrderPrice) {
		this.unitOrderPrice = unitOrderPrice;
	}

	public BigDecimal getBaseOrderPrice() {
		return baseOrderPrice;
	}

	public void setBaseOrderPrice(BigDecimal baseOrderPrice) {
		this.baseOrderPrice = baseOrderPrice;
	}

	public Date getLotOrderDate() {
		return lotOrderDate;
	}

	public void setLotOrderDate(Date lotOrderDate) {
		this.lotOrderDate = lotOrderDate;
	}

}
