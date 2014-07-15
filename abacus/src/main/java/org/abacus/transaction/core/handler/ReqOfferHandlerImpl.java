package org.abacus.transaction.core.handler;

import org.abacus.transaction.core.persistance.repository.ReqDetailOfferRepository;
import org.abacus.transaction.shared.entity.ReqDetailOfferEntity;
import org.abacus.transaction.shared.event.CreateOfferEvent;
import org.abacus.transaction.shared.event.OfferCreatedEvent;
import org.abacus.transaction.shared.event.OfferUpdatedEvent;
import org.abacus.transaction.shared.event.UpdateOfferEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("reqOfferHandler")
public class ReqOfferHandlerImpl implements ReqOfferHandler {

	@Autowired
	private ReqDetailOfferRepository reqDetailOfferRepository;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteOffer(ReqDetailOfferEntity offer) {
		reqDetailOfferRepository.delete(offer.getId());		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public OfferCreatedEvent saveOffer(CreateOfferEvent createOfferEvent) {
		
		ReqDetailOfferEntity offer = createOfferEvent.getOffer();
		String user = createOfferEvent.getUser();
		
		offer.createHook(user);
		offer = reqDetailOfferRepository.save(offer);

		return new OfferCreatedEvent(offer);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public OfferUpdatedEvent updateOffer(UpdateOfferEvent updateOfferEvent) {
		
		ReqDetailOfferEntity offer = updateOfferEvent.getOffer();
		String user = updateOfferEvent.getUser();
		
		offer.updateHook(user);
		offer = reqDetailOfferRepository.save(offer);
		
		return new OfferUpdatedEvent(offer);
	}

	

	
	
}
