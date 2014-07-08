package org.abacus.catering.shared.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.abacus.common.shared.entity.DynamicEntity;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.entity.DefValueEntity;
import org.abacus.organization.shared.entity.OrganizationEntity;
import org.abacus.transaction.shared.entity.StkDocumentEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@SuppressWarnings("serial")
@Table(name = "cat_menu")
public class CatMenuEntity extends DynamicEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id", nullable = false)
	private OrganizationEntity organization;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "meal_id", nullable = false)
	private DefValueEntity meal;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "document_id", nullable = true)
	private StkDocumentEntity document;

	@Temporal(TemporalType.DATE)
	@Column(name = "menu_date", nullable = false)
	private Date menuDate;

	@Enumerated(EnumType.STRING)
	@Column(name = "menu_status", nullable = false, length=30)
	private EnumList.MenuStatusEnum menuStatus;

	@Column(name = "count_prepare", precision = 10, scale = 3)
	private BigDecimal countPrepare;

	@Column(name = "count_spend", precision = 10, scale = 3)
	private BigDecimal countSpend;

	@OneToMany(mappedBy = "menu", fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	private Set<CatMenuItemEntity> menuItemSet;

	@Column(name = "cancel_reason", nullable = true)
	private String cancelReason;

	public List<CatMenuItemEntity> getMenuItemList() {
		return new ArrayList<CatMenuItemEntity>(menuItemSet);
	}

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

	public BigDecimal getCountPrepare() {
		return countPrepare;
	}

	public void setCountPrepare(BigDecimal countPrepare) {
		this.countPrepare = countPrepare;
	}

	public BigDecimal getCountSpend() {
		return countSpend;
	}

	public void setCountSpend(BigDecimal countSpend) {
		this.countSpend = countSpend;
	}

	public Set<CatMenuItemEntity> getMenuItemSet() {
		return menuItemSet;
	}

	public void setMenuItemSet(Set<CatMenuItemEntity> menuItemSet) {
		this.menuItemSet = menuItemSet;
	}

	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

	public StkDocumentEntity getDocument() {
		return document;
	}

	public void setDocument(StkDocumentEntity document) {
		this.document = document;
	}

}
