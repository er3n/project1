package org.abacus.user.shared.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.abacus.common.shared.entity.StaticEntity;
import org.abacus.definition.shared.entity.DefItemEntity;
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
	private Set<SecUserOrganizationEntity> organizationList;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	@Fetch(FetchMode.SELECT)
	private Set<SecUserGroupEntity> userGroupList;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "vendor_id", nullable = true)
	private DefItemEntity vendor;

	@Column(name = "organization_root", nullable = true, length=2)
	private String organizationRoot;

	public SecUserEntity(){
	}
		
	public SecUserEntity(String id){
		this.id = id;
	}
	
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

	public Set<SecUserOrganizationEntity> getOrganizationList() {
		return organizationList;
	}

	public void setOrganizationList(Set<SecUserOrganizationEntity> organizationList) {
		this.organizationList = organizationList;
	}

	public Set<SecUserGroupEntity> getUserGroupList() {
		return userGroupList;
	}

	public void setUserGroupList(Set<SecUserGroupEntity> sertGroupList) {
		this.userGroupList = sertGroupList;
	}

	public DefItemEntity getVendor() {
		return vendor;
	}

	public void setVendor(DefItemEntity vendor) {
		this.vendor = vendor;
	}

	public String getOrganizationRoot() {
		return organizationRoot;
	}

	public void setOrganizationRoot(String organizationRoot) {
		this.organizationRoot = organizationRoot;
	}

}