package org.abacus.definition.shared.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.abacus.common.shared.entity.StaticEntity;

@Entity
@Table(name = "def_type")
@SuppressWarnings("serial")
public class DefTypeEntity extends StaticEntity {

	@Column(name = "name", nullable = false)
	private String name;

//	DefConstant.DefTypeGroupEnum
	@Column(name = "group_enum", nullable = false)
	private String group; 
 
	@Column(name = "level", nullable = false)
	private Integer level = 1; 

	@Column(name = "trtype", nullable = false)
	private Integer trtype = 0; 

	public DefTypeEntity() {
	}

	public DefTypeEntity(String id) {
		this.setId(id);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getTrtype() {
		return trtype;
	}

	public void setTrtype(Integer trtype) {
		this.trtype = trtype;
	}

}
