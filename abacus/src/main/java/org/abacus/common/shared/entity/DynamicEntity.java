package org.abacus.common.shared.entity;


import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@MappedSuperclass
public class DynamicEntity implements Serializable, Cloneable, Comparable<DynamicEntity> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_id")
	@SequenceGenerator(name = "seq_id", sequenceName = "seq_id", allocationSize = 1)
	@Column(name = "id")
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_created", nullable = true)
	private Date dateCreated = new Date();

	@Column(name = "user_created", nullable = true, length=30)
	private String userCreated;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_updated", nullable = true)
	private Date dateUpdated = new Date();

	@Column(name = "user_updated", nullable = true, length=30)
	private String userUpdated;

	@Version
	@Column(name = "version", nullable = false)
	private Integer version;

	@Transient
	private DynamicEntity point;
	

	public DynamicEntity getPoint() {
		return point;
	}

	public void setPoint() {
		try {
			this.point = (DynamicEntity) this.clone();
		} catch (CloneNotSupportedException e) {
			this.point = null;
			e.printStackTrace();
		}
	}
	
	public void updateHook(String userUpdated) {
		this.dateUpdated = Calendar.getInstance().getTime();
		this.userUpdated = userUpdated;
	}

	public void createHook(String userCreated) {
		this.dateCreated = Calendar.getInstance().getTime();
		this.userCreated = userCreated;
	}
	
	public void cleanHook(){
		this.id = null;
		this.dateCreated = null;
		this.userCreated = null;
		this.dateUpdated = null;
		this.userUpdated = null;
		this.version = null;
	}
	
	public void cleanCreateHook(String userCreated){
		this.cleanHook();
		this.createHook(userCreated);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof DynamicEntity)) {
			return false;
		}
		final DynamicEntity other = (DynamicEntity) obj;
		if (this.id != null && other.id != null) {
			if (!this.id.equals(other.id)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString(){
		if (this.id!=null){
			return this.getClass().getSimpleName()+":"+this.id;
		}
		return this.getClass().getSimpleName()+":NEW";
	}

	public boolean isNew() {
		return (this.getId()==null);
	}

	@Override
	public int compareTo(DynamicEntity o) {
		return this.id.compareTo(o.id);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getUserCreated() {
		return userCreated;
	}

	public void setUserCreated(String userCreated) {
		this.userCreated = userCreated;
	}

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public String getUserUpdated() {
		return userUpdated;
	}

	public void setUserUpdated(String userUpdated) {
		this.userUpdated = userUpdated;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
