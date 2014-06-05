package org.abacus.transaction.shared.holder;

import java.io.Serializable;
import java.util.Date;

import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.entity.DefTaskEntity;

@SuppressWarnings("serial")
public class SearchDocumentCriteria implements Serializable {

	private String docNo;
	private Date docDate;
	private String organization;
	private EnumList.DefTypeEnum typeEnum;
	private DefTaskEntity task;
	private Integer trStateDocument;

	public String getDocNo() {
		return docNo;
	}

	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}

	public Date getDocDate() {
		return docDate;
	}

	public void setDocDate(Date docDate) {
		this.docDate = docDate;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public EnumList.DefTypeEnum getTypeEnum() {
		return typeEnum;
	}

	public void setTypeEnum(EnumList.DefTypeEnum typeEnum) {
		this.typeEnum = typeEnum;
	}

	public DefTaskEntity getTask() {
		return task;
	}

	public void setTask(DefTaskEntity task) {
		this.task = task;
	}

	public Integer getTrStateDocument() {
		return trStateDocument;
	}

	public void setTrStateDocument(Integer trStateDocument) {
		this.trStateDocument = trStateDocument;
	}

}
