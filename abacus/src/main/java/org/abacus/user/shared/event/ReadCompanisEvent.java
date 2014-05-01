package org.abacus.user.shared.event;

import java.util.List;

import org.abacus.common.shared.event.ReadEvent;
import org.abacus.user.shared.entity.CompanyEntity;

public class ReadCompanisEvent extends ReadEvent {

	private List<CompanyEntity> companyList;

	public ReadCompanisEvent(List<CompanyEntity> companyList) {
		this.companyList = companyList;
	}

	public List<CompanyEntity> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(List<CompanyEntity> companyList) {
		this.companyList = companyList;
	}

}
