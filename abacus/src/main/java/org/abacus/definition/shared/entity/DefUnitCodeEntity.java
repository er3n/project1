package org.abacus.definition.shared.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.abacus.common.shared.entity.DynamicEntity;

@Entity
@Table(name = "def_unit_code")
@SuppressWarnings("serial")
public class DefUnitCodeEntity extends DynamicEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_group_id", nullable = false)
	private DefUnitGroupEntity unitGroup;

	@Column(name = "code", nullable = false)
	private String code; 

	@Column(name = "name", nullable = false)
	private String name;

	public DefUnitCodeEntity(){
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

	public DefUnitGroupEntity getUnitGroup() {
		return unitGroup;
	}

	public void setUnitGroup(DefUnitGroupEntity unitGroup) {
		this.unitGroup = unitGroup;
	}

}
