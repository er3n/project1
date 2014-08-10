package org.abacus.catering.shared.event;

import java.util.List;

import org.abacus.catering.shared.entity.CatMenuEntity;
import org.abacus.common.shared.event.UpdatedEvent;
import org.abacus.organization.shared.entity.FiscalYearEntity;
import org.abacus.transaction.shared.entity.StkDetailEntity;
import org.abacus.transaction.shared.entity.StkDocumentEntity;

public class ConfirmMenuEvent extends UpdatedEvent {

	private StkDocumentEntity stkDocument;
	private List<StkDetailEntity> details;
	private String user;
	private CatMenuEntity menu;
	private FiscalYearEntity fiscalYear;

	public ConfirmMenuEvent(StkDocumentEntity stkDocument, List<StkDetailEntity> details, CatMenuEntity menu, String user, FiscalYearEntity fiscalYear) {
		this.stkDocument = stkDocument;
		this.details = details;
		this.menu = menu;
		this.user = user;
		this.fiscalYear = fiscalYear;
	}

	public StkDocumentEntity getStkDocument() {
		return stkDocument;
	}

	public void setStkDocument(StkDocumentEntity stkDocument) {
		this.stkDocument = stkDocument;
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

	public FiscalYearEntity getFiscalYear() {
		return fiscalYear;
	}

	public void setFiscalYear(FiscalYearEntity fiscalYear) {
		this.fiscalYear = fiscalYear;
	}

}
