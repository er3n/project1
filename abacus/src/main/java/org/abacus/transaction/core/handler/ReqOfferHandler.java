package org.abacus.transaction.core.handler;

import java.util.List;

import org.abacus.transaction.shared.entity.ReqDetailOfferEntity;
import org.abacus.transaction.shared.event.CreateOfferEvent;
import org.abacus.transaction.shared.event.OfferCreatedEvent;
import org.abacus.transaction.shared.event.OfferUpdatedEvent;
import org.abacus.transaction.shared.event.SelectedOfferUpdated;
import org.abacus.transaction.shared.event.UpdateOfferEvent;
import org.abacus.transaction.shared.event.UpdateSelectedOfferEvent;
import org.abacus.transaction.shared.holder.ReqPurcVendorHolder;



public interface ReqOfferHandler {

	void deleteOffer(ReqDetailOfferEntity offer);

	OfferCreatedEvent saveOffer(CreateOfferEvent createOfferEvent);

	OfferUpdatedEvent updateOffer(UpdateOfferEvent updateOfferEvent);

	SelectedOfferUpdated updateSelectedOffer(UpdateSelectedOfferEvent updateSelectedOfferEvent);

	List<ReqPurcVendorHolder> findChoosenVendors(Long documentId);

	
}
