package org.abacus.user.shared.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.abacus.common.shared.entity.DynamicEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@SuppressWarnings("serial")
@Table(name = "sec_group")
public class SecGroupEntity extends DynamicEntity {

	@Column(name = "name", nullable = false)
	private String name;

	@OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
	@Fetch(FetchMode.SELECT)
	private List<SecGroupAuthorityEntity> groupAuthorityList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SecGroupAuthorityEntity> getGroupAuthorityList() {
		return groupAuthorityList;
	}

	public void setGroupAuthorityList(
			List<SecGroupAuthorityEntity> groupAuthorityList) {
		this.groupAuthorityList = groupAuthorityList;
	}

}
