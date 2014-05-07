package org.abacus.definition.shared.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.abacus.common.shared.entity.DynamicEntity;
import org.abacus.organization.shared.entity.OrganizationEntity;

@Entity
@Table(name = "def_param_answer")
@SuppressWarnings("serial")
public class DefParamAnswerEntity extends DynamicEntity {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "param_id", nullable = false)
	private DefParamEntity param;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id", nullable = false)
	private OrganizationEntity organization;

	@Column(name = "value", nullable = false)
	private String value;

	public DefParamAnswerEntity(){
	}

	public DefParamEntity getParam() {
		return param;
	}

	public void setParam(DefParamEntity param) {
		this.param = param;
	}

	public OrganizationEntity getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationEntity organization) {
		this.organization = organization;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
