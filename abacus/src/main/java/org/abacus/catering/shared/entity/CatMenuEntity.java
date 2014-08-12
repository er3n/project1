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
import org.abacus.organization.shared.entity.FiscalYearEntity;
import org.abacus.transaction.shared.entity.FinDocumentEntity;
import org.abacus.transaction.shared.entity.StkDocumentEntity;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "document_stk_id", nullable = true)
	private StkDocumentEntity stkDocument;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "document_fin_id", nullable = true)
	private FinDocumentEntity finDocument;

	@Temporal(TemporalType.DATE)
	@Column(name = "menu_date", nullable = false)
	private Date menuDate;

	@Enumerated(EnumType.STRING)
	@Column(name = "menu_status", nullable = false, length=30)
	private EnumList.MenuStatusEnum menuStatus;

	@Column(name = "count_prepare", nullable = false, precision = 10, scale = 3)
	private BigDecimal countPrepare = BigDecimal.ZERO;

	@Column(name = "count_sale", nullable = false, precision = 10, scale = 3)
	private BigDecimal countSale = BigDecimal.ZERO;

	@Column(name = "cancel_reason", nullable = true)
	private String cancelReason;

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

	public BigDecimal getCountSale() {
		return countSale;
	}

	public void setCountSale(BigDecimal countSale) {
		this.countSale = countSale!=null?countSale:BigDecimal.ZERO;
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

	public FiscalYearEntity getFiscalYear() {
		return fiscalYear;
	}

	public void setFiscalYear(FiscalYearEntity fiscalYear) {
		this.fiscalYear = fiscalYear;
	}

	public StkDocumentEntity getStkDocument() {
		return stkDocument;
	}

	public void setStkDocument(StkDocumentEntity stkDocument) {
		this.stkDocument = stkDocument;
	}

	public FinDocumentEntity getFinDocument() {
		return finDocument;
	}

	public void setFinDocument(FinDocumentEntity finDocument) {
		this.finDocument = finDocument;
	}

}
