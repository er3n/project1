package org.abacus.common.shared.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@SuppressWarnings("serial")
@MappedSuperclass
public class StaticEntity implements RootEntity {

	@Id
	@Column(name = "id")
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof StaticEntity)) {
			return false;
		}
		final StaticEntity other = (StaticEntity) obj;
		if (this.id != null && other.id != null) {
			if (!this.id.equals(other.id)) {
				return false;
			}
		}
		return true;
	}

}
