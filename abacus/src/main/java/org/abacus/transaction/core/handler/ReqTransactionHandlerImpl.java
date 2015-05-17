package org.abacus.transaction.core.handler;

import java.util.List;

import org.abacus.definition.shared.constant.EnumList;
import org.abacus.transaction.core.persistance.ReqTransactionDao;
import org.abacus.transaction.core.persistance.TraTransactionDao;
import org.abacus.transaction.core.persistance.repository.ReqDetailRepository;
import org.abacus.transaction.core.persistance.repository.ReqDocumentRepository;
import org.abacus.transaction.core.persistance.repository.StkDocumentRepository;
import org.abacus.transaction.core.persistance.repository.TraDetailRepository;
import org.abacus.transaction.core.persistance.repository.TraDocumentRepository;
import org.abacus.transaction.shared.UnableToCreateDetailException;
import org.abacus.transaction.shared.UnableToCreateDocumentException;
import org.abacus.transaction.shared.UnableToDeleteDetailException;
import org.abacus.transaction.shared.UnableToDeleteDocumentException;
import org.abacus.transaction.shared.UnableToUpdateDetailException;
import org.abacus.transaction.shared.UnableToUpdateDocumentExpception;
import org.abacus.transaction.shared.entity.ReqDetailEntity;
import org.abacus.transaction.shared.entity.ReqDocumentEntity;
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
import org.abacus.transaction.shared.event.UpdateDetailEvent;
import org.abacus.transaction.shared.event.UpdateDocumentEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("reqTransactionHandler")
public class ReqTransactionHandlerImpl extends TraTransactionSupport<ReqDocumentEntity, ReqDetailEntity> {

	@Autowired
	private ReqDetailRepository reqDetailRepository;  

	@Autowired
	private ReqDocumentRepository reqDocumentRepository;  

	@Autowired
	private StkDocumentRepository stkDocumentRepository;  

	@Autowired
	private ReqTransactionDao reqTransactionDao;
	
	@Override
	protected TraTransactionDao<ReqDocumentEntity, ReqDetailEntity> getTransactionDao() {
		return reqTransactionDao;
	}
 
	@Override
	protected TraDocumentRepository<ReqDocumentEntity> getDocumentRepository() {
		return reqDocumentRepository;
	}
	
	@Override
	protected TraDetailRepository<ReqDetailEntity> getDetailRepository() {
		return reqDetailRepository;
	}
	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public DocumentCreatedEvent<ReqDocumentEntity> newDocument(CreateDocumentEvent<ReqDocumentEntity> event) {
		ReqDocumentEntity document = event.getDocument();
		
		
		
		if(document.getDepartment() != null && document.getDepartmentOpp() != null && document.getDepartment().getId().equals(document.getDepartmentOpp().getId())){
			throw new UnableToCreateDocumentException("departmentsCanNotBeEqual");
		}
		
		document.setRequestStatus(EnumList.RequestStatus.PREPARE);
		return super.newDocumentSupport(event);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public DocumentUpdatedEvent<ReqDocumentEntity> updateDocument(UpdateDocumentEvent<ReqDocumentEntity> event) throws UnableToUpdateDocumentExpception {
		ReqDocumentEntity doc = event.getDocument();
		doc = reqDocumentRepository.saveTraDocument(doc);
		return new DocumentUpdatedEvent<ReqDocumentEntity>(doc);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public DocumentDeletedEvent<ReqDocumentEntity> deleteDocument(DeleteDocumentEvent<ReqDocumentEntity> event) throws UnableToDeleteDocumentException {
		ReqDocumentEntity document = reqDocumentRepository.findWithFetch(event.getDocument().getId());
		List<ReqDetailEntity> detailList = reqDetailRepository.findByDocumentId(event.getDocument().getId());
		
		for (ReqDetailEntity dtl : detailList) {
			reqDetailRepository.delete(dtl.getId());
		}
		reqDocumentRepository.delete(document.getId());
		return new DocumentDeletedEvent<>();
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public DetailCreatedEvent<ReqDetailEntity> newDetail(CreateDetailEvent<ReqDetailEntity> detailCreateEvent) throws UnableToCreateDetailException {
		Integer trStateDetail = detailCreateEvent.getDetail().getTrStateDetail();
		if (trStateDetail==null){
			trStateDetail = detailCreateEvent.getDetail().getDocument().getTypeEnum().getState();
			detailCreateEvent.getDetail().setTrStateDetail(trStateDetail);
		}
		
		ReqDetailEntity detail = detailCreateEvent.getDetail();
		ReqDocumentEntity document = (ReqDocumentEntity) detail.getDocument();
		if(document.getTypeEnum().equals(EnumList.DefTypeEnum.REQ_IO_P)){
			detail.setDepartment(document.getDepartmentOpp());
		}else{
			detail.setDepartment(document.getDepartment());
			detail.setDepartmentOpp(document.getDepartmentOpp());
		}
		
		
		DetailCreatedEvent<ReqDetailEntity> detailCreatedEvent=null;
		detailCreatedEvent = super.newDetailSupport(detailCreateEvent);
		return detailCreatedEvent;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public DetailUpdatedEvent<ReqDetailEntity> updateDetail(UpdateDetailEvent<ReqDetailEntity> event) throws UnableToUpdateDetailException {
		ReqDetailEntity detail = reqDetailRepository.saveTraDetail(event.getDetail());
		return new DetailUpdatedEvent<ReqDetailEntity>(detail);
	}
	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public DetailDeletedEvent<ReqDetailEntity> deleteDetail(DeleteDetailEvent<ReqDetailEntity> event) throws UnableToDeleteDetailException {
		reqDetailRepository.delete(event.getDetail());
		return new DetailDeletedEvent<ReqDetailEntity>();
	}
	
	@Override
	@Deprecated
	public DocumentCanceledEvent<ReqDocumentEntity> cancelDocument(CancelDocumentEvent<ReqDocumentEntity> cancelDocumentEvent) throws UnableToUpdateDocumentExpception {
		return null;
	}	

}
