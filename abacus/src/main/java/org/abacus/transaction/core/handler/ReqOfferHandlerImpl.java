package org.abacus.transaction.core.handler;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.abacus.transaction.core.persistance.repository.ReqDetailOfferRepository;
import org.abacus.transaction.core.persistance.repository.ReqDetailRepository;
import org.abacus.transaction.shared.entity.ReqDetailEntity;
import org.abacus.transaction.shared.entity.ReqDetailOfferEntity;
import org.abacus.transaction.shared.event.CreateOfferEvent;
import org.abacus.transaction.shared.event.OfferCreatedEvent;
import org.abacus.transaction.shared.event.OfferUpdatedEvent;
import org.abacus.transaction.shared.event.SelectedOfferUpdated;
import org.abacus.transaction.shared.event.UpdateOfferEvent;
import org.abacus.transaction.shared.event.UpdateSelectedOfferEvent;
import org.abacus.transaction.shared.holder.ReqPurcVendorHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("reqOfferHandler")
public class ReqOfferHandlerImpl implements ReqOfferHandler {

	@Autowired
	private ReqDetailOfferRepository reqDetailOfferRepository;
	
	@Autowired
	private ReqDetailRepository reqDetailRepository;
	
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

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public SelectedOfferUpdated updateSelectedOffer(UpdateSelectedOfferEvent updateSelectedOfferEvent) {
		
		ReqDetailOfferEntity selectedOffer = updateSelectedOfferEvent.getOffer();
		ReqDetailEntity detail = updateSelectedOfferEvent.getDetail();
		String user = updateSelectedOfferEvent.getUser();
		
		for(ReqDetailOfferEntity offer : detail.getOfferSet()){
			if(offer.getIsSelected() && !offer.getId().equals(selectedOffer.getId())){
				offer.setIsSelected(false);
				offer.updateHook(user);
				reqDetailOfferRepository.save(offer);
			}
		}
		
		selectedOffer.updateHook(user);
		reqDetailOfferRepository.save(selectedOffer);
		
		if(selectedOffer.getIsSelected()){
			detail.setBaseDetailAmount(selectedOffer.getUnitOfferPrice());
			
		}else{
			detail.setBaseDetailAmount(BigDecimal.ZERO);
		}
		reqDetailRepository.save(detail);
		
		return new SelectedOfferUpdated(selectedOffer);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ReqPurcVendorHolder> findChoosenVendors(Long documentId) {
		
		List<ReqDetailOfferEntity> selectedVendorOfferList = reqDetailOfferRepository.findSelectedVendorDetailsByDocument(documentId);
		
		List<ReqPurcVendorHolder> holderList = new ArrayList<ReqPurcVendorHolder>();
		for(ReqDetailOfferEntity selectedVendorOffer : selectedVendorOfferList){
			
			boolean isDetailAppend = false;
			for(ReqPurcVendorHolder newHolder : holderList){
				if(newHolder.getVendor().getId().equals(selectedVendorOffer.getVendorItem().getId())){
					isDetailAppend = true;
					newHolder.getDetails().add(selectedVendorOffer.getDetail());
				}
			}
			if(!isDetailAppend){
				ReqPurcVendorHolder holder = new ReqPurcVendorHolder();
				holder.setReqDocument(selectedVendorOffer.getDetail().getDocument());
				holder.setStkDocument(selectedVendorOffer.getDetail().getStkDocument());
				holder.setVendor(selectedVendorOffer.getVendorItem());
				holder.setDetails(new ArrayList<ReqDetailEntity>());
				holder.getDetails().add(selectedVendorOffer.getDetail());
				holderList.add(holder);
			}
			
		}
		
		return holderList;
	}

	

	
	
}
