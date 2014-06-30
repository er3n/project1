package org.abacus.catering.shared.event;

import org.abacus.catering.shared.entity.CatMenuEntity;
import org.abacus.organization.shared.entity.DepartmentEntity;

public class CreateMenuPeriviewEvent {

	private CatMenuEntity menu;
	private DepartmentEntity departmentEntity;
	private String rootOrganization;

	public CreateMenuPeriviewEvent(CatMenuEntity menu, DepartmentEntity departmentEntity, String rootOrganization) {
		this.menu = menu;
		this.departmentEntity = departmentEntity;
		this.rootOrganization = rootOrganization;
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

	public String getRootOrganization() {
		return rootOrganization;
	}

	public void setRootOrganization(String rootOrganization) {
		this.rootOrganization = rootOrganization;
	}

}
