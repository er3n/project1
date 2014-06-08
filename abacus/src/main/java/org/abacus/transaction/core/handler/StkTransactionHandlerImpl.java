package org.abacus.transaction.core.handler;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.abacus.definition.shared.constant.EnumList;
import org.abacus.transaction.core.persistance.repository.StkDetailTrackRepository;
import org.abacus.transaction.shared.UnableToCreateDetailException;
import org.abacus.transaction.shared.UnableToOutputDetail;
import org.abacus.transaction.shared.entity.StkDetailEntity;
import org.abacus.transaction.shared.entity.StkDetailTrackEntity;
import org.abacus.transaction.shared.event.CreateDetailEvent;
import org.abacus.transaction.shared.event.DetailCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("stkTransactionHandler")
public class StkTransactionHandlerImpl extends TraTransactionSupport {

	@Autowired
	private StkDetailTrackRepository detailTrackRepository;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public DetailCreatedEvent newDetail(CreateDetailEvent event) throws UnableToCreateDetailException {
		
		Integer trStateDetail = event.getDetail().getDocument().getTrStateDocument() * event.getDetail().getDocument().getTypeEnum().getState();
		event.getDetail().setTrStateDetail(trStateDetail);
		
		DetailCreatedEvent detailCreatedEvent = super.newDetail(event);
		
		if(trStateDetail.equals(EnumList.TraState.INP.value())){
			this.addInputDetail(detailCreatedEvent);
		}else if(trStateDetail.equals(EnumList.TraState.OUT.value())){
			this.addOutputDetail(detailCreatedEvent);
		}

		return detailCreatedEvent;
		
	}

	private void addOutputDetail(DetailCreatedEvent event) throws UnableToOutputDetail {
		
		StkDetailEntity detail = (StkDetailEntity) event.getDetail();
		String user = event.getDetail().getUserCreated();
		BigDecimal detailCount =  detail.getBaseDetailCount();
		
		BigDecimal currentItemCount = detailTrackRepository.currentItemCount(detail.getItem().getId(),detail.getDepartment().getId(), detail.getFiscalYear().getId());
		
		boolean isNotEnoughtItemStock = detailCount.compareTo(currentItemCount) > 0;
		if(isNotEnoughtItemStock){
			throw new UnableToOutputDetail();
		}
		
		List<StkDetailTrackEntity> itemListInStock = detailTrackRepository.currentItemList(detail.getItem().getId(),detail.getDepartment().getId(), detail.getFiscalYear().getId());
		
		BigDecimal restCount = detailCount;
		for(StkDetailTrackEntity itemInStock : itemListInStock){
			
			if(restCount.compareTo(BigDecimal.ZERO) <= 0){
				break;
			}
			
			BigDecimal countInStock = itemInStock.getBaseTrackCount().subtract(itemInStock.getBaseUsedCount());
			
			BigDecimal countOutStock = null;
			if(countInStock.compareTo(restCount) >= 0){
				countOutStock = restCount;
				restCount  = BigDecimal.ZERO;
			}else{
				countOutStock = countInStock;
				restCount = restCount.subtract(countInStock);
			}
			
			itemInStock.setBaseUsedCount(itemInStock.getBaseUsedCount().add(countOutStock));
			itemInStock.updateHook(user);
			itemInStock = detailTrackRepository.save(itemInStock);
			
			 
			StkDetailTrackEntity detailTrack = new StkDetailTrackEntity();
			
			detailTrack.setDetail(detail);
			detailTrack.setRootDetailId(itemInStock.getRootDetailId());
			detailTrack.setParentTrackId(itemInStock.getId());
			detailTrack.setBaseTrackCount(countOutStock);
			detailTrack.setBaseUsedCount(countOutStock);
			detailTrack.setUnitTrackPrice(itemInStock.getUnitTrackPrice());
			detailTrack.setUnitCostPrice(itemInStock.getUnitCostPrice());
			detailTrack.setLotTrackDate(itemInStock.getLotTrackDate());
			detailTrack.setBatchTrackNo(itemInStock.getBatchTrackNo());
			
			detailTrack.createHook(user);
			
			detailTrack = detailTrackRepository.save(detailTrack);
			
		}
		
	}

	private void addInputDetail(DetailCreatedEvent event) {
		StkDetailEntity detail = (StkDetailEntity) event.getDetail();
		String user = event.getDetail().getUserCreated();
		BigDecimal detailCount =  detail.getBaseDetailCount();
		BigDecimal unitTrackPrice = detail.getBaseDetailAmount().divide(detailCount,EnumList.RoundScale.STK.getValue(),RoundingMode.HALF_EVEN);

		StkDetailTrackEntity detailTrack = new StkDetailTrackEntity();
		
		detailTrack.setDetail(detail);
		detailTrack.setRootDetailId(detail.getId());
		detailTrack.setParentTrackId(null);
		detailTrack.setBaseTrackCount(detailCount);
		detailTrack.setBaseUsedCount(BigDecimal.ZERO);
		detailTrack.setUnitTrackPrice(unitTrackPrice);
		detailTrack.setUnitCostPrice(unitTrackPrice);
		detailTrack.setLotTrackDate(detail.getLotDetailDate());
		detailTrack.setBatchTrackNo(detail.getBatchDetailNo());
		
		detailTrack.createHook(user);
		
		detailTrack = detailTrackRepository.save(detailTrack);
		
	}
	
}
