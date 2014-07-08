package org.abacus.catering.shared.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.abacus.common.shared.entity.DynamicEntity;
import org.abacus.definition.shared.entity.DefValueEntity;
import org.abacus.organization.shared.entity.OrganizationEntity;

@Entity
@SuppressWarnings("serial")
@Table(name = "cat_meal_filter")
public class CatMealFilterEntity extends DynamicEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id", nullable = false)
	private OrganizationEntity organization;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "meal_id", nullable = false)
	private DefValueEntity meal;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_start", nullable = false)
	private Date dateStart;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_finish", nullable = false)
	private Date dateFinish;

	@Column(name = "description", nullable = true)
	private String description;

	@Column(name = "count_prepare", nullable = false, precision = 10, scale = 3)
	private BigDecimal countPrepare;
	
	public OrganizationEntity getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationEntity organization) {
		this.organization = organization;
	}

	public DefValueEntity getMeal() {
		return meal;
	}

	public void setMeal(DefValueEntity meal) {
		this.meal = meal;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public BigDecimal getCountPrepare() {
		return countPrepare;
	}

	public void setCountPrepare(BigDecimal countPrepare) {
		this.countPrepare = countPrepare;
	}

	
}
