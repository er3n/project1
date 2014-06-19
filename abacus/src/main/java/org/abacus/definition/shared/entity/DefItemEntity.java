package org.abacus.definition.shared.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.abacus.common.shared.entity.DynamicEntity;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.organization.shared.entity.OrganizationEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "def_item")
@SuppressWarnings("serial")
public class DefItemEntity extends DynamicEntity {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "type_id", nullable = false)
	@Fetch(FetchMode.JOIN)
	private DefTypeEntity type;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id", nullable = false)
	private OrganizationEntity organization;

	@Column(name = "code", nullable = false)
	private String code;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "is_active", nullable = false)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean active = true;

	@Enumerated(EnumType.STRING)
	@Column(name = "class_enum", nullable = true)
	private EnumList.DefItemClassEnum itemClass;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id", nullable = false)
	private DefValueEntity category;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_group_id", nullable = false)
	private DefUnitGroupEntity unitGroup;

	@OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
	@Fetch(FetchMode.SELECT)
	private Set<DefItemUnitEntity> itemUnitSet;

	@OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
	@Fetch(FetchMode.SELECT)
	private Set<DefItemProductEntity> itemProductSet;

	public DefItemEntity() {
	}

	public DefTypeEntity getType() {
		return type;
	}

	public void setType(DefTypeEntity type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public OrganizationEntity getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationEntity organization) {
		this.organization = organization;
	}

	public EnumList.DefItemClassEnum getItemClass() {
		return itemClass;
	}

	public void setItemClass(EnumList.DefItemClassEnum itemClass) {
		this.itemClass = itemClass;
	}

	public DefValueEntity getCategory() {
		return category;
	}

	public void setCategory(DefValueEntity category) {
		this.category = category;
	}

	public DefUnitGroupEntity getUnitGroup() {
		return unitGroup;
	}

	public void setUnitGroup(DefUnitGroupEntity unitGroup) {
		this.unitGroup = unitGroup;
	}

	public Set<DefItemUnitEntity> getItemUnitSet() {
		return itemUnitSet;
	}

	public void setItemUnitSet(Set<DefItemUnitEntity> itemUnitSet) {
		this.itemUnitSet = itemUnitSet;
	}

	public Set<DefItemProductEntity> getItemProductSet() {
		return itemProductSet;
	}

	public void setItemProductSet(Set<DefItemProductEntity> itemProductSet) {
		this.itemProductSet = itemProductSet;
	}

}
