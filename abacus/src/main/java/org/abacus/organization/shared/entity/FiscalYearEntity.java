package org.abacus.organization.shared.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.abacus.common.shared.entity.StaticEntity;

@Entity
@SuppressWarnings("serial")
@Table(name = "org_fiscal_year")
public class FiscalYearEntity extends StaticEntity {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "organization_id", nullable = false)
	private OrganizationEntity organization;

	@Column(name = "name", nullable = false)
	private String name;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date_start", nullable = true)
	private Date dateStart;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_finish", nullable = true)
	private Date dateFinish;
	
	public FiscalYearEntity() {
	}

	public FiscalYearEntity(String id) {
		super.id = id;
	}

	public OrganizationEntity getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationEntity organization) {
		this.organization = organization;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateFinish() {
		return dateFinish;
	}

	public void setDateFinish(Date dateFinish) {
		this.dateFinish = dateFinish;
	}


}
