package org.abacus.definition.shared.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.abacus.common.shared.entity.StaticEntity;

@Entity
@Table(name = "def_type")
@SuppressWarnings("serial")
public class DefTypeEntity extends StaticEntity {

	@Column(name = "name", nullable = false)
	private String name;

	@Transient
	private boolean isNew=false;
	
	public boolean getIsNew() {
		return isNew;
	}

	public void setIsNew(boolean isNew) {
		this.isNew = isNew;
	}

	public DefTypeEntity() {
	}

	public DefTypeEntity(Boolean isNew) {
		setIsNew(isNew);
	}
	
	public DefTypeEntity(String id) {
		setId(id);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
