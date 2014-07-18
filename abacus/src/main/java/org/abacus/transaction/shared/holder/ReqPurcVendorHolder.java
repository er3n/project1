package org.abacus.transaction.shared.holder;

import java.io.Serializable;
import java.util.List;

import org.abacus.definition.shared.entity.DefItemEntity;
import org.abacus.transaction.shared.entity.ReqDetailEntity;
import org.abacus.transaction.shared.entity.ReqDocumentEntity;
import org.abacus.transaction.shared.entity.StkDocumentEntity;

@SuppressWarnings("serial")
public class ReqPurcVendorHolder implements Serializable {

	private DefItemEntity vendor;
	private ReqDocumentEntity reqDocument;
	private List<ReqDetailEntity> details;
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

	public List<ReqDetailEntity> getDetails() {
		return details;
	}

	public void setDetails(List<ReqDetailEntity> details) {
		this.details = details;
	}

	public StkDocumentEntity getStkDocument() {
		return stkDocument;
	}

	public void setStkDocument(StkDocumentEntity stkDocument) {
		this.stkDocument = stkDocument;
	}

}
