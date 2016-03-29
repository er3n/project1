package org.abacus.definition.shared.holder;

import java.io.Serializable;
import java.util.List;

import org.abacus.definition.shared.constant.EnumList.DefItemClassEnum;
import org.abacus.definition.shared.constant.EnumList.DefTypeEnum;
import org.abacus.definition.shared.entity.DefItemEntity;
import org.abacus.organization.shared.entity.OrganizationEntity;

@SuppressWarnings("serial")
public class ItemSearchCriteria implements Serializable {

	private OrganizationEntity organization;
	private DefTypeEnum itemType;
	private DefItemClassEnum itemClass;
	private String code;
	private String codeLike;
	private String nameLike;
	private String categoryNameLike;
	private Boolean status;
	private String filterTypeDesc;
	private List<DefItemEntity> itemList;

	private Integer first;
	private Integer pageSize;

	public ItemSearchCriteria(OrganizationEntity organization, DefTypeEnum itemType,
			DefItemClassEnum itemClass) {
		this.itemType = itemType;
		this.itemClass = itemClass;
		this.organization = organization;
	}

	public OrganizationEntity getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationEntity organization) {
		this.organization = organization;
	}

	public DefTypeEnum getItemType() {
		return itemType;
	}

	public void setItemType(DefTypeEnum itemType) {
		this.itemType = itemType;
	}

	public DefItemClassEnum getItemClass() {
		return itemClass;
	}

	public void setItemClass(DefItemClassEnum itemClass) {
		this.itemClass = itemClass;
	}

	public Integer getFirst() {
		return first;
	}

	public void setFirst(Integer first) {
		this.first = first;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public String getCodeLike() {
		return codeLike;
	}

	public void setCodeLike(String codeLike) {
		this.codeLike = codeLike;
	}

	public String getNameLike() {
		return nameLike;
	}

	public void setNameLike(String nameLike) {
		this.nameLike = nameLike;
	}

	public String getCategoryNameLike() {
		return categoryNameLike;
	}

	public void setCategoryNameLike(String categoryNameLike) {
		this.categoryNameLike = categoryNameLike;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getFilterTypeDesc() {
		return filterTypeDesc;
	}

	public void setFilterTypeDesc(String filterTypeDesc) {
		this.filterTypeDesc = filterTypeDesc;
	}

	public List<DefItemEntity> getItemList() {
		return itemList;
	}

	public void setItemList(List<DefItemEntity> itemList) {
		this.itemList = itemList;
	}

}
