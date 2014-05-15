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
@Table(name = "org_fiscal_period")
public class FiscalPeriodEntity extends StaticEntity {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fiscal_id", nullable = false)
	private FiscalEntity fiscal;
	
	@Column(name = "period_no", nullable = false, length=2)
	private String periodNo;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_start", nullable = true)
	private Date dateStart;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_finish", nullable = true)
	private Date dateFinish;

	public FiscalEntity getFiscal() {
		return fiscal;
	}

	public void setFiscal(FiscalEntity fiscal) {
		this.fiscal = fiscal;
	}

	public String getPeriodNo() {
		return periodNo;
	}

	public void setPeriodNo(String periodNo) {
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

	
}
