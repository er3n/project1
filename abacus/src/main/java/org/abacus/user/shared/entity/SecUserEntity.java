package org.abacus.user.shared.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.abacus.common.shared.entity.StaticEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "sec_user")
@SuppressWarnings("serial")
public class SecUserEntity extends StaticEntity {

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "is_active", nullable = false)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean active;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	@Fetch(FetchMode.SELECT)
	private Set<SecUserCompanyEntity> companyList;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	@Fetch(FetchMode.SELECT)
	private Set<SecGroupMemberEntity> groupMemberList;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Set<SecUserCompanyEntity> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(Set<SecUserCompanyEntity> companyList) {
		this.companyList = companyList;
	}

	public Set<SecGroupMemberEntity> getGroupMemberList() {
		return groupMemberList;
	}

	public void setGroupMemberList(Set<SecGroupMemberEntity> groupMemberList) {
		this.groupMemberList = groupMemberList;
	}

}