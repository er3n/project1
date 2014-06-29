package org.abacus.transaction.core.handler;

import java.util.List;

import org.abacus.definition.shared.constant.EnumList;
import org.abacus.transaction.core.persistance.FinTransactionDao;
import org.abacus.transaction.core.persistance.TraTransactionDao;
import org.abacus.transaction.core.persistance.repository.FinDetailRepository;
import org.abacus.transaction.core.persistance.repository.FinDocumentRepository;
import org.abacus.transaction.core.persistance.repository.TraDetailRepository;
import org.abacus.transaction.core.persistance.repository.TraDocumentRepository;
import org.abacus.transaction.shared.UnableToCreateDetailException;
import org.abacus.transaction.shared.UnableToDeleteDetailException;
import org.abacus.transaction.shared.UnableToDeleteDocumentException;
import org.abacus.transaction.shared.UnableToUpdateDetailException;
import org.abacus.transaction.shared.UnableToUpdateDocumentExpception;
import org.abacus.transaction.shared.entity.FinDetailEntity;
import org.abacus.transaction.shared.entity.FinDocumentEntity;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("finTransactionHandler")
public class FinTransactionHandlerImpl extends TraTransactionSupport<FinDocumentEntity, FinDetailEntity> {

	@Autowired
	private FinDetailRepository finDetailRepository;  

	@Autowired
	private FinDocumentRepository finDocumentRepository;  

	@Autowired
	private FinTransactionDao finTransactionDao;
	
	@Override
	protected TraTransactionDao<FinDocumentEntity, FinDetailEntity> getTransactionDao() {
		return finTransactionDao;
	}

	@Override
	protected TraDocumentRepository<FinDocumentEntity> getDocumentRepository() {
		return finDocumentRepository;
	}

	@Override
	protected TraDetailRepository<FinDetailEntity> getDetailRepository() {
		return finDetailRepository;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public DocumentUpdatedEvent<FinDocumentEntity> updateDocument(UpdateDocumentEvent<FinDocumentEntity> event) throws UnableToUpdateDocumentExpception {
		FinDocumentEntity doc = event.getDocument();
		doc = finDocumentRepository.save(doc);
		return new DocumentUpdatedEvent<FinDocumentEntity>(doc);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public DocumentDeletedEvent<FinDocumentEntity> deleteDocument(DeleteDocumentEvent<FinDocumentEntity> event) throws UnableToDeleteDocumentException {
		// Finans, Muhasebe kaydi varsa onlarda da silinecek, sorulacak
		FinDocumentEntity document = finDocumentRepository.findWithFetch(event.getDocument().getId());
		List<FinDetailEntity> detailList = finDetailRepository.findByDocumentId(event.getDocument().getId());
		savePointDetailList(detailList);
		for (FinDetailEntity dtl : detailList) {
			Boolean result = deleteDetailRecords(dtl);
			if (!result){
				throw new UnableToDeleteDetailException();
			}
		}
		finDocumentRepository.delete(document);
		return new DocumentDeletedEvent<>();
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public DocumentCanceledEvent<FinDocumentEntity> cancelDocument(CancelDocumentEvent<FinDocumentEntity> cancelDocumentEvent) throws UnableToUpdateDocumentExpception {
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public DetailCreatedEvent<FinDetailEntity> newDetail(CreateDetailEvent<FinDetailEntity> detailCreateEvent) throws UnableToCreateDetailException {
		
		Integer trStateDetail = detailCreateEvent.getDetail().getDocument().getTrStateDocument() * detailCreateEvent.getDetail().getDocument().getTypeEnum().getState();
		DetailCreatedEvent<FinDetailEntity> detailCreatedEvent=null;
		
		if(trStateDetail.equals(EnumList.TraState.INP.value())){
			detailCreateEvent.getDetail().setTrStateDetail(trStateDetail);
			detailCreatedEvent = super.newDetail(detailCreateEvent);
		}else if(trStateDetail.equals(EnumList.TraState.OUT.value())){
			detailCreateEvent.getDetail().setTrStateDetail(trStateDetail);
			detailCreatedEvent = super.newDetail(detailCreateEvent);
		}

		return detailCreatedEvent;
		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public DetailUpdatedEvent<FinDetailEntity> updateDetail(UpdateDetailEvent<FinDetailEntity> event) throws UnableToUpdateDetailException {
		FinDetailEntity det = event.getDetail();
		if (trackRefreshRequired(det)){
			Boolean result = deleteDetailRecords(event.getDetail());
			if (!result){
				throw new UnableToUpdateDetailException();
			}
			DetailCreatedEvent<FinDetailEntity> detailCreated = newDetail(new CreateDetailEvent<FinDetailEntity>(det, event.getUser()));
			det = detailCreated.getDetail();
		} else {
			det = finDetailRepository.save(det);
			det.savePoint();
		}
		return new DetailUpdatedEvent<FinDetailEntity>(det);
	}
	
	private Boolean trackRefreshRequired(FinDetailEntity dtl){
		FinDetailEntity old = dtl.getPoint();
		if (dtl.getItemDetailCount().compareTo(old.getItemDetailCount())!=0){
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

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public DetailDeletedEvent<FinDetailEntity> deleteDetail(DeleteDetailEvent<FinDetailEntity> event) throws UnableToDeleteDetailException {
		Boolean result = deleteDetailRecords(event.getDetail());
		if (!result){
			throw new UnableToDeleteDetailException();
		}
		return new DetailDeletedEvent<FinDetailEntity>();
	}
	
	private Boolean deleteDetailRecords(FinDetailEntity detail){
		try{

			finDetailRepository.delete(detail.getId());
			
			return true;
		} catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}
	

}
