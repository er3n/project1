package org.abacus.transaction.core.handler;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.entity.DefItemEntity;
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
	private StkDetailTrackRepository stkDetailTrackRepository;
	
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
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public DocumentUpdatedEvent<StkDocumentEntity> updateDocument(UpdateDocumentEvent<StkDocumentEntity> event) throws UnableToUpdateDocumentExpception {
		StkDocumentEntity doc = event.getDocument();
		doc = stkDocumentRepository.save(doc);
		return new DocumentUpdatedEvent<StkDocumentEntity>(doc);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public DocumentDeletedEvent<StkDocumentEntity> deleteDocument(DeleteDocumentEvent<StkDocumentEntity> event) throws UnableToDeleteDocumentException {
		// Finans, Muhasebe kaydi varsa onlarda da silinecek, sorulacak
		StkDocumentEntity document = stkDocumentRepository.findWithFetch(event.getDocument().getId());
		List<StkDetailEntity> detailList = stkDetailRepository.findByDocumentId(event.getDocument().getId());
		for (StkDetailEntity dtl : detailList) {
			Boolean result = deleteDetailRecords(dtl);
			if (!result){
				throw new UnableToDeleteDetailException();
			}
		}
		stkDocumentRepository.delete(document);
		return new DocumentDeletedEvent<>();
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public DocumentCanceledEvent<StkDocumentEntity> cancelDocument(CancelDocumentEvent<StkDocumentEntity> cancelDocumentEvent) throws UnableToUpdateDocumentExpception {
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public DetailCreatedEvent<StkDetailEntity> newDetail(CreateDetailEvent<StkDetailEntity> detailCreateEvent) throws UnableToCreateDetailException {
		StkDocumentEntity doc = detailCreateEvent.getDetail().getDocument();
		DefItemEntity itm = detailCreateEvent.getDetail().getItem();
		boolean createStkTrack = //
				doc.getTypeEnum().name().startsWith(EnumList.DefTypeGroupEnum.STK.name()) && 	//Stk Document
				itm.getType().getId().equals(EnumList.DefTypeEnum.ITM_SR_ST.name()); 			//Stk Item
		
		Integer trStateDetail = detailCreateEvent.getDetail().getDocument().getTypeEnum().getState();
		DetailCreatedEvent<StkDetailEntity> detailCreatedEvent=null;
		
		if(trStateDetail.equals(EnumList.TraState.INP.value())){
			detailCreateEvent.getDetail().setTrStateDetail(trStateDetail);
			detailCreatedEvent = super.newDetail(detailCreateEvent);
			if (createStkTrack){
				this.addInputDetailTrackList(detailCreatedEvent);
			}
		}else if(trStateDetail.equals(EnumList.TraState.OUT.value())){
			detailCreateEvent.getDetail().setTrStateDetail(trStateDetail);
			detailCreatedEvent = super.newDetail(detailCreateEvent);
			if (createStkTrack){
				this.addOutputDetailTrackList(detailCreatedEvent);
			}
		}else{
			//Transfer Out
			detailCreateEvent.getDetail().setTrStateDetail(EnumList.TraState.OUT.value());
			detailCreatedEvent = super.newDetail(detailCreateEvent);
			if (createStkTrack){
				this.addOutputDetailTrackList(detailCreatedEvent);
			}
			List<StkDetailTrackEntity> outDetailTrackList = detailCreatedEvent.getDetailTrackList();
			
			//Transfer In
			StkDetailEntity outDetail = detailCreatedEvent.getDetail();
			StkDetailEntity inDetail = new StkDetailEntity();
			BeanUtils.copyProperties(outDetail, inDetail);
			inDetail.cleanCreateHook(outDetail.getUserCreated());
			inDetail.setDepartment(outDetail.getDepartmentOpp());
			inDetail.setDepartmentOpp(outDetail.getDepartment());
			inDetail.setRefDetailId(outDetail.getId());
			inDetail.setTrStateDetail(EnumList.TraState.INP.value());
			detailCreateEvent.setDetail(inDetail);
			detailCreatedEvent = super.newDetail(detailCreateEvent);
			detailCreatedEvent.setDetailTrackList(outDetailTrackList);
			if (createStkTrack){
				this.addInputDetailTrackList(detailCreatedEvent);
			}
		}

		return detailCreatedEvent;
		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public DetailUpdatedEvent<StkDetailEntity> updateDetail(UpdateDetailEvent<StkDetailEntity> event) throws UnableToUpdateDetailException {
		StkDetailEntity det = event.getDetail();
		if (trackRefreshRequired(det)){
			Boolean result = deleteDetailRecords(event.getDetail());
			if (!result){
				throw new UnableToUpdateDetailException();
			}
			DetailCreatedEvent<StkDetailEntity> detailCreated = newDetail(new CreateDetailEvent<StkDetailEntity>(det, event.getUser()));
			det = detailCreated.getDetail();
		} else {
			det = stkDetailRepository.save(det);
		}
		return new DetailUpdatedEvent<StkDetailEntity>(det);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public DetailDeletedEvent<StkDetailEntity> deleteDetail(DeleteDetailEvent<StkDetailEntity> event) throws UnableToDeleteDetailException {
		Boolean result = deleteDetailRecords(event.getDetail());
		if (!result){
			throw new UnableToDeleteDetailException();
		}
		return new DetailDeletedEvent<StkDetailEntity>();
	}
	
	private Boolean trackRefreshRequired(StkDetailEntity dtl){
		StkDetailEntity old = dtl.getPoint();
		if (old==null){
			return true;
		}
		if (dtl.getItemDetailCount().compareTo(old.getItemDetailCount())!=0){
			return true;
		}
		if (dtl.getItemUnit().getId().compareTo(old.getItemUnit().getId())!=0){
			return true;
		}
		if (dtl.getItem().getId().compareTo(old.getItem().getId())!=0){
			return true;
		}
		if (dtl.getDepartment().getId().compareTo(old.getDepartment().getId())!=0){
			return true;
		}
		return false;
	}
	
	private Boolean deleteDetailRecords(StkDetailEntity detail){
		try{
			//Input silerken tracklar silinemez ise kullanilmis demektir, Kontrole gerek yok.
			if (detail.getTrStateDetail().equals(EnumList.TraState.OUT.value())){

				//Transfer Ise Once TransferInput Kayitlarini Sil, Silemiyor sa kullanilmistir
				if (detail.getDepartmentOpp()!=null){
					StkDetailEntity oppDetail = stkDetailRepository.findByRefId(detail.getId());
					if (oppDetail!=null){
						stkDetailTrackRepository.deleteDetailTrack(oppDetail.getId());
						stkDetailRepository.delete(oppDetail.getId());
					}
				}
				
				//Output silerken ParentTrack kaydinin UsedCount bilgisini output kadar artirmak gereklidir.
				List<StkDetailTrackEntity> trackList  = stkDetailTrackRepository.findDetailTrack(detail.getId());
				for (StkDetailTrackEntity track : trackList) {
					if (track.getParentTrack()!=null){
						StkDetailTrackEntity parent = track.getParentTrack();
						parent.setBaseUsedCount(parent.getBaseUsedCount().subtract(track.getBaseTrackCount()));
						stkDetailTrackRepository.save(parent);
					}
				}
			}
			stkDetailTrackRepository.deleteDetailTrack(detail.getId());
			stkDetailRepository.delete(detail.getId());
			
			return true;
		} catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	private void addInputDetailTrackList(DetailCreatedEvent<StkDetailEntity> event) {
		StkDetailEntity detail = event.getDetail();
		String user = detail.getUserCreated();
		
		if (event.getDetailTrackList().size()==0){ // Direk Giris = 0 Eski Track, 1 Yeni Track Olusacak
			BigDecimal detailCount =  detail.getBaseDetailCount();
			BigDecimal unitTrackPrice = detail.getBaseDetailAmount().divide(detailCount,EnumList.RoundScale.STK.getValue(),RoundingMode.HALF_EVEN);

			StkDetailTrackEntity inDetailTrack = new StkDetailTrackEntity();
			inDetailTrack.setDetail(detail);
			inDetailTrack.setRootDetail(detail);
			inDetailTrack.setParentTrack(null);
			inDetailTrack.setBaseTrackCount(detailCount);
			inDetailTrack.setBaseUsedCount(BigDecimal.ZERO);
			inDetailTrack.setUnitTrackPrice(unitTrackPrice);
			inDetailTrack.setUnitCostPrice(unitTrackPrice);
			inDetailTrack.setDueTrackDate(detail.getDueDate());
			inDetailTrack.setBatchTrackNo(detail.getBatchDetailNo());
			inDetailTrack.createHook(user);
			inDetailTrack.setTrStateTrack(EnumList.TraState.INP.value());
			inDetailTrack = stkDetailTrackRepository.save(inDetailTrack);
			event.getDetailTrackList().add(inDetailTrack);
		} else { // Transferle Gelen Giris = N Eski Track, N Yeni Track Olusacak  
			for (StkDetailTrackEntity outDetailTrack : event.getDetailTrackList()) {
				StkDetailTrackEntity inDetailTrack = new StkDetailTrackEntity();
				BeanUtils.copyProperties(outDetailTrack, inDetailTrack);
				inDetailTrack.cleanCreateHook(user);
				inDetailTrack.setDetail(detail);
				inDetailTrack.setParentTrack(outDetailTrack);
				inDetailTrack.setBaseUsedCount(BigDecimal.ZERO);
				inDetailTrack.setBaseTrackCount(outDetailTrack.getBaseUsedCount());
				inDetailTrack.setTrStateTrack(EnumList.TraState.INP.value());
				inDetailTrack = stkDetailTrackRepository.save(inDetailTrack);
//				event.getDetailTrackList().add(inDetailTrack);
			}
		}
	}
	
	private void addOutputDetailTrackList(DetailCreatedEvent<StkDetailEntity> event) throws UnableToOutputDetail {
		StkDetailEntity detail = event.getDetail();

		List<StkDetailTrackEntity> findAvailableTrack = stkDetailTrackRepository.currentItemList(detail.getItem().getId(),detail.getDepartment().getId(), detail.getFiscalYear().getId());

		String user = event.getDetail().getUserCreated();
		BigDecimal detailCount =  detail.getBaseDetailCount();
		BigDecimal currentItemCount = stkDetailTrackRepository.currentItemCount(detail.getItem().getId(),detail.getDepartment().getId(), detail.getFiscalYear().getId());
		  
		boolean isNotEnoughtItemStock = currentItemCount == null || detailCount.compareTo(currentItemCount) > 0;
		if(isNotEnoughtItemStock){
			throw new UnableToOutputDetail(detail.getItem().getName());
		}
		
		BigDecimal restCount = detailCount;
		for(StkDetailTrackEntity itemInStock : findAvailableTrack){
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
			//Existing Parent Input Track Update
			itemInStock.setBaseUsedCount(itemInStock.getBaseUsedCount().add(countOutStock));
			itemInStock = stkDetailTrackRepository.save(itemInStock);
			
			//New Out Track
			StkDetailTrackEntity detailTrack = new StkDetailTrackEntity();
			detailTrack.setDetail(detail);
			detailTrack.setRootDetail(itemInStock.getRootDetail());
			detailTrack.setParentTrack(itemInStock);
			detailTrack.setBaseTrackCount(countOutStock);
			detailTrack.setBaseUsedCount(countOutStock);
			detailTrack.setUnitTrackPrice(itemInStock.getUnitTrackPrice());
			detailTrack.setUnitCostPrice(itemInStock.getUnitCostPrice());
			detailTrack.setDueTrackDate(itemInStock.getDueTrackDate());
			detailTrack.setBatchTrackNo(itemInStock.getBatchTrackNo());
			detailTrack.setTrStateTrack(EnumList.TraState.OUT.value());
			detailTrack.createHook(user);
			detailTrack = stkDetailTrackRepository.save(detailTrack);
			event.getDetailTrackList().add(detailTrack);
		}
	}
	
}
