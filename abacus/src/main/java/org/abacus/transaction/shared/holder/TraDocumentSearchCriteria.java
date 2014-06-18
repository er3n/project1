package org.abacus.transaction.shared.holder;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class TraDocumentSearchCriteria implements Serializable {

	private Long documentId;

	private String docNo;

	private Date docStartDate;

	private Date docEndDate;

	public TraDocumentSearchCriteria() {

	}

	public TraDocumentSearchCriteria(Long documentId) {
		this.documentId = documentId;
	}

	public String getDocNo() {
		return docNo;
	}

	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}

	public Date getDocStartDate() {
		return docStartDate;
	}

	public void setDocStartDate(Date docStartDate) {
		this.docStartDate = docStartDate;
	}

	public Date getDocEndDate() {
		return docEndDate;
	}

	public void setDocEndDate(Date docEndDate) {
		this.docEndDate = docEndDate;
	}

	public Long getDocumentId() {
		return documentId;
	}

	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}

}
