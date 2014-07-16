package org.abacus.transaction.shared.event;

import org.abacus.transaction.shared.entity.ReqDetailEntity;
import org.abacus.transaction.shared.entity.ReqDetailOfferEntity;

public class UpdateSelectedOfferEvent {

	private ReqDetailOfferEntity offer;
	private ReqDetailEntity detail;
	private String user;

	public UpdateSelectedOfferEvent(ReqDetailEntity detail, ReqDetailOfferEntity offer, String user) {
		this.detail = detail;
		this.offer = offer;
		this.user = user;
	}

	public ReqDetailOfferEntity getOffer() {
		return offer;
	}

	public void setOffer(ReqDetailOfferEntity offer) {
		this.offer = offer;
	}

	public ReqDetailEntity getDetail() {
		return detail;
	}

	public void setDetail(ReqDetailEntity detail) {
		this.detail = detail;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

}
