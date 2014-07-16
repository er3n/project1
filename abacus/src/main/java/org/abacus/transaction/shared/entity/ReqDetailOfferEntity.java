package org.abacus.transaction.shared.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.abacus.common.shared.entity.DynamicEntity;
import org.abacus.definition.shared.entity.DefItemEntity;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "req_detail_offer")
@SuppressWarnings("serial")
public class ReqDetailOfferEntity extends DynamicEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "detail_id", nullable = false)
	private ReqDetailEntity detail;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vendor_item_id", nullable = false)
	private DefItemEntity vendorItem;

	@Column(name = "unit_offer_prive", nullable = false, precision = 12, scale = 2)
	private BigDecimal unitOfferPrice;
	
	@Column(name = "is_selected", nullable = false)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean isSelected = false;
	
	@Column(name = "offer_note", nullable = true)
	private String offerNote;
	
	public ReqDetailOfferEntity() {
	}

	public ReqDetailEntity getDetail() {
		return detail;
	}

	public void setDetail(ReqDetailEntity detail) {
		this.detail = detail;
	}

	public DefItemEntity getVendorItem() {
		return vendorItem;
	}

	public void setVendorItem(DefItemEntity vendorItem) {
		this.vendorItem = vendorItem;
	}

	public BigDecimal getUnitOfferPrice() {
		return unitOfferPrice;
	}

	public void setUnitOfferPrice(BigDecimal unitOfferPrice) {
		this.unitOfferPrice = unitOfferPrice;
	}

	public Boolean getIsSelected() {
		return isSelected;
	}

	public void setIsSelected(Boolean isSelected) {
		this.isSelected = isSelected;
	}

	public String getOfferNote() {
		return offerNote;
	}

	public void setOfferNote(String offerNote) {
		this.offerNote = offerNote;
	}

}
