package org.abacus.definition.shared.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.abacus.common.shared.entity.DynamicEntity;
import org.abacus.definition.shared.constant.EnumList;

@Entity
@Table(name = "def_item_value")
@SuppressWarnings("serial")
public class DefItemValueEntity extends DynamicEntity {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "item_id", nullable = false)
	private DefItemEntity item;

	@Enumerated(EnumType.STRING)
	@Column(name = "value_type_enum", nullable = false, length=30)
	private EnumList.DefTypeEnum typeEnum;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "value_id", nullable = false)
	private DefValueEntity value;

	public DefItemValueEntity(){
	}

	public DefItemEntity getItem() {
		return item;
	}

	public void setItem(DefItemEntity item) {
		this.item = item;
	}

	public DefValueEntity getValue() {
		return value;
	}

	public void setValue(DefValueEntity value) {
		this.value = value;
	}

	public EnumList.DefTypeEnum getTypeEnum() {
		return typeEnum;
	}

	public void setTypeEnum(EnumList.DefTypeEnum typeEnum) {
		this.typeEnum = typeEnum;
	}

}
