package org.abacus.transaction.core.handler;

import java.util.List;

import org.abacus.definition.core.handler.DefTaskHandler;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.entity.DefTaskEntity;
import org.abacus.transaction.core.persistance.repository.ReqDetailRepository;
import org.abacus.transaction.core.persistance.repository.ReqDocumentRepository;
import org.abacus.transaction.shared.entity.ReqDetailEntity;
import org.abacus.transaction.shared.entity.ReqDocumentEntity;
import org.abacus.transaction.shared.entity.StkDetailEntity;
import org.abacus.transaction.shared.entity.StkDocumentEntity;
import org.abacus.transaction.shared.event.ConfirmDocumentEvent;
import org.abacus.transaction.shared.event.CreateDetailEvent;
import org.abacus.transaction.shared.event.CreateDocumentEvent;
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
	

	//Required olursa hata olusuyor
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ReqDocumentEntity confirmDocument(ConfirmDocumentEvent confirmDocumentEvent) {
		
		ReqDocumentEntity reqDocument = confirmDocumentEvent.getReqDocumentEntity();
		String user = confirmDocumentEvent.getUser();
		String organization = confirmDocumentEvent.getOrganization();
		String fiscalYear = confirmDocumentEvent.getFiscalYear();
		String rootOrganization = confirmDocumentEvent.getRootOrganization();
		
		StkDocumentEntity stkDocument = new StkDocumentEntity();
		BeanUtils.copyProperties(reqDocument, stkDocument);
		stkDocument.setId(null);
		DefTaskEntity stkTask = taskHandler.getTaskList(rootOrganization, EnumList.DefTypeEnum.STK_IO_T).get(0);
		stkDocument.setTask(stkTask);
		stkTransactionHandler.newDocument(new CreateDocumentEvent<StkDocumentEntity>(stkDocument, user, organization, fiscalYear));
		
		List<ReqDetailEntity> reqDetails = reqDetailRepository.findByDocumentId(reqDocument.getId());
		
		for(ReqDetailEntity reqDetail : reqDetails){	
			reqDetail.setDocument(null);
			StkDetailEntity stkDetailEntity = new StkDetailEntity();
			BeanUtils.copyProperties(reqDetail, stkDetailEntity);
			stkDetailEntity.setDocument(stkDocument);
			stkTransactionHandler.newDetail(new CreateDetailEvent<StkDetailEntity>(stkDetailEntity, user));
		}
		
		reqDocument = this.updateDocumentRequestStatus(reqDocument, EnumList.RequestStatus.DONE, user);
		
		return reqDocument;
	}

	
	
}
