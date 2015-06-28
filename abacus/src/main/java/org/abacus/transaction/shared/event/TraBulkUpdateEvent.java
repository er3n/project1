package org.abacus.transaction.shared.event;

import java.util.List;

import org.abacus.organization.shared.entity.FiscalYearEntity;
import org.abacus.organization.shared.entity.OrganizationEntity;
import org.abacus.transaction.shared.entity.TraDetailEntity;
import org.abacus.transaction.shared.entity.TraDocumentEntity;

public class TraBulkUpdateEvent<T extends TraDocumentEntity, D extends TraDetailEntity> {

	private T document;
	private List<D> detailList;
	private String user;
	private OrganizationEntity organization;
	private FiscalYearEntity fiscalYear2;

	public TraBulkUpdateEvent(T document, List<D> detailList, String user, OrganizationEntity organization, FiscalYearEntity fiscalYear2) {
		this.document = document;
		this.detailList = detailList;
		this.user = user;
		this.organization = organization;
		this.fiscalYear2 = fiscalYear2;
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

	public OrganizationEntity getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationEntity organization) {
		this.organization = organization;
	}

	public FiscalYearEntity getFiscalYear2() {
		return fiscalYear2;
	}

	public void setFiscalYear(FiscalYearEntity fiscalYear2) {
		this.fiscalYear2 = fiscalYear2;
	}

}
