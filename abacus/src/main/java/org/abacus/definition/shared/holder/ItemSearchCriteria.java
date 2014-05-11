package org.abacus.definition.shared.holder;

import java.io.Serializable;

import org.abacus.definition.shared.constant.EnumList.DefItemClassEnum;
import org.abacus.definition.shared.constant.EnumList.DefTypeEnum;

@SuppressWarnings("serial")
public class ItemSearchCriteria implements Serializable {

	private String organization;
	private DefTypeEnum itemType;
	private DefItemClassEnum itemClass;
	private String codeLike;
	private String nameLike;
	private String categoryCodeLike;
	private Boolean status;

	private Integer first;
	private Integer pageSize;

	public ItemSearchCriteria(String organization, DefTypeEnum itemType,
			DefItemClassEnum itemClass) {
		this.itemType = itemType;
		this.itemClass = itemClass;
		this.organization = organization;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
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

	public String getCategoryCodeLike() {
		return categoryCodeLike;
	}

	public void setCategoryCodeLike(String categoryCodeLike) {
		this.categoryCodeLike = categoryCodeLike;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

}