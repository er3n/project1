package org.abacus.definition.shared.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	
}
