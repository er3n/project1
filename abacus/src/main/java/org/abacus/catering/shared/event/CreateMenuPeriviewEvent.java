package org.abacus.catering.shared.event;

import org.abacus.catering.shared.entity.CatMenuEntity;
import org.abacus.organization.shared.entity.DepartmentEntity;
import org.abacus.organization.shared.entity.FiscalYearEntity;

public class CreateMenuPeriviewEvent {

	private CatMenuEntity menu;
	private DepartmentEntity departmentEntity;
	private FiscalYearEntity fiscalYear;

	public CreateMenuPeriviewEvent(CatMenuEntity menu, DepartmentEntity departmentEntity, FiscalYearEntity fiscalYear) {
		this.menu = menu;
		this.departmentEntity = departmentEntity;
		this.fiscalYear = fiscalYear;
	}

	public CatMenuEntity getMenu() {
		return menu;
	}

	public void setMenu(CatMenuEntity menu) {
		this.menu = menu;
	}

	public DepartmentEntity getDepartmentEntity() {
		return departmentEntity;
	}

	public void setDepartmentEntity(DepartmentEntity departmentEntity) {
		this.departmentEntity = departmentEntity;
	}

	public FiscalYearEntity getFiscalYear() {
		return fiscalYear;
	}

	public void setFiscalYear(FiscalYearEntity fiscalYear) {
		this.fiscalYear = fiscalYear;
	}


}
