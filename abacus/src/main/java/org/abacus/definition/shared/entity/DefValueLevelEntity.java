package org.abacus.definition.shared.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.abacus.common.shared.entity.StaticEntity;

@Entity
@Table(name = "def_value_level")
@SuppressWarnings("serial")
public class DefValueLevelEntity extends StaticEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "value_id", nullable = false)
	private DefValueEntity value;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id", nullable = false)
	private DefValueEntity parent;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type_id", nullable = false)
	private DefTypeEntity type;

	@Column(name = "level_asc", nullable = false)
	private Integer level_asc;

	@Column(name = "level_desc", nullable = false)
	private Integer level_desc;
	
	public DefValueEntity getValue() {
		return value;
	}

	public void setValue(DefValueEntity value) {
		this.value = value;
	}

	public DefValueEntity getParent() {
		return parent;
	}

	public void setParent(DefValueEntity parent) {
		this.parent = parent;
	}

	public DefTypeEntity getType() {
		return type;
	}

	public void setType(DefTypeEntity type) {
		this.type = type;
	}

	public Integer getLevel_asc() {
		return level_asc;
	}

	public void setLevel_asc(Integer level_asc) {
		this.level_asc = level_asc;
	}

	public Integer getLevel_desc() {
		return level_desc;
	}

	public void setLevel_desc(Integer level_desc) {
		this.level_desc = level_desc;
	}

}
