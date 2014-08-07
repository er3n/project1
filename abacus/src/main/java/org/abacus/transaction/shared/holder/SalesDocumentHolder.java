package org.abacus.transaction.shared.holder;

import java.io.Serializable;
import java.math.BigDecimal;

import org.abacus.definition.shared.entity.DefItemEntity;

@SuppressWarnings("serial")
public class SalesDocumentHolder implements Serializable {

	private DefItemEntity item;
	private BigDecimal unitPrice;
	private BigDecimal count;
	
	public DefItemEntity getItem() {
		return item;
	}
	public void setItem(DefItemEntity item) {
		this.item = item;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public BigDecimal getCount() {
		return count;
	}
	public void setCount(BigDecimal count) {
		this.count = count;
	}

}
