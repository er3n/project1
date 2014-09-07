package org.abacus.user.shared.holder;

import java.io.Serializable;
import java.util.List;

import org.abacus.definition.shared.constant.EnumList;
import org.abacus.organization.shared.entity.OrganizationEntity;
import org.abacus.user.shared.entity.SecGroupEntity;
import org.abacus.user.shared.entity.SecUserEntity;

public class SearchUserCriteria implements Serializable {

	private boolean isRootUser;
	private SecUserEntity user;
	private List<SecGroupEntity> groupList;
	private OrganizationEntity organization;
	private EnumList.Hierachy hierarchy;
	
	public String getUsername(){
		return (user==null?null:user.getId());
	}
	
	public SecUserEntity getUser() {
		return user;
	}

	public void setUser(SecUserEntity user) {
		this.user = user;
	}

	public List<SecGroupEntity> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<SecGroupEntity> groupList) {
		this.groupList = groupList;
	}

	public OrganizationEntity getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationEntity organization) {
		this.organization = organization;
	}

	public EnumList.Hierachy getHierarchy() {
		return hierarchy;
	}

	public void setHierarchy(EnumList.Hierachy hierarchy) {
		this.hierarchy = hierarchy;
	}

	public boolean getIsRootUser() {
		return isRootUser;
	}

	public void setIsRootUser(boolean isRootUser) {
		this.isRootUser = isRootUser;
	}

}
