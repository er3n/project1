package org.abacus.user.shared.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.abacus.common.shared.entity.StaticEntity;

@Entity
@SuppressWarnings("serial")
@Table(name = "COMPANY")
public class CompanyEntity extends StaticEntity {

	@Column(name = "LEVEL", nullable = false)
	private Integer level;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_ID")
	private CompanyEntity parent;

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public CompanyEntity getParent() {
		return parent;
	}

	public void setParent(CompanyEntity parent) {
		this.parent = parent;
	}

}
