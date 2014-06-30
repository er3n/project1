package org.abacus.catering.shared.event;

import java.util.List;

import org.abacus.catering.shared.entity.CatMenuEntity;
import org.abacus.common.shared.event.UpdatedEvent;
import org.abacus.transaction.shared.entity.StkDetailEntity;
import org.abacus.transaction.shared.entity.StkDocumentEntity;

public class ConfirmMenuEvent extends UpdatedEvent {

	private StkDocumentEntity document;
	private List<StkDetailEntity> details;
	private String user;
	private CatMenuEntity menu;
	private String fiscalYear;
	private String organization;

	public ConfirmMenuEvent(StkDocumentEntity document, List<StkDetailEntity> details, CatMenuEntity menu, String user, String organization, String fiscalYear) {
		this.document = document;
		this.details = details;
		this.menu = menu;
		this.user = user;
		this.organization = organization;
		this.fiscalYear = fiscalYear;
	}

	public StkDocumentEntity getDocument() {
		return document;
	}

	public void setDocument(StkDocumentEntity document) {
		this.document = document;
	}

	public List<StkDetailEntity> getDetails() {
		return details;
	}

	public void setDetails(List<StkDetailEntity> details) {
		this.details = details;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public CatMenuEntity getMenu() {
		return menu;
	}

	public void setMenu(CatMenuEntity menu) {
		this.menu = menu;
	}

	public String getFiscalYear() {
		return fiscalYear;
	}

	public void setFiscalYear(String fiscalYear) {
		this.fiscalYear = fiscalYear;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

}
