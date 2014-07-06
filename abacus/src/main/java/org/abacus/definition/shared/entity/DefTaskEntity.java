package org.abacus.definition.shared.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.abacus.common.shared.entity.DynamicEntity;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.constant.EnumList.DefTypeEnum;
import org.abacus.organization.shared.entity.OrganizationEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "def_task")
@SuppressWarnings("serial")
public class DefTaskEntity extends DynamicEntity {

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

	@Enumerated(EnumType.STRING)
	@Column(name = "item_type_document", nullable = true)
	private EnumList.DefTypeEnum itemTypeDocument;

	@Enumerated(EnumType.STRING)
	@Column(name = "item_type_detail", nullable = true)
	private EnumList.DefTypeEnum itemTypeDetail;

	@Column(name = "is_active", nullable = false)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean active = true;
	
	public DefTaskEntity(){
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

	public EnumList.DefTypeEnum getItemTypeDocument() {
		return itemTypeDocument;
	}

	public void setItemTypeDocument(EnumList.DefTypeEnum itemTypeDocument) {
		this.itemTypeDocument = itemTypeDocument;
	}

	public EnumList.DefTypeEnum getItemTypeDetail() {
		return itemTypeDetail;
	}

	public void setItemTypeDetail(EnumList.DefTypeEnum itemTypeDetail) {
		this.itemTypeDetail = itemTypeDetail;
	}
	
}
