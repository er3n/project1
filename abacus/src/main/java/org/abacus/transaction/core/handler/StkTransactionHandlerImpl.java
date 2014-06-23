package org.abacus.transaction.core.handler;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.abacus.definition.shared.constant.EnumList;
import org.abacus.transaction.core.persistance.StkTransactionDao;
import org.abacus.transaction.core.persistance.TraTransactionDao;
import org.abacus.transaction.core.persistance.repository.StkDetailRepository;
import org.abacus.transaction.core.persistance.repository.StkDetailTrackRepository;
import org.abacus.transaction.core.persistance.repository.StkDocumentRepository;
import org.abacus.transaction.core.persistance.repository.TraDetailRepository;
import org.abacus.transaction.core.persistance.repository.TraDocumentRepository;
import org.abacus.transaction.shared.UnableToCreateDetailException;
import org.abacus.transaction.shared.UnableToDeleteDetailException;
import org.abacus.transaction.shared.UnableToDeleteDocumentException;
import org.abacus.transaction.shared.UnableToOutputDetail;
import org.abacus.transaction.shared.UnableToUpdateDetailException;
import org.abacus.transaction.shared.UnableToUpdateDocumentExpception;
import org.abacus.transaction.shared.entity.StkDetailEntity;
import org.abacus.transaction.shared.entity.StkDetailTrackEntity;
import org.abacus.transaction.shared.entity.StkDocumentEntity;
import org.abacus.transaction.shared.event.CancelDocumentEvent;
import org.abacus.transaction.shared.event.CreateDetailEvent;
import org.abacus.transaction.shared.event.DeleteDetailEvent;
import org.abacus.transaction.shared.event.DeleteDocumentEvent;
import org.abacus.transaction.shared.event.DetailCreatedEvent;
import org.abacus.transaction.shared.event.DetailDeletedEvent;
import org.abacus.transaction.shared.event.DetailUpdatedEvent;
import org.abacus.transaction.shared.event.DocumentCanceledEvent;
import org.abacus.transaction.shared.event.DocumentDeletedEvent;
import org.abacus.transaction.shared.event.DocumentUpdatedEvent;
import org.abacus.transaction.shared.event.UpdateDetailEvent;
import org.abacus.transaction.shared.event.UpdateDocumentEvent;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("stkTransactionHandler")
public class StkTransactionHandlerImpl extends TraTransactionSupport<StkDocumentEntity, StkDetailEntity> {

	@Autowired
	private StkDetailRepository stkDetailRepository;  

	@Autowired
	private StkDocumentRepository stkDocumentRepository;  

	@Autowired
	private StkDetailTrackRepository detailTrackRepository;
	
	@Autowired
	private StkTransactionDao stkTransactionDao;
	
	@Override
	protected TraTransactionDao<StkDocumentEntity, StkDetailEntity> getTransactionDao() {
		return stkTransactionDao;
	}

	@Override
	protected TraDocumentRepository<StkDocumentEntity> getDocumentRepository() {
		return stkDocumentRepository;
	}

