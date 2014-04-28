package org.abacus.definition.shared.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.abacus.common.shared.entity.DynamicEntity;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "def_value")
@SuppressWarnings("serial")
public class DefValueEntity extends DynamicEntity {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "type_id", nullable = false)
	private DefTypeEntity type;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "parent_id", nullable = true)
	private DefValueEntity parent; 

	@Column(name = "code", nullable = false)
	private String code; 

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "is_active", nullable = false)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean active = true;
	
	@Transient
	private boolean isNew=false;
	
	public boolean getIsNew() {
		return isNew;
	}

	public void setIsNew(boolean isNew) {
		this.isNew = isNew;
	}

	public DefValueEntity(){
	}
	
	public DefValueEntity(Long id){
		this.setId(id);
	}

	public DefValueEntity(Long id,String code, String ack){
		this.setId(id);
		this.code = code;
		this.name = ack;
	}

	public DefTypeEntity getType() {
		return type;
	}

	public void setType(DefTypeEntity type) {
		this.type = type;
	}

	public DefValueEntity getParent() {
		return parent;
	}

	public void setParent(DefValueEntity parent) {
		this.parent = parent;
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
	
	
}
