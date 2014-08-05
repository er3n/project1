package org.abacus.transaction.core.handler;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EnumType;

import org.abacus.definition.core.handler.DefTaskHandler;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.constant.EnumList.DefTypeEnum;
import org.abacus.definition.shared.entity.DefItemEntity;
import org.abacus.definition.shared.entity.DefTaskEntity;
import org.abacus.organization.shared.entity.FiscalPeriodEntity;
import org.abacus.organization.shared.entity.OrganizationEntity;
import org.abacus.transaction.core.persistance.repository.ReqDetailRepository;
import org.abacus.transaction.core.persistance.repository.ReqDocumentRepository;
import org.abacus.transaction.core.persistance.repository.StkDetailRepository;
import org.abacus.transaction.core.persistance.repository.StkDocumentRepository;
import org.abacus.transaction.shared.entity.FinDetailEntity;
import org.abacus.transaction.shared.entity.FinDocumentEntity;
import org.abacus.transaction.shared.entity.ReqDetailEntity;
import org.abacus.transaction.shared.entity.ReqDocumentEntity;
import org.abacus.transaction.shared.entity.StkDetailEntity;
import org.abacus.transaction.shared.entity.StkDocumentEntity;
import org.abacus.transaction.shared.event.CreateDetailEvent;
import org.abacus.transaction.shared.event.CreateDocumentEvent;
import org.abacus.transaction.shared.event.UpdateDocumentEvent;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping;

@Service("traIntegrationHandler")
public class TraIntegrationHandlerImpl implements TraIntegrationHandler {

	@Autowired
	private StkDetailRepository stkDetailRepository;  

	@Autowired
	private StkDocumentRepository stkDocumentRepository;  

	@Autowired
	private TraTransactionHandler<FinDocumentEntity, FinDetailEntity> finTransactionHandler;  

	@Autowired
	private TraTransactionHandler<StkDocumentEntity, StkDetailEntity> stkTransactionHandler;  

	@Autowired
	private DefTaskHandler taskHandler;
	
	@Autowired
	private ReqDetailRepository reqDetailRepository;
	
	@Autowired
	private ReqDocumentRepository reqDocumentRepository;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public StkDocumentEntity createStkFromReq(Long docId, FiscalPeriodEntity fisPeriod2, DefItemEntity vendor) {
		
		ReqDocumentEntity reqDocument = reqDocumentRepository.findWithFetch(docId);
		
		StkDocumentEntity stkDocument = new StkDocumentEntity();
		BeanUtils.copyProperties(reqDocument, stkDocument);
		stkDocument.setId(null);
		stkDocument.setItem(vendor);

		OrganizationEntity organization = reqDocument.getOrganization();
		if (fisPeriod2!=null && !reqDocument.getFiscalPeriod2().getId().equals(fisPeriod2.getId())){
			organization = fisPeriod2.getFiscalYear().getOrganization();
			stkDocument.setOrganization(organization);
			stkDocument.setFiscalPeriod1(null);
			stkDocument.setFiscalPeriod2(fisPeriod2);
		}
		
		EnumList.DefTypeEnum proceedingTaskType = null;
		if(reqDocument.getTask().getType().getId().equals(EnumList.DefTypeEnum.REQ_IO_T.name())){
			proceedingTaskType = EnumList.DefTypeEnum.STK_IO_T;
		}else{
			proceedingTaskType = EnumList.DefTypeEnum.STK_WB_I; 
		}
		
		DefTaskEntity stkTask = taskHandler.getTaskList(organization, proceedingTaskType).get(0);
		stkDocument.setTask(stkTask);
		stkTransactionHandler.newDocument(new CreateDocumentEvent<StkDocumentEntity>(stkDocument));
		
		List<ReqDetailEntity> reqDetails = null;
		BigDecimal baseDetailCount = null;
		if(vendor == null) {
			reqDetails = reqDetailRepository.findByDocumentId(reqDocument.getId());
		}else{
			reqDetails = reqDetailRepository.findByDocumentAndSelectedVendor(reqDocument.getId(),vendor.getId());
		}
		
		
		for(ReqDetailEntity reqDetail : reqDetails){	
			StkDetailEntity stkDetailEntity = new StkDetailEntity(reqDetail,stkDocument);
			stkTransactionHandler.newDetail(new CreateDetailEvent<StkDetailEntity>(stkDetailEntity, stkDocument.getUserCreated()));
		}		
		
		return stkDocument;
		
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public FinDocumentEntity createFinFromStk(Long docId, EnumList.DefTypeEnum stkDocType) {

		//Create FinDocument
		StkDocumentEntity stkDoc = stkDocumentRepository.findWithFetch(docId);
		FinDocumentEntity finDoc = new FinDocumentEntity(stkDoc);
		finDoc.setId(null);
		EnumList.DefTypeEnum finDocType = null;
		if (stkDocType.equals(EnumList.DefTypeEnum.STK_WB_I)){
			finDocType = EnumList.DefTypeEnum.FIN_B;
		} else if (stkDocType.equals(EnumList.DefTypeEnum.STK_WB_O)){
			finDocType = EnumList.DefTypeEnum.FIN_S;
		}
		if (finDocType==null){
			return null;
		}
		DefTaskEntity finTask = taskHandler.getTaskList(finDoc.getOrganization(), finDocType).get(0); 
		finDoc.setTask(finTask);
		finDoc.setTypeEnum(finTask.getType().getTypeEnum());
		finTransactionHandler.newDocument(new CreateDocumentEvent<FinDocumentEntity>(finDoc));
		//Update Reference irsaliyeye fatura idsi
		stkDoc.setRefFinDocumentId(finDoc.getId());
		stkTransactionHandler.updateDocument(new UpdateDocumentEvent<StkDocumentEntity>(stkDoc));
		
		//Convert FinDetail
		BigDecimal totalAmount = BigDecimal.ZERO;
		List<StkDetailEntity> stkDetList = stkDetailRepository.findByDocumentId(docId);
		for (StkDetailEntity stkDet : stkDetList) {
			FinDetailEntity finDet = new FinDetailEntity(stkDet);
			finDet.setDocument(finDoc);
			finDet.setTrStateDetail(finDoc.getTask().getType().getTrStateType()); 
			finTransactionHandler.newDetail(new CreateDetailEvent<FinDetailEntity>(finDet, false));
			totalAmount = totalAmount.add(finDet.getBaseDetailAmount());
		}
		
		//Create FinDetail Info
		FinDetailEntity infoDet = new FinDetailEntity();
		infoDet.createHook(finDoc.getUserCreated());
		infoDet.setDocument(finDoc);
		infoDet.setTrStateDetail(finDoc.getTask().getType().getTrStateType()*(-1)); //Opposite State
		infoDet.setItem(finDoc.getItem());
		infoDet.setItemDetailCount(BigDecimal.ONE);
		infoDet.setBaseDetailAmount(totalAmount);
		infoDet.setResource(EnumList.DefTypeGroupEnum.ACC);
		finTransactionHandler.newDetail(new CreateDetailEvent<FinDetailEntity>(infoDet, false));
		
		return finDoc;	
	}
	
}
