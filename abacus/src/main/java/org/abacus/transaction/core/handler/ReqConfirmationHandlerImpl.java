package org.abacus.transaction.core.handler;

import java.util.List;

import org.abacus.definition.core.handler.DefTaskHandler;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.entity.DefItemEntity;
import org.abacus.definition.shared.entity.DefTaskEntity;
import org.abacus.organization.shared.entity.FiscalYearEntity;
import org.abacus.organization.shared.entity.OrganizationEntity;
import org.abacus.transaction.core.persistance.repository.ReqDetailRepository;
import org.abacus.transaction.core.persistance.repository.ReqDocumentRepository;
import org.abacus.transaction.shared.UnableToChangeRequestStatus;
import org.abacus.transaction.shared.entity.ReqDetailEntity;
import org.abacus.transaction.shared.entity.ReqDocumentEntity;
import org.abacus.transaction.shared.entity.StkDetailEntity;
import org.abacus.transaction.shared.entity.StkDocumentEntity;
import org.abacus.transaction.shared.event.ConfirmDocumentEvent;
import org.abacus.transaction.shared.event.CreateDetailEvent;
import org.abacus.transaction.shared.event.CreateDocumentEvent;
import org.abacus.transaction.shared.holder.ReqPurcVendorHolder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("reqConfirmationHandler")
public class ReqConfirmationHandlerImpl implements ReqConfirmationHandler {

	@Autowired
	private ReqDocumentRepository reqDocumentRepository;
	
	@Autowired
	private ReqDetailRepository reqDetailRepository;
	
	@Autowired
	private DefTaskHandler taskHandler;
	
	@Autowired
	@Qualifier(value="stkTransactionHandler")
	private TraTransactionHandler<StkDocumentEntity, StkDetailEntity> stkTransactionHandler; 
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public ReqDocumentEntity requestDocument(ReqDocumentEntity document, String user) {
		document = this.updateDocumentRequestStatus(document, EnumList.RequestStatus.REQUEST, user);
		return document;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public ReqDocumentEntity cancelDocument(ReqDocumentEntity document, String user) {
		document = this.updateDocumentRequestStatus(document, EnumList.RequestStatus.CANCEL, user);
		return document;
	}
	
	private ReqDocumentEntity updateDocumentRequestStatus(ReqDocumentEntity document,EnumList.RequestStatus requestStatus,String user){
		document.setRequestStatus(requestStatus);
		document.updateHook(user);
		document = reqDocumentRepository.save(document);
		return document;
	}
	

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public ReqDocumentEntity confirmDocument(ConfirmDocumentEvent confirmDocumentEvent) {
		
		ReqDocumentEntity reqDocument = confirmDocumentEvent.getReqDocumentEntity();
		String user = confirmDocumentEvent.getUser();
		OrganizationEntity organization = confirmDocumentEvent.getOrganization();
		FiscalYearEntity fiscalYear = confirmDocumentEvent.getFiscalYear();
		DefItemEntity vendor = confirmDocumentEvent.getVendor();
		
		StkDocumentEntity stkDocument = new StkDocumentEntity();
		BeanUtils.copyProperties(reqDocument, stkDocument);
		stkDocument.setId(null);
		
		EnumList.DefTypeEnum proceedingTaskType = null;
		if(reqDocument.getTask().getType().equals(EnumList.DefTypeEnum.REQ_IO_T)){
			proceedingTaskType = EnumList.DefTypeEnum.STK_IO_T;
		}else{
			proceedingTaskType = EnumList.DefTypeEnum.STK_WB_I; 
		}
		
		DefTaskEntity stkTask = taskHandler.getTaskList(organization, proceedingTaskType).get(0);
		stkDocument.setTask(stkTask);
		stkTransactionHandler.newDocument(new CreateDocumentEvent<StkDocumentEntity>(stkDocument, user, organization, fiscalYear));
		
		List<ReqDetailEntity> reqDetails = null;
		if(vendor == null) {
			reqDetails = reqDetailRepository.findByDocumentId(reqDocument.getId());
		}else{
			reqDetails = reqDetailRepository.findByDocumentAndSelectedVendor(reqDocument.getId(),vendor.getId());
		}
		
		
		for(ReqDetailEntity reqDetail : reqDetails){	
			StkDetailEntity stkDetailEntity = new StkDetailEntity(reqDetail,stkDocument);
			stkTransactionHandler.newDetail(new CreateDetailEvent<StkDetailEntity>(stkDetailEntity, user));
		}
		
		reqDocument = this.updateDocumentRequestStatus(reqDocument, EnumList.RequestStatus.DONE, user);
		
		return reqDocument;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void reviewDocument(ReqDocumentEntity document, String user) {
		this.updateDocumentRequestStatus(document, EnumList.RequestStatus.REVIEW, user);
		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void partiallyDoneDocument(ReqDocumentEntity document, String user) {
		this.updateDocumentRequestStatus(document, EnumList.RequestStatus.PARTIALLY, user);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void backToReviewDocument(ReqDocumentEntity document, String user) {
		Boolean isAnyStkDocumentCreated = reqDetailRepository.isAnyStkDocumentCreated(document.getId());
		if(isAnyStkDocumentCreated){
			throw new UnableToChangeRequestStatus("unableToChangeRequestStatusWbChanged");
		}
		this.updateDocumentRequestStatus(document, EnumList.RequestStatus.REVIEW, user);
		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void backToRequestDocument(ReqDocumentEntity document, String user) {
		this.updateDocumentRequestStatus(document, EnumList.RequestStatus.REQUEST, user);
	}
	
	
}
