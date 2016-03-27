package org.abacus.common.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.abacus.organization.shared.entity.FiscalYearEntity;
import org.abacus.organization.shared.entity.OrganizationEntity;
import org.abacus.user.shared.entity.SecUserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

@SuppressWarnings("serial")
public class SecUser implements UserDetails {

	private SecUserEntity userEntity;

	private List<String> authorityNames;

	private List<OrganizationEntity> organizationList;
	private Set<FiscalYearEntity> companyFiscalYearSet;

	private OrganizationEntity selectedOrganization;
	private FiscalYearEntity selectedFiscalYear;

	public SecUser(SecUserEntity userEntity){
		this.userEntity = userEntity;
	}
	
	public void initApp(List<OrganizationEntity> organizationList, OrganizationEntity selectedOrganization, Set<FiscalYearEntity> companyFiscalYearSet, FiscalYearEntity defaultFiscalYear) {
		setOrganizationList(organizationList);
		setSelectedOrganization(selectedOrganization);
		setCompanyFiscalYearSet(companyFiscalYearSet);
		setSelectedFiscalYear(defaultFiscalYear);
	}

	@Override
	public String toString(){
		return userEntity.getId();
	}
	
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((this.userEntity.getId() == null) ? 0 : this.userEntity.getId().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object oth) {
		if (this == oth)
			return true;
		if (oth == null)
			return false;
		if (getClass() != oth.getClass())
			return false;
		SecUser other = (SecUser) oth;
		if (this.userEntity.getId() == null) {
			if (other.userEntity.getId() != null)
				return false;
		} else if (!this.userEntity.getId().equals(other.userEntity.getId()))
			return false;
		return true;
	}

	@Override
	public String getUsername() {
		return this.userEntity.getId();
	}

	@Override
	public String getPassword() {
		return this.userEntity.getPassword();
	}

	@Override
	public boolean isEnabled() {
		return this.userEntity.getActive();
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

	public List<String> getAuthorityNames() {
		return authorityNames;
	}

	public void setAuthorityNames(List<String> authorityNames) {
		this.authorityNames = authorityNames;
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

	public SecUserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(SecUserEntity userEntity) {
		this.userEntity = userEntity;
	}

}
