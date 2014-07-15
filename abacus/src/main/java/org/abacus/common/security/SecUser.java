package org.abacus.common.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.abacus.definition.shared.entity.DefItemEntity;
import org.abacus.organization.core.util.OrganizationUtils;
import org.abacus.organization.shared.entity.FiscalYearEntity;
import org.abacus.organization.shared.entity.OrganizationEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

@SuppressWarnings("serial")
public class SecUser implements UserDetails {

	private String username;
	private String password;
	private boolean isActive;

	private List<String> authorityNames;

	private List<OrganizationEntity> organizationList;
	private OrganizationEntity selectedOrganization;

	private OrganizationEntity rootOrganization;

	private FiscalYearEntity selectedFiscalYear;

	private Set<FiscalYearEntity> companyFiscalYearSet;

	private DefItemEntity vendor;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();

		if (!CollectionUtils.isEmpty(authorityNames)) {
			for (final String authorityName : authorityNames) {
				GrantedAuthority authority = new GrantedAuthority() {
					@Override
					public String getAuthority() {
						return authorityName;
					}
				};
				authorities.add(authority);
			}
		}

		return authorities;
	}

	public void init(List<OrganizationEntity> organizationList, OrganizationEntity selectedOrganization, Set<FiscalYearEntity> companyFiscalYearSet, FiscalYearEntity defaultFiscalYear,DefItemEntity vendor) {
		setOrganizationList(organizationList);
		setSelectedOrganization(selectedOrganization);
		setCompanyFiscalYearSet(companyFiscalYearSet);
		setSelectedFiscalYear(defaultFiscalYear);
		setVendor(vendor);
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return isActive();
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public List<String> getAuthorityNames() {
		return authorityNames;
	}

	public void setAuthorityNames(List<String> authorityNames) {
		this.authorityNames = authorityNames;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<OrganizationEntity> getOrganizationList() {
		return organizationList;
	}

	public void setOrganizationList(List<OrganizationEntity> organizationList) {
		this.organizationList = organizationList;
	}

	public OrganizationEntity getSelectedOrganization() {
		return selectedOrganization;
	}

	public void setSelectedOrganization(OrganizationEntity selectedOrganization) {
		this.selectedOrganization = selectedOrganization;
		this.rootOrganization = OrganizationUtils.findRootOrganization(this.selectedOrganization);
	}

	public OrganizationEntity getRootOrganization() {
		return rootOrganization;
	}

	public void setRootOrganization(OrganizationEntity rootOrganization) {
		this.rootOrganization = rootOrganization;
	}

	public FiscalYearEntity getSelectedFiscalYear() {
		return selectedFiscalYear;
	}

	public void setSelectedFiscalYear(FiscalYearEntity selectedFiscalYear) {
		this.selectedFiscalYear = selectedFiscalYear;
	}

	public Set<FiscalYearEntity> getCompanyFiscalYearSet() {
		return companyFiscalYearSet;
	}

	public void setCompanyFiscalYearSet(Set<FiscalYearEntity> companyFiscalYearSet) {
		this.companyFiscalYearSet = companyFiscalYearSet;
	}

	public DefItemEntity getVendor() {
		return vendor;
	}

	public void setVendor(DefItemEntity vendor) {
		this.vendor = vendor;
	}

}
