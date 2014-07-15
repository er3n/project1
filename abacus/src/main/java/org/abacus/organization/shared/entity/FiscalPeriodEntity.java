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
import org.hibernate.annotations.Type;

@Entity
@SuppressWarnings("serial")
@Table(name = "org_fiscal_period")
public class FiscalPeriodEntity extends StaticEntity {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fiscal_year_id", nullable = false)
	private FiscalYearEntity fiscalYear;
	
	@Column(name = "period_no", nullable = false)
	private Integer periodNo;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_start", nullable = true)
	private Date dateStart;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_finish", nullable = true)
	private Date dateFinish;

	@Column(name = "is_acc_active", nullable = false)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean isAccActive;

	@Column(name = "is_fin_active", nullable = false)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean isFinActive;

	@Column(name = "is_stk_active", nullable = false)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean isStkActive;

	public FiscalPeriodEntity(String id) {
		super.id = id;
	}
	
	public FiscalPeriodEntity(){}

	public Integer getPeriodNo() {
		return periodNo;
	}

	public void setPeriodNo(Integer periodNo) {
		this.periodNo = periodNo;
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

	public FiscalYearEntity getFiscalYear() {
		return fiscalYear;
	}

	public void setFiscalYear(FiscalYearEntity fiscalYear) {
		this.fiscalYear = fiscalYear;
	}

	public Boolean getIsAccActive() {
		return isAccActive;
	}

	public void setIsAccActive(Boolean isAccActive) {
		this.isAccActive = isAccActive;
	}

	public Boolean getIsFinActive() {
		return isFinActive;
	}

	public void setIsFinActive(Boolean isFinActive) {
		this.isFinActive = isFinActive;
	}

	public Boolean getIsStkActive() {
		return isStkActive;
	}

	public void setIsStkActive(Boolean isStkActive) {
		this.isStkActive = isStkActive;
	}

}
