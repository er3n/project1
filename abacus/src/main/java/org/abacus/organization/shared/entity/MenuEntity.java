package org.abacus.organization.shared.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.abacus.common.shared.entity.DynamicEntity;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.entity.DefValueEntity;

@Entity
@SuppressWarnings("serial")
@Table(name = "org_menu")
public class MenuEntity extends DynamicEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id", nullable = false)
	private OrganizationEntity organization;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "meal_id", nullable = false)
	private DefValueEntity meal;

	@Temporal(TemporalType.DATE)
	@Column(name = "menu_date", nullable = false)
	private Date menuDate;

	@Enumerated(EnumType.STRING)
	@Column(name = "menu_status", nullable = false)
	private EnumList.MenuStatusEnum menuStatus;

	@Column(name = "count_prepare", nullable = false)
	private Integer countPrepare;

	@Column(name = "count_spend", nullable = false)
	private Integer countSpend;

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

	public Date getMenuDate() {
		return menuDate;
	}

	public void setMenuDate(Date menuDate) {
		this.menuDate = menuDate;
	}

	public EnumList.MenuStatusEnum getMenuStatus() {
		return menuStatus;
	}

	public void setMenuStatus(EnumList.MenuStatusEnum menuStatus) {
		this.menuStatus = menuStatus;
	}

	public Integer getCountPrepare() {
		return countPrepare;
	}

	public void setCountPrepare(Integer countPrepare) {
		this.countPrepare = countPrepare;
	}

	public Integer getCountSpend() {
		return countSpend;
	}

	public void setCountSpend(Integer countSpend) {
		this.countSpend = countSpend;
	}

}