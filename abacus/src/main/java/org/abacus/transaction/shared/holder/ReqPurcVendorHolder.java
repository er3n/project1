package org.abacus.transaction.shared.holder;

import java.io.Serializable;

import org.abacus.definition.shared.entity.DefItemEntity;
import org.abacus.transaction.shared.entity.ReqDetailEntity;
import org.abacus.transaction.shared.entity.ReqDocumentEntity;
import org.abacus.transaction.shared.entity.StkDocumentEntity;

@SuppressWarnings("serial")
public class ReqPurcVendorHolder implements Serializable {

	private DefItemEntity vendor;
	private ReqDocumentEntity reqDocument;
	private ReqDetailEntity details;
	private StkDocumentEntity stkDocument;

	public DefItemEntity getVendor() {
		return vendor;
	}

	public void setVendor(DefItemEntity vendor) {
		this.vendor = vendor;
	}

	public ReqDocumentEntity getReqDocument() {
		return reqDocument;
	}

	public void setReqDocument(ReqDocumentEntity reqDocument) {
		this.reqDocument = reqDocument;
	}

	public ReqDetailEntity getDetails() {
		return details;
	}

	public void setDetails(ReqDetailEntity details) {
		this.details = details;
	}

	public StkDocumentEntity getStkDocument() {
		return stkDocument;
	}

	public void setStkDocument(StkDocumentEntity stkDocument) {
		this.stkDocument = stkDocument;
	}

}