	@Override
	protected TraDetailRepository<StkDetailEntity> getDetailRepository() {
		return stkDetailRepository;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public DetailCreatedEvent<StkDetailEntity> newDetail(CreateDetailEvent<StkDetailEntity> detailCreateEvent) throws UnableToCreateDetailException {
		
		boolean createStkTrack = //
				detailCreateEvent.getDetail().getDocument().getTypeEnum().name().startsWith(EnumList.DefTypeGroupEnum.STK.name()) && 	//Stk Document
				detailCreateEvent.getDetail().getItem().getType().getId().equals(EnumList.DefTypeEnum.ITM_SR_ST.name()); 				//Stk Item
		
		Integer trStateDetail = detailCreateEvent.getDetail().getDocument().getTrStateDocument() * detailCreateEvent.getDetail().getDocument().getTypeEnum().getState();
		DetailCreatedEvent<StkDetailEntity> detailCreatedEvent=null;
		
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

	private DetailCreatedEvent addStkTransferDetailsAndTracks(CreateDetailEvent<StkDetailEntity> detailCreateEvent) throws UnableToCreateDetailException {
		
		List<StkDetailTrackEntity> detailTrackList = new ArrayList<StkDetailTrackEntity>();
		
		//Transfer Out
		Integer trStateDetailOut = detailCreateEvent.getDetail().getDocument().getTrStateDocument() * -1;
		detailCreateEvent.getDetail().setTrStateDetail(trStateDetailOut);
		DetailCreatedEvent<StkDetailEntity> detailCreatedEvent = super.newDetail(detailCreateEvent);
		
		DetailCreatedEvent stockDetailCreatedEvent = this.addOutputDetailTrack(detailCreatedEvent);
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
		
		DetailCreatedEvent<StkDetailEntity> detailCreatedEventIn = super.newDetail(new CreateDetailEvent<StkDetailEntity>(inputDetail, outDetail.getUserCreated()));
		
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
		return new DetailCreatedEvent(outDetail, detailTrackList);
	}

	private DetailCreatedEvent addOutputDetailTrack(DetailCreatedEvent<StkDetailEntity> event) throws UnableToOutputDetail {
		
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
		
		return new DetailCreatedEvent(detail, detailTrackList);
		
	}

	private DetailCreatedEvent addInputDetailTrack(DetailCreatedEvent<StkDetailEntity> event) {
		
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
		
		return new DetailCreatedEvent(detail, detailTrackList);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public DocumentDeletedEvent<StkDocumentEntity> deleteDocument(DeleteDocumentEvent<StkDocumentEntity> event) throws UnableToDeleteDocumentException {
		// Finans, Muhasebe kaydi varsa onlarda da silinecek, sorulacak
		StkDocumentEntity document = stkDocumentRepository.findWithFetch(event.getDocumentId());
		List<StkDetailEntity> detailList = stkDetailRepository.findByDocumentId(event.getDocumentId());
		for (StkDetailEntity dtl : detailList) {
			if (stkUpdateControl(dtl)){
				Boolean dIslem = stkTransactionDao.detailDelete(dtl);
			} else {
				throw new UnableToDeleteDocumentException();
			}
		}
		Boolean tIslem = stkTransactionDao.documentDelete(document);
		return new DocumentDeletedEvent<>();
	}
	
	private Boolean stkUpdateControl(StkDetailEntity dtl){
		//item & depo degismiyor ancak diger degerler degisiyorsa sadece miktar kontrolleri yapilacak
		//item & depodan biri degisiyor sa eski row tamamen siliniyormus gibi kontrol edilecek
		printValues(dtl);
		if (dtl.getTrStateDetail()<0){
			return stkUpdateOutputControl(dtl);
		} else if (dtl.getTrStateDetail()>0){
			return stkUpdateInputControl(dtl);
		} 
		return true;
	}
	
	private Boolean stkUpdateInputControl(StkDetailEntity dtl){
		// + Giris kayitlarinda eksiltme ve silme yapilabir mi kontrolu eklenecek
		return true;
	}
	
	private Boolean stkUpdateOutputControl(StkDetailEntity dtl){
		// - Cikis kayitlarinda artirma yapilabir mi kontrolu eklenecek
		return true;
	}
	
	private void printValues(StkDetailEntity new_){
		StkDetailEntity old_ = (StkDetailEntity)new_.getMemento();
		System.out.println("--- Eski Degerler ---  Id:"+old_.getId());
		System.out.println("Item       : "+old_.getItem().getCode());
		System.out.println("Department : "+old_.getDepartment().getCode());
		System.out.println("Count      : "+old_.getBaseDetailCount());
		System.out.println("State      : "+old_.getTrStateSign());
		System.out.println("--- Yeni Degerler ---  Id:"+new_.getId());
		System.out.println("Item       : "+new_.getItem().getCode());
		System.out.println("Department : "+new_.getDepartment().getCode());
		System.out.println("Count      : "+new_.getBaseDetailCount());
		System.out.println("State      : "+new_.getTrStateSign());
	}

	@Override
	public DocumentUpdatedEvent<StkDocumentEntity> updateDocument(UpdateDocumentEvent<StkDocumentEntity> event) throws UnableToUpdateDocumentExpception {
		StkDocumentEntity doc = event.getDocument();
		return null;
	}


	@Override
	public DocumentCanceledEvent cancelDocument(CancelDocumentEvent cancelDocumentEvent) throws UnableToUpdateDocumentExpception {
		Long docId = cancelDocumentEvent.getDocumentId();
		return null;
	}

	@Override
	public DetailUpdatedEvent<StkDetailEntity> updateDetail(UpdateDetailEvent<StkDetailEntity> event) throws UnableToUpdateDetailException {
		StkDetailEntity det = event.getDetail();
		return null;
	}

	@Override
	public DetailDeletedEvent<StkDetailEntity> deleteDetail(DeleteDetailEvent<StkDetailEntity> event) throws UnableToDeleteDetailException {
		Long detId = event.getDetailId();
		return null;
	}
	
}
