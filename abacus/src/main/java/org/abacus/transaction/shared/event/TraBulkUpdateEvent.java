package org.abacus.transaction.shared.event;

import java.util.List;

import org.abacus.transaction.shared.entity.TraDetailEntity;
import org.abacus.transaction.shared.entity.TraDocumentEntity;

public class TraBulkUpdateEvent<T extends TraDocumentEntity, D extends TraDetailEntity<D>> {

	private T document;
	private List<D> detailList;
	private String user;
	private String organization;
	private String fiscalYear;

	public TraBulkUpdateEvent(T document, List<D> detailList, String user, String organization, String fiscalYear) {
		this.document = document;
		this.detailList = detailList;
		this.user = user;
		this.organization = organization;
		this.fiscalYear = fiscalYear;
	}

	public T getDocument() {
		return document;
	}

	public void setDocument(T document) {
		this.document = document;
	}

	public List<D> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<D> detailList) {
		this.detailList = detailList;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getFiscalYear() {
		return fiscalYear;
	}

	public void setFiscalYear(String fiscalYear) {
		this.fiscalYear = fiscalYear;
	}

}
