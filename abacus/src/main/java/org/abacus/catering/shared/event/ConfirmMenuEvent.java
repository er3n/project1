package org.abacus.catering.shared.event;

import org.abacus.catering.shared.entity.CatMenuEntity;
import org.abacus.common.shared.event.UpdatedEvent;
import org.abacus.organization.shared.entity.DepartmentEntity;

public class ConfirmMenuEvent extends UpdatedEvent {

	private CatMenuEntity menu;
	private String username;
	private String fiscalYear;
	private DepartmentEntity departmentEntity;
	private String organization;
	private String rootOrganization;

	public ConfirmMenuEvent(CatMenuEntity menu, DepartmentEntity departmentEntity, String organization, String fiscalYear, String username,String rootOrganization) {
		this.menu = menu;
		this.departmentEntity = departmentEntity;
		this.organization = organization;
		this.fiscalYear = fiscalYear;
		this.username = username;
		this.rootOrganization = rootOrganization;
	}

	public CatMenuEntity getMenu() {
		return menu;
	}

	public void setMenu(CatMenuEntity menu) {
		this.menu = menu;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFiscalYear() {
		return fiscalYear;
	}

	public void setFiscalYear(String fiscalYear) {
		this.fiscalYear = fiscalYear;
	}

	public DepartmentEntity getDepartmentEntity() {
		return departmentEntity;
	}

	public void setDepartmentEntity(DepartmentEntity departmentEntity) {
		this.departmentEntity = departmentEntity;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getRootOrganization() {
		return rootOrganization;
	}

	public void setRootOrganization(String rootOrganization) {
		this.rootOrganization = rootOrganization;
	}

}
