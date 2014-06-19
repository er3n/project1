package org.abacus.transaction.core.handler;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.abacus.definition.shared.constant.EnumList;
import org.abacus.transaction.core.persistance.repository.StkDetailRepository;
import org.abacus.transaction.core.persistance.repository.StkDetailTrackRepository;
import org.abacus.transaction.shared.UnableToCreateDetailException;
import org.abacus.transaction.shared.UnableToOutputDetail;
import org.abacus.transaction.shared.entity.StkDetailEntity;
import org.abacus.transaction.shared.entity.StkDetailTrackEntity;
import org.abacus.transaction.shared.event.CreateDetailEvent;
import org.abacus.transaction.shared.event.DetailCreatedEvent;
import org.abacus.transaction.shared.event.ReadDetailEvent;
import org.abacus.transaction.shared.event.RequestReadDetailEvent;
import org.abacus.transaction.shared.event.StkDetailCreatedEvent;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("stkTransactionHandler")
public class StkTransactionHandlerImpl extends TraTransactionSupport {

	@Autowired
	private StkDetailTrackRepository detailTrackRepository;
	
	@Autowired
	private StkDetailRepository stkDetailRepository;  
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public DetailCreatedEvent newDetail(CreateDetailEvent detailCreateEvent) throws UnableToCreateDetailException {
		
		boolean createStkTrack = detailCreateEvent.getDetail().getDocument().getTypeEnum().name().startsWith(EnumList.DefTypeGroupEnum.STK.name());
		Integer trStateDetail = detailCreateEvent.getDetail().getDocument().getTrStateDocument() * detailCreateEvent.getDetail().getDocument().getTypeEnum().getState();
		DetailCreatedEvent detailCreatedEvent=null;
		
		if(trStateDetail.equals(EnumList.TraState.INP.value())){
			detailCreateEvent.getDetail().setTrStateDetail(trStateDetail);
			detailCreatedEvent = super.newDetail(detailCreateEvent);
			if (createStkTrack){
				this.addInputDetailTrack(detailCreatedEvent);
			}
		}else if(trStateDetail.equals(EnumList.TraState.OUT.value())){
			detailCreateEvent.getDetail().setTrStateDetail(trStateDetail);
			detailCreatedEvent = super.newDetail(detailCreateEvent);
			if (createStkTrack){
				this.addOutputDetailTrack(detailCreatedEvent);
			}
		}else{
			if (createStkTrack){
				detailCreatedEvent = this.addStkTransferDetailsAndTracks(detailCreateEvent);
			}
		}

		return detailCreatedEvent;
		
	}

	private StkDetailCreatedEvent addStkTransferDetailsAndTracks(CreateDetailEvent detailCreateEvent) throws UnableToCreateDetailException {
		
		List<StkDetailTrackEntity> detailTrackList = new ArrayList<StkDetailTrackEntity>();
		
		//Transfer Out
		Integer trStateDetailOut = detailCreateEvent.getDetail().getDocument().getTrStateDocument() * -1;
		detailCreateEvent.getDetail().setTrStateDetail(trStateDetailOut);
		DetailCreatedEvent detailCreatedEvent = super.newDetail(detailCreateEvent);
		
		StkDetailCreatedEvent stockDetailCreatedEvent = this.addOutputDetailTrack(detailCreatedEvent);
		StkDetailEntity outDetail = (StkDetailEntity) detailCreatedEvent.getDetail();
		List<StkDetailTrackEntity> outDetailTrackList = stockDetailCreatedEvent.getDetailTrackList();
		
		detailTrackList.addAll(outDetailTrackList);
		

		//Transfer In
		Integer trStateDetailIn = trStateDetailOut * (-1);

		StkDetailEntity inputDetail = new StkDetailEntity();
		BeanUtils.copyProperties(outDetail, inputDetail);
		inputDetail.setDepartment(outDetail.getDepartmentOpp());
		inputDetail.setDepartmentOpp(outDetail.getDepartment());
		inputDetail.createHook(outDetail.getUserCreated());
		inputDetail.setTrStateDetail(trStateDetailIn);
		inputDetail.setRefDetailId(outDetail.getId());
		
		DetailCreatedEvent detailCreatedEventIn = super.newDetail(new CreateDetailEvent(inputDetail, outDetail.getUserCreated()));
		
		for(StkDetailTrackEntity outDetailTrack : outDetailTrackList){
			StkDetailTrackEntity inDetailTrack = new StkDetailTrackEntity();
			BeanUtils.copyProperties(outDetailTrack, inDetailTrack);
			inDetailTrack.cleanCreateHook(outDetail.getUserCreated());
			inDetailTrack.setDetail(inputDetail);
			inDetailTrack.setParentTrackId(outDetailTrack.getId());
			inDetailTrack.setBaseUsedCount(BigDecimal.ZERO);
			inDetailTrack = detailTrackRepository.save(inDetailTrack);
			
			detailTrackList.add(inDetailTrack);

		}
		return new StkDetailCreatedEvent(outDetail, detailTrackList);
	}

	private StkDetailCreatedEvent addOutputDetailTrack(DetailCreatedEvent event) throws UnableToOutputDetail {
		
		StkDetailEntity detail = (StkDetailEntity) event.getDetail();
		String user = event.getDetail().getUserCreated();
		BigDecimal detailCount =  detail.getBaseDetailCount();
		
		BigDecimal currentItemCount = detailTrackRepository.currentItemCount(detail.getItem().getId(),detail.getDepartment().getId(), detail.getFiscalYear().getId());
		  
		boolean isNotEnoughtItemStock = currentItemCount == null || detailCount.compareTo(currentItemCount) > 0;
		if(isNotEnoughtItemStock){
			throw new UnableToOutputDetail();
		}
		
		List<StkDetailTrackEntity> itemListInStock = detailTrackRepository.currentItemList(detail.getItem().getId(),detail.getDepartment().getId(), detail.getFiscalYear().getId());
		
		BigDecimal restCount = detailCount;
		List<StkDetailTrackEntity> detailTrackList = new ArrayList<StkDetailTrackEntity>();
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
			
			detailTrackList.add(detailTrack);
			
		}
		
		return new StkDetailCreatedEvent(detail, detailTrackList);
		
	}

	private StkDetailCreatedEvent addInputDetailTrack(DetailCreatedEvent event) {
		
		StkDetailEntity detail = (StkDetailEntity) event.getDetail();
		String user = event.getDetail().getUserCreated();
		BigDecimal detailCount =  detail.getBaseDetailCount();
		BigDecimal unitTrackPrice = detail.getBaseDetailAmount().divide(detailCount,EnumList.RoundScale.STK.getValue(),RoundingMode.HALF_EVEN);
		List<StkDetailTrackEntity> detailTrackList = new ArrayList<StkDetailTrackEntity>();

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

		detailTrackList.add(detailTrack);
		
		return new StkDetailCreatedEvent(detail, detailTrackList);

	}

	@Override
	public ReadDetailEvent readDetail(RequestReadDetailEvent event) {
		List<StkDetailEntity> details = stkDetailRepository.findByDocumentId(event.getDocumentId());
		return new ReadDetailEvent(details);
	}
	
}
