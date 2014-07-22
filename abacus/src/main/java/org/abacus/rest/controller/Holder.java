package org.abacus.rest.controller;

import java.io.Serializable;
import java.math.BigDecimal;

public class Holder implements Serializable {

	private Number finansalYil;
	private String sirket;
	private BigDecimal tutar;
	private String islem;

	public Number getFinansalYil() {
		return finansalYil;
	}

	public void setFinansalYil(Number finansalYil) {
		this.finansalYil = finansalYil;
	}

	public String getSirket() {
		return sirket;
	}

	public void setSirket(String sirket) {
		this.sirket = sirket;
	}

	public BigDecimal getTutar() {
		return tutar;
	}

	public void setTutar(BigDecimal tutar) {
		this.tutar = tutar;
	}

	public String getIslem() {
		return islem;
	}

	public void setIslem(String islem) {
		this.islem = islem;
	}

}
