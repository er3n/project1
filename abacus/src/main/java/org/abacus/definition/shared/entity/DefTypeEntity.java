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

	@Override
	public String toString(){
		return this.isNew()?"New:"+super.toString():(this.getId());
	}
	
	public boolean isNew() {
		return (this.getId()==null || this.getId().equals("."));
	}

	public DefTypeEntity() {
		this.setId(".");
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

}
