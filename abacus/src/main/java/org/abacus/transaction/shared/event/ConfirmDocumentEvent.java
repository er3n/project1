package org.abacus.transaction.shared.event;

import org.abacus.common.shared.event.UpdatedEvent;
import org.abacus.definition.shared.entity.DefItemEntity;
import org.abacus.transaction.shared.entity.ReqDocumentEntity;

public class ConfirmDocumentEvent extends UpdatedEvent {

	private ReqDocumentEntity reqDocumentEntity;
	private DefItemEntity vendor;

	public ConfirmDocumentEvent(ReqDocumentEntity reqDocumentEntity) {
		this.reqDocumentEntity = reqDocumentEntity;
	}

	public ConfirmDocumentEvent(ReqDocumentEntity reqDocumentEntity, DefItemEntity vendor) {
		this.reqDocumentEntity = reqDocumentEntity;
		this.vendor = vendor;
	}

	public ReqDocumentEntity getReqDocumentEntity() {
		return reqDocumentEntity;
	}

	public void setReqDocumentEntity(ReqDocumentEntity reqDocumentEntity) {
		this.reqDocumentEntity = reqDocumentEntity;
	}

	public DefItemEntity getVendor() {
		return vendor;
	}

	public void setVendor(DefItemEntity vendor) {
		this.vendor = vendor;
	}

}
