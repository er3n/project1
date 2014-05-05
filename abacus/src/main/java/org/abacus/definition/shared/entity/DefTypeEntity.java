package org.abacus.definition.shared.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.abacus.common.shared.entity.StaticEntity;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.constant.SelectionEnum;

@Entity
@Table(name = "def_type")
@SuppressWarnings("serial")
public class DefTypeEntity extends StaticEntity {

	@Column(name = "name", nullable = false)
	private String name;

	@Enumerated(EnumType.STRING)
	@Column(name = "group_enum", nullable = false)
	private EnumList.DefTypeGroupEnum typeGroup; 
 
	@Transient
	private SelectionEnum transientGroup; 
	
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

	public EnumList.DefTypeGroupEnum getTypeGroup() {
		return typeGroup;
	}

	public void setTypeGroup(EnumList.DefTypeGroupEnum group) {
		this.typeGroup = group;
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

	public SelectionEnum getTransientGroup() {
		return new SelectionEnum(typeGroup);
	}

	public void setTransientGroup(SelectionEnum g) {
		this.typeGroup = EnumList.DefTypeGroupEnum.valueOf(g.name());
	}

}
