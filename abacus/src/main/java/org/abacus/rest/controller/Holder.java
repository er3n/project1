package org.abacus.rest.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Holder implements Serializable {

	private Date finansalYil;
	private String sirket;
	private BigDecimal gelir;
	private BigDecimal gider;
	private Long calisanSayisi;
	private BigDecimal enBuyukHarcama;

	public Date getFinansalYil() {
		return finansalYil;
	}

	public void setFinansalYil(Date finansalYil) {
		this.finansalYil = finansalYil;
	}

	public String getSirket() {
		return sirket;
	}

	public void setSirket(String sirket) {
		this.sirket = sirket;
	}

	public BigDecimal getGelir() {
		return gelir;
	}

	public void setGelir(BigDecimal gelir) {
		this.gelir = gelir;
	}

	public BigDecimal getGider() {
		return gider;
	}

	public void setGider(BigDecimal gider) {
		this.gider = gider;
	}

	public Long getCalisanSayisi() {
		return calisanSayisi;
	}

	public void setCalisanSayisi(Long calisanSayisi) {
		this.calisanSayisi = calisanSayisi;
	}

	public BigDecimal getEnBuyukHarcama() {
		return enBuyukHarcama;
	}

	public void setEnBuyukHarcama(BigDecimal enBuyukHarcama) {
		this.enBuyukHarcama = enBuyukHarcama;
	}

}
