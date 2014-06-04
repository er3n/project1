package org.abacus.definition.shared.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.abacus.common.shared.entity.StaticEntity;
import org.abacus.definition.shared.constant.EnumList;
import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "def_type")
@SuppressWarnings("serial")
public class DefTypeEntity extends StaticEntity {

	@Column(name = "name", nullable = false)
	private String name;
 
	@Column(name = "level", nullable = false)
	private Integer level = 1; 

	@Column(name = "tr_state_typ", nullable = false)
	@Range(min=-1, max=+1)
	private Integer trStateTyp;

	public DefTypeEntity() {
	}

	public EnumList.DefTypeEnum getTypeEnum(){
		if (this.getId()==null){
			return null;
		}
		return EnumList.DefTypeEnum.valueOf(this.getId());
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

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getTrType() {
		return (trStateTyp>0?"(+)":(trStateTyp<0?"(-)":"."));
	}

	public Integer getTrStateTyp() {
		return trStateTyp;
	}

	public void setTrStateTyp(Integer trStateTyp) {
		this.trStateTyp = trStateTyp;
	}

}
