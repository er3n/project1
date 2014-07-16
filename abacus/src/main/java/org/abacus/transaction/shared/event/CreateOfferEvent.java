package org.abacus.transaction.shared.event;

import org.abacus.transaction.shared.entity.ReqDetailOfferEntity;

public class CreateOfferEvent {
	private ReqDetailOfferEntity offer;
	private String user;
	
	public CreateOfferEvent(ReqDetailOfferEntity offer,String user){
		this.user = user;
		this.offer = offer;
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
