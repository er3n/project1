package org.abacus.transaction.core.handler;

import java.math.BigDecimal;
import java.util.List;

import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.constant.GlcConstant;
import org.abacus.transaction.core.persistance.FinTransactionDao;
import org.abacus.transaction.core.persistance.TraTransactionDao;
import org.abacus.transaction.core.persistance.repository.FinDetailRepository;
import org.abacus.transaction.core.persistance.repository.FinDocumentRepository;
import org.abacus.transaction.core.persistance.repository.StkDocumentRepository;
import org.abacus.transaction.core.persistance.repository.TraDetailRepository;
import org.abacus.transaction.core.persistance.repository.TraDocumentRepository;
import org.abacus.transaction.shared.UnableToCreateDetailException;
import org.abacus.transaction.shared.UnableToDeleteDetailException;
import org.abacus.transaction.shared.UnableToDeleteDocumentException;
import org.abacus.transaction.shared.UnableToFindGlcException;
import org.abacus.transaction.shared.UnableToUpdateDetailException;
import org.abacus.transaction.shared.UnableToUpdateDocumentExpception;
import org.abacus.transaction.shared.entity.FinDetailEntity;
import org.abacus.transaction.shared.entity.FinDocumentEntity;
import org.abacus.transaction.shared.event.CancelDocumentEvent;
import org.abacus.transaction.shared.event.CreateDetailEvent;
import org.abacus.transaction.shared.event.CreateDocumentEvent;
import org.abacus.transaction.shared.event.DeleteDetailEvent;
import org.abacus.transaction.shared.event.DeleteDocumentEvent;
import org.abacus.transaction.shared.event.DetailCreatedEvent;
import org.abacus.transaction.shared.event.DetailDeletedEvent;
import org.abacus.transaction.shared.event.DetailUpdatedEvent;
import org.abacus.transaction.shared.event.DocumentCanceledEvent;
import org.abacus.transaction.shared.event.DocumentCreatedEvent;
import org.abacus.transaction.shared.event.DocumentDeletedEvent;
import org.abacus.transaction.shared.event.DocumentUpdatedEvent;
import org.abacus.transaction.shared.event.ReadDetailEvent;
import org.abacus.transaction.shared.event.RequestReadDetailEvent;
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
	private StkDocumentRepository stkDocumentRepository;  

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
		//StkDocument RemoveRefInfo FIXME
		stkDocumentRepository.deleteRefFinInfo(event.getDocument().getId());
		finDetailRepository.updateRefFinInfo(event.getDocument().getId());

		// Finans, Muhasebe kaydi varsa onlarda da silinecek, sorulacak
		FinDocumentEntity document = finDocumentRepository.findWithFetch(event.getDocument().getId());
		List<FinDetailEntity> detailList = finDetailRepository.findByDocumentId(event.getDocument().getId());
		for (FinDetailEntity dtl : detailList) {
			Boolean result = deleteDetailRecords(dtl);
			if (!result){
				throw new UnableToDeleteDetailException();
			}
		}
		//Delete FinDocument
		finDocumentRepository.delete(document);
		return new DocumentDeletedEvent<>();
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public DocumentCanceledEvent<FinDocumentEntity> cancelDocument(CancelDocumentEvent<FinDocumentEntity> cancelDocumentEvent) throws UnableToUpdateDocumentExpception {
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public DetailCreatedEvent<FinDetailEntity> newDetail(CreateDetailEvent<FinDetailEntity> detailCreateEvent) throws UnableToCreateDetailException {

		FinDetailEntity detail = detailCreateEvent.getDetail();
		
		if (detailCreateEvent.getIsOppositeCreate()){
			detailCreateEvent.getDetail().setTrStateDetail(detail.getDocument().getTask().getType().getTrStateType()*(-1));
		}
		if (detail.getBsDocument()!=null){
			detail.setItem(detail.getBsDocument().getItem());
		}
		setDetailGLC(detail);

		DetailCreatedEvent<FinDetailEntity> detailCreatedEvent = super.newDetailSupport(detailCreateEvent);
		
		if (detailCreateEvent.getIsOppositeCreate()){
			createAccRecord(detail.getDocument());
		}
		
		return detailCreatedEvent;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	private void createAccRecord(FinDocumentEntity doc) throws UnableToCreateDetailException {
			List<FinDetailEntity> finDetList = finDetailRepository.findByDocumentId(doc.getId());
			BigDecimal totalAmount = BigDecimal.ZERO;
			for (FinDetailEntity finDet : finDetList) {
				if (finDet.getResource().equals(EnumList.DefTypeGroupEnum.ACC)){
					finDetailRepository.delete(finDet);	
				} else {
					totalAmount = totalAmount.add(finDet.getBaseDetailAmount());
				}
			}
			if (totalAmount.compareTo(BigDecimal.ZERO)!=0){
				FinDetailEntity infoDet = new FinDetailEntity();
				infoDet.createHook(doc.getUserCreated());
				infoDet.setDocument(doc);
				infoDet.setTrStateDetail(doc.getTask().getType().getTrStateType()); 
				infoDet.setItem(doc.getItem());
				infoDet.setItemDetailCount(BigDecimal.ONE);
				infoDet.setBaseDetailAmount(totalAmount);
				infoDet.setResource(EnumList.DefTypeGroupEnum.ACC);
				setDetailGLC(infoDet);

				super.newDetailSupport(new CreateDetailEvent<FinDetailEntity>(infoDet, false));
			}
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	private void setDetailGLC(FinDetailEntity detail) throws UnableToCreateDetailException{
		if (detail.getDocument().getTypeEnum().equals(EnumList.DefTypeEnum.FIN_J)){
			return;
		}
		EnumList.AccountGLC glc = GlcConstant.getAccountGLC(detail.getDocument().getTypeEnum(), detail.getItem().getType().getTypeEnum(), detail.getTrStateEnum());
		if (glc==null){
			throw new UnableToFindGlcException(detail.getDocument().getTypeEnum(), detail.getItem().getType().getTypeEnum(), detail.getTrStateEnum());
		}
		detail.setGlc(glc);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public DetailUpdatedEvent<FinDetailEntity> updateDetail(UpdateDetailEvent<FinDetailEntity> event) throws UnableToUpdateDetailException {
		FinDetailEntity detail = event.getDetail();
		if (detail.getBsDocument()!=null){
			detail.setItem(detail.getBsDocument().getItem());
		}
		detail = finDetailRepository.save(detail);
		if (event.getIsOppositeCreate()){
			createAccRecord(detail.getDocument());
		}
		return new DetailUpdatedEvent<FinDetailEntity>(detail);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public DetailDeletedEvent<FinDetailEntity> deleteDetail(DeleteDetailEvent<FinDetailEntity> event) throws UnableToDeleteDetailException {
		Boolean result = deleteDetailRecords(event.getDetail());
		if (!result){
			throw new UnableToDeleteDetailException();
		}
		if (event.getIsOppositeCreate()){
			FinDocumentEntity doc = finDocumentRepository.findOne(event.getDetail().getDocument().getId());
			createAccRecord(doc);
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
	
	@Override
	public DocumentCreatedEvent<FinDocumentEntity> newDocument(CreateDocumentEvent<FinDocumentEntity> event) {
		DocumentCreatedEvent<FinDocumentEntity> created = super.newDocumentSupport(event);
		FinDocumentEntity newDoc = created.getDocument();
		finDocumentRepository.save(newDoc);
		newDoc = finDocumentRepository.findOne(newDoc.getId());
		return created;
	}
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ReadDetailEvent<FinDetailEntity> readPRDetailList(RequestReadDetailEvent<FinDetailEntity> event) {
		List<FinDetailEntity> prDetailList = finDetailRepository.findPRDetailList(event.getDocumentId());
		return new ReadDetailEvent<FinDetailEntity>(prDetailList);
	}
}
