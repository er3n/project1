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

	@Column(name = "group_id", nullable = false)
	private String group = DefConstant.GroupEnum.V.name(); //TODO : tanim degisecek
 
	@Column(name = "level", nullable = false)
	private Integer level = 1; //TODO : tanim degisecek

	@Column(name = "trtype", nullable = false)
	private Integer trtype = 0; //TODO : stok/finans degisecek

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
