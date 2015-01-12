package org.abacus.common.shared.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Immutable;

@SuppressWarnings("serial")
@MappedSuperclass
@Immutable
public class ViewEntity implements Serializable, Cloneable, Comparable<ViewEntity>  {

	@Id
	@Column(name = "id")
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int compareTo(ViewEntity o) {
		return this.id.compareTo(o.id);
	}

}
