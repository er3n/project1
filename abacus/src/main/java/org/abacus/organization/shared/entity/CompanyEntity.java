package org.abacus.organization.shared.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.abacus.common.shared.entity.StaticEntity;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.constant.ISelectionEnum;

@Entity
@SuppressWarnings("serial")
@Table(name = "org_company")
public class CompanyEntity extends StaticEntity {

	@Column(name = "name")
	private String name;

//	EnumList.OrgCompanyLevelEnum
	@Column(name = "level_enum", nullable = false)
	private String level = "L0";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private CompanyEntity parent;

	public ISelectionEnum getLevelEnum(){
		if (this.level!=null){
			return EnumList.OrgCompanyLevelEnum.valueOf(this.level);
		}
		return null;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public CompanyEntity getParent() {
		return parent;
	}

	public void setParent(CompanyEntity parent) {
		this.parent = parent;
	}

}
