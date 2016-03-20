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
import org.abacus.definition.shared.entity.DefItemEntity;
import org.abacus.organization.shared.entity.FiscalYearEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@SuppressWarnings("serial")
@Table(name = "cat_menu")
public class CatMenuEntity extends DynamicEntity {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fiscal_year_id", nullable = false)
	private FiscalYearEntity fiscalYear;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "menu_info_id", nullable = false)
	private CatMenuInfoEntity menuInfo;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "person_id", nullable = false)
	private DefItemEntity person;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "menu_date", nullable = false)
	private Date menuDate;

	@Enumerated(EnumType.STRING)
	@Column(name = "menu_status", nullable = false, length=30)
	private EnumList.MenuStatusEnum menuStatus;

	@Column(name = "count_prepare", nullable = false, precision = 2, scale = 0)
	private BigDecimal countPrepare = BigDecimal.ZERO;


	@OneToMany(mappedBy = "menu", fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	private Set<CatMenuItemEntity> menuItemSet;


	public List<CatMenuItemEntity> getMenuItemList() {
		return new ArrayList<CatMenuItemEntity>(menuItemSet);
	}
 
	public CatMenuInfoEntity getMenuInfo() {
		return menuInfo;
	}

	public void setMenuInfo(CatMenuInfoEntity menuInfo) {
		this.menuInfo = menuInfo;
	}

	public DefItemEntity getPerson() {
		return person;
	}

	public void setPerson(DefItemEntity person) {
		this.person = person;
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
		this.countPrepare = countPrepare!=null?countPrepare:BigDecimal.ZERO;
	}

	public Set<CatMenuItemEntity> getMenuItemSet() {
		return menuItemSet;
	}

	public void setMenuItemSet(Set<CatMenuItemEntity> menuItemSet) {
		this.menuItemSet = menuItemSet;
	}

	public FiscalYearEntity getFiscalYear() {
		return fiscalYear;
	}

	public void setFiscalYear(FiscalYearEntity fiscalYear) {
		this.fiscalYear = fiscalYear;
	}


}
