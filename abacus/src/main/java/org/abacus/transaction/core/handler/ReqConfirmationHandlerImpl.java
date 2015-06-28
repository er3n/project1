package org.abacus.transaction.core.handler;

import org.abacus.definition.core.handler.DefTaskHandler;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.entity.DefItemEntity;
import org.abacus.organization.shared.entity.FiscalPeriodEntity;
import org.abacus.organization.shared.entity.FiscalYearEntity;
import org.abacus.organization.shared.entity.OrganizationEntity;
import org.abacus.transaction.core.persistance.repository.ReqDetailRepository;
import org.abacus.transaction.core.persistance.repository.ReqDocumentRepository;
import org.abacus.transaction.shared.UnableToChangeRequestStatus;
import org.abacus.transaction.shared.entity.ReqDocumentEntity;
import org.abacus.transaction.shared.entity.StkDetailEntity;
import org.abacus.transaction.shared.entity.StkDocumentEntity;
import org.abacus.transaction.shared.event.ConfirmDocumentEvent;
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
	private TraIntegrationHandler traIntegrationHandler;
	
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
	public StkDocumentEntity confirmDocument(ConfirmDocumentEvent confirmDocumentEvent) {
		
		ReqDocumentEntity reqDocument = confirmDocumentEvent.getReqDocumentEntity();
		
		StkDocumentEntity stkDocument = this.confirmPartialDocument(confirmDocumentEvent);
		this.updateDocumentRequestStatus(reqDocument, EnumList.RequestStatus.DONE, reqDocument.getUserCreated());
		
		return stkDocument;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public StkDocumentEntity confirmPartialDocument(ConfirmDocumentEvent confirmDocumentEvent) {
		ReqDocumentEntity reqDocument = confirmDocumentEvent.getReqDocumentEntity();
		DefItemEntity vendor = confirmDocumentEvent.getVendor();
		FiscalPeriodEntity period2 = confirmDocumentEvent.getFiscalPeriod2();
		StkDocumentEntity stkDocument = traIntegrationHandler.createStkFromReq(reqDocument.getId(), period2, vendor);
		
		return stkDocument;
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
