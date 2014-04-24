package org.abacus.common.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

@SuppressWarnings("serial")
public class SecUser implements UserDetails {

	private String username;
	private String password;
	private boolean isActive;

	private List<String> authorityNames;

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

}
