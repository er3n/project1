package org.abacus.transaction.shared.event;

import org.abacus.transaction.shared.entity.ReqDetailOfferEntity;

public class UpdateOfferEvent {

	private ReqDetailOfferEntity offer;
	private String user;

	public UpdateOfferEvent(ReqDetailOfferEntity offer, String user) {
		this.offer = offer;
		this.user = user;
	}

	public ReqDetailOfferEntity getOffer() {
		return offer;
	}

	public void setOffer(ReqDetailOfferEntity offer) {
		this.offer = offer;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

}
