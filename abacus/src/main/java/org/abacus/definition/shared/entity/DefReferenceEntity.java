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
import org.abacus.organization.shared.entity.OrganizationEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "def_reference")
@SuppressWarnings("serial")
public class DefReferenceEntity extends DynamicEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id", nullable = false)
	private OrganizationEntity organization;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "type_id", nullable = false)
	@Fetch(FetchMode.JOIN)
	private DefTypeEntity type;

	@Enumerated(EnumType.STRING)
	@Column(name = "ref_type_id", nullable = false, length=30)
	private EnumList.DefTypeEnum refType;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ref_value_id", nullable = true)
	@Fetch(FetchMode.JOIN)
	private DefValueEntity refValue;
	
	public DefReferenceEntity(){
	}

	public DefTypeEntity getType() {
		return type;
	}

	public void setType(DefTypeEntity type) {
		this.type = type;
	}

	public EnumList.DefTypeEnum getRefType() {
		return refType;
	}

	public void setRefType(EnumList.DefTypeEnum refType) {
		this.refType = refType;
	}

	public DefValueEntity getRefValue() {
		return refValue;
	}

	public void setRefValue(DefValueEntity refValue) {
		this.refValue = refValue;
	}

	public OrganizationEntity getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationEntity organization) {
		this.organization = organization;
	}

}
