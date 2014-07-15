package org.abacus.transaction.core.handler;

import org.abacus.transaction.shared.entity.ReqDetailOfferEntity;
import org.abacus.transaction.shared.event.CreateOfferEvent;
import org.abacus.transaction.shared.event.OfferCreatedEvent;
import org.abacus.transaction.shared.event.OfferUpdatedEvent;
import org.abacus.transaction.shared.event.UpdateOfferEvent;



public interface ReqOfferHandler {

	void deleteOffer(ReqDetailOfferEntity offer);

	OfferCreatedEvent saveOffer(CreateOfferEvent createOfferEvent);

	OfferUpdatedEvent updateOffer(UpdateOfferEvent updateOfferEvent);

	
}
