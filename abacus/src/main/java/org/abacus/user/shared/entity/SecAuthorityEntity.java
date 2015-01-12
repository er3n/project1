
package org.abacus.user.shared.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.abacus.common.shared.entity.StaticEntity;

@SuppressWarnings("serial")
@Entity
@Table(name = "sec_authority")
public class SecAuthorityEntity extends StaticEntity {

	@Column(name = "code", nullable = false)
	private String code;

	@Column(name = "name", nullable = false)
	private String name;

	public Boolean isSubMenu(){
		return code.length()==1;
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
