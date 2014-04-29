package org.abacus.definition.shared.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.abacus.common.shared.entity.StaticEntity;

@Entity
@Table(name = "def_state")
@SuppressWarnings("serial")
public class DefStateEntity extends StaticEntity {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "type_id", nullable = false)
	private DefTypeEntity type;

	@Column(name = "code", nullable = false)
	private String code;

	@Column(name = "name", nullable = false)
	private String name;

	@Override
	public String toString(){
		return this.isNew()?"New:"+super.toString():(this.getType().getId()+":"+this.getId());
	}
	
	public boolean isNew() {
		return (this.getId()==null);
	}

	public DefStateEntity(){
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

}
