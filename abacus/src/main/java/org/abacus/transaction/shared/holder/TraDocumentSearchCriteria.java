package org.abacus.transaction.shared.holder;

import java.io.Serializable;
import java.util.Date;

import org.abacus.definition.shared.constant.EnumList;

@SuppressWarnings("serial")
public class TraDocumentSearchCriteria implements Serializable {

	private EnumList.DefTypeGroupEnum documentGroupEnum;
	
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

	public EnumList.DefTypeGroupEnum getDocumentGroupEnum() {
		return documentGroupEnum;
	}

	public void setDocumentGroupEnum(EnumList.DefTypeGroupEnum documentGroupEnum) {
		this.documentGroupEnum = documentGroupEnum;
	}

}
