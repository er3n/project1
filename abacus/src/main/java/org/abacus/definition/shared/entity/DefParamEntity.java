package org.abacus.definition.shared.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.abacus.common.shared.entity.StaticEntity;
import org.abacus.definition.shared.constant.EnumList;

@Entity
@Table(name = "def_param")
@SuppressWarnings("serial")
public class DefParamEntity extends StaticEntity {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "type_id", nullable = false)
	private DefTypeEntity type;

	@Column(name = "code", nullable = false, length=30)
	private String code;

	@Column(name = "name", nullable = false)
	private String name;

	public DefParamEntity(){
	}
	
	public EnumList.DefParameterEnum getParameterEnum(){
		if (this.getId()==null){
			return null;
		}
		return EnumList.DefParameterEnum.valueOf(this.getId());
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
