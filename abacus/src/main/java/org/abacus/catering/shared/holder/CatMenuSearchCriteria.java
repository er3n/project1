package org.abacus.catering.shared.holder;

import java.io.Serializable;
import java.util.Date;

import org.abacus.definition.shared.constant.EnumList;
import org.joda.time.LocalDate;

@SuppressWarnings("serial")
public class CatMenuSearchCriteria implements Serializable {

	private EnumList.CatMenuPeriod period;
	private Date date;
	private Date startDate;
	private Date endDate;
	private String organization;

	public EnumList.CatMenuPeriod getPeriod() {
		return period;
	}

	public void setPeriod(EnumList.CatMenuPeriod period) {
		this.period = period;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getDate() {
		return date;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public void setDate(Date date) {
		this.date = date;

		LocalDate referanceDate = new LocalDate(date);
		if (period.equals(EnumList.CatMenuPeriod.WEEKLY)) {
			this.startDate = referanceDate.dayOfWeek().withMinimumValue().toDate();
			this.endDate = referanceDate.dayOfWeek().withMaximumValue().toDate();
		} else {
			this.startDate = referanceDate.dayOfMonth().withMinimumValue().toDate();
			this.endDate = referanceDate.dayOfMonth().withMaximumValue().toDate();
		}

	}

	public void refreshDate() {
		this.setDate(date);
	}

}
