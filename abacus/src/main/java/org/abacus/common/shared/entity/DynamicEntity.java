package org.abacus.common.shared.entity;

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

@SuppressWarnings("serial")
@MappedSuperclass
public class DynamicEntity implements RootEntity {


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_id")
	@SequenceGenerator(name = "seq_id", sequenceName = "seq_id", allocationSize = 1)	
	@Column(name = "id")
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_created", nullable = true)
	private Date dateCreated = new Date();

	@Column(name = "user_created", nullable = true)
	private String userCreated;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_updated", nullable = true)
	private Date dateUpdated = new Date();

	@Column(name = "user_updates", nullable = true)
	private String userUpdated;

	@Version
	@Column(name = "version", nullable = false)
	private int version = 0;

	public void updateHook(String userUpdated) {
		this.dateUpdated = Calendar.getInstance().getTime();
		this.userUpdated = userUpdated;
	}

	public void createHook(String userCreated) {
		this.dateCreated = Calendar.getInstance().getTime();
		this.userCreated = userCreated;
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
			if (this.id != other.id) {
				return false;
			}
		}
		return true;
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

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}
