package org.abacus.user.shared.event;

import java.util.List;

import org.abacus.common.shared.event.ReadEvent;
import org.abacus.organization.shared.entity.CompanyEntity;

public class ReadCompaniesEvent extends ReadEvent {

	private List<CompanyEntity> companyList;

	public ReadCompaniesEvent(List<CompanyEntity> companyList) {
		this.companyList = companyList;
	}

	public List<CompanyEntity> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(List<CompanyEntity> companyList) {
		this.companyList = companyList;
	}

}
