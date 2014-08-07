package org.abacus.transaction.shared.holder;

import java.io.Serializable;
import java.math.BigDecimal;

import org.abacus.definition.shared.entity.DefItemEntity;

@SuppressWarnings("serial")
public class SalesDocumentHolder implements Serializable {

	private DefItemEntity item;
	private BigDecimal count;
	private BigDecimal unitPrice;
	
	public SalesDocumentHolder(DefItemEntity item, BigDecimal count, BigDecimal unitPrice){
		this.item = item; 
		this.count = count;
		this.unitPrice = unitPrice;
	}
	
	public DefItemEntity getItem() {
		return item;
	}
	public void setItem(DefItemEntity item) {
		this.item = item;
	}
	public BigDecimal getCount() {
		return count;
	}
	public void setCount(BigDecimal count) {
		this.count = count;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
}
