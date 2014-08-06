package org.abacus.common.shared.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Immutable;

@SuppressWarnings("serial")
@MappedSuperclass
@Immutable
public class ViewEntity implements RootEntity {

	@Id
	@Column(name = "id")
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


}
