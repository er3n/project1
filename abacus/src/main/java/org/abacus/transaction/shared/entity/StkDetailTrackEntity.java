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
import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "stk_detail_track")
@SuppressWarnings("serial")
public class StkDetailTrackEntity extends DynamicEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "detail_id", nullable = false)
	private StkDetailEntity detail;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "root_detail_id", nullable = false)
	private StkDetailEntity rootDetail;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_track_id", nullable = true)
	private StkDetailTrackEntity parentTrack;

	@Column(name = "base_track_count", nullable = false, precision = 10, scale = 3)
	private BigDecimal baseTrackCount;

	@Column(name = "base_used_count", nullable = false, precision = 10, scale = 3)
	private BigDecimal baseUsedCount;

	@Column(name = "unit_track_price", nullable = false, precision = 12, scale = 2)
	private BigDecimal unitTrackPrice;

	@Column(name = "unit_cost_price", nullable = false, precision = 12, scale = 2)
	private BigDecimal unitCostPrice;
	
	@Column(name = "tr_state_track", nullable = false)
	@Range(min=-1, max=+1)
	private Integer trStateTrack;

	@Temporal(TemporalType.DATE)
	@Column(name = "lot_track_date", nullable = false)
	private Date lotTrackDate;

	@Column(name = "batch_track_no", nullable = false)
	private String batchTrackNo;

	public StkDetailTrackEntity() {
	}

	public StkDetailEntity getDetail() {
		return detail;
	}

	public void setDetail(StkDetailEntity detail) {
		this.detail = detail;
	}

	public StkDetailEntity getRootDetail() {
		return rootDetail;
	}

	public void setRootDetail(StkDetailEntity rootDetail) {
		this.rootDetail = rootDetail;
	}

	public StkDetailTrackEntity getParentTrack() {
		return parentTrack;
	}

	public void setParentTrack(StkDetailTrackEntity parentTrack) {
		this.parentTrack = parentTrack;
	}

	public BigDecimal getBaseTrackCount() {
		return baseTrackCount;
	}

	public void setBaseTrackCount(BigDecimal baseTrackCount) {
		this.baseTrackCount = baseTrackCount;
	}

	public BigDecimal getBaseUsedCount() {
		return baseUsedCount;
	}

	public void setBaseUsedCount(BigDecimal baseUsedCount) {
		this.baseUsedCount = baseUsedCount;
	}

	public BigDecimal getUnitTrackPrice() {
		return unitTrackPrice;
	}

	public void setUnitTrackPrice(BigDecimal unitTrackPrice) {
		this.unitTrackPrice = unitTrackPrice;
	}

	public BigDecimal getUnitCostPrice() {
		return unitCostPrice;
	}

	public void setUnitCostPrice(BigDecimal unitCostPrice) {
		this.unitCostPrice = unitCostPrice;
	}

	public Date getLotTrackDate() {
		return lotTrackDate;
	}

	public void setLotTrackDate(Date lotTrackDate) {
		this.lotTrackDate = lotTrackDate;
	}

	public String getBatchTrackNo() {
		return batchTrackNo;
	}

	public void setBatchTrackNo(String batchTrackNo) {
		this.batchTrackNo = batchTrackNo;
	}

	public Integer getTrStateTrack() {
		return trStateTrack;
	}

	public void setTrStateTrack(Integer trStateTrack) {
		this.trStateTrack = trStateTrack;
	}

}
