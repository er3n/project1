package org.abacus.transaction.core.handler;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.abacus.definition.core.handler.DefTaskHandler;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.entity.DefItemEntity;
import org.abacus.definition.shared.entity.DefTaskEntity;
import org.abacus.organization.shared.entity.DepartmentEntity;
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
import org.abacus.transaction.shared.holder.SalesDocumentHolder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public FinDocumentEntity createFinFromStk(Long docId) {
		StkDocumentEntity stkDoc = stkDocumentRepository.findWithFetch(docId);
		EnumList.DefTypeEnum finDocType = null;
		if (stkDoc.getTypeEnum().equals(EnumList.DefTypeEnum.STK_WB_I)){//Alis Irsaliye
			finDocType = EnumList.DefTypeEnum.FIN_B;
		} else if (stkDoc.getTypeEnum().equals(EnumList.DefTypeEnum.STK_WB_O)){//Satis Irsaliye
			finDocType = EnumList.DefTypeEnum.FIN_S;
		} else if (stkDoc.getTypeEnum().equals(EnumList.DefTypeEnum.STK_IO_O)){//Stok Cikis
			finDocType = EnumList.DefTypeEnum.FIN_J_SC;
		}
		if (finDocType==null){
			return null;
		}
		
		//Create FinDocument
		FinDocumentEntity finDoc = new FinDocumentEntity();
		BeanUtils.copyProperties(stkDoc, finDoc);
		finDoc.setId(null);

		DefTaskEntity finTask = taskHandler.getTaskList(finDoc.getOrganization(), finDocType).get(0); 
		finDoc.setTask(finTask);
		finDoc.setTypeEnum(finTask.getType().getTypeEnum());
		finTransactionHandler.newDocument(new CreateDocumentEvent<FinDocumentEntity>(finDoc));
		
		Map<DefItemEntity, BigDecimal> stkItemMap = new HashMap<DefItemEntity, BigDecimal>();
		//Convert FinDetail
		BigDecimal totalAmount = BigDecimal.ZERO;
		List<StkDetailEntity> stkDetList = stkDetailRepository.findByDocumentId(docId);
		for (StkDetailEntity stkDet : stkDetList) {
			StkDetailEntity tempStk = new StkDetailEntity();
			BeanUtils.copyProperties(stkDet, tempStk);
			tempStk.setDocument(null); 
			FinDetailEntity finDet = new FinDetailEntity();
			BeanUtils.copyProperties(tempStk, finDet);
			finDet.setDocument(finDoc);
			finDet.setTrStateDetail(finDoc.getTask().getType().getTrStateType()); 
			finTransactionHandler.newDetail(new CreateDetailEvent<FinDetailEntity>(finDet, false));
			totalAmount = totalAmount.add(finDet.getBaseDetailAmount());
			if (stkDoc.getTypeEnum().name().startsWith(EnumList.DefTypeEnum.STK_IO_O.name())){
				BigDecimal existSum = stkItemMap.get(finDet.getItem());
				stkItemMap.put(finDet.getItem(), finDet.getBaseDetailAmount().add(existSum!=null?existSum:BigDecimal.ZERO));
			}
		}
		
		if (stkDoc.getTypeEnum().name().startsWith(EnumList.DefTypeEnum.STK_WB.name())){
			//Create Bill/Sales FinDetail Info
			FinDetailEntity infoDet = new FinDetailEntity();
			infoDet.createHook(finDoc.getUserCreated());
			infoDet.setDocument(finDoc);
			infoDet.setTrStateDetail(finDoc.getTask().getType().getTrStateType()*(-1)); //Opposite State
			infoDet.setItem(finDoc.getItem());
			infoDet.setItemDetailCount(BigDecimal.ONE);
			infoDet.setBaseDetailAmount(totalAmount);
			infoDet.setResource(EnumList.DefTypeGroupEnum.ACC);
			infoDet.setDetNote("Not:"+finDoc.getDocNo()+":"+finDoc.getDocDate().toString());
			finTransactionHandler.newDetail(new CreateDetailEvent<FinDetailEntity>(infoDet, false));
		}

		if (stkDoc.getTypeEnum().name().startsWith(EnumList.DefTypeEnum.STK_IO_O.name()) && !stkItemMap.isEmpty()){
			//Create Stk Cost FinDetail Info
			for (DefItemEntity item : stkItemMap.keySet()) {
				FinDetailEntity infoDet = new FinDetailEntity();
				infoDet.createHook(finDoc.getUserCreated());
				infoDet.setDocument(finDoc);
				infoDet.setTrStateDetail(finDoc.getTask().getType().getTrStateType()*(-1)); //Opposite State
				infoDet.setItem(item);
				infoDet.setItemDetailCount(BigDecimal.ONE);
				infoDet.setBaseDetailAmount(stkItemMap.get(item));
				infoDet.setResource(EnumList.DefTypeGroupEnum.ACC);
				finTransactionHandler.newDetail(new CreateDetailEvent<FinDetailEntity>(infoDet, false));
				infoDet.setDetNote("Not:"+finDoc.getDocNo()+":"+finDoc.getDocDate().toString());
			}
		}
		
		//Update Reference irsaliyeye fatura idsi
		stkDoc.setRefFinDocumentId(finDoc.getId());
		stkTransactionHandler.updateDocument(new UpdateDocumentEvent<StkDocumentEntity>(stkDoc));

		return finDoc;	
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public StkDocumentEntity createSalesDocument(List<SalesDocumentHolder> holderList, DefItemEntity customer, FiscalPeriodEntity fisPeriod2, DepartmentEntity department, Date transactionDate){
		
		OrganizationEntity organization = fisPeriod2.getFiscalYear().getOrganization();
		
		StkDocumentEntity stkDocument = new StkDocumentEntity();
		stkDocument.setFiscalPeriod2(fisPeriod2);
		stkDocument.setDocDate(transactionDate);
		stkDocument.setDocNo("SLS:"+  stkDocument.getDocDate().toGMTString().substring(0, 11).trim());
		stkDocument.setItem(customer);
		stkDocument.setOrganization(fisPeriod2.getFiscalYear().getOrganization());
		
		EnumList.DefTypeEnum proceedingTaskType = EnumList.DefTypeEnum.STK_WB_O;
		DefTaskEntity stkTask = taskHandler.getTaskList(organization, proceedingTaskType).get(0);
		stkDocument.setTask(stkTask);
		stkTransactionHandler.newDocument(new CreateDocumentEvent<StkDocumentEntity>(stkDocument));
		
		for(SalesDocumentHolder holder : holderList){	
			StkDetailEntity stkDetailEntity = new StkDetailEntity();
			stkDetailEntity.setDocument(stkDocument);
			stkDetailEntity.setItem(holder.getItem());
			stkDetailEntity.setTrStateDetail(-1);
			stkDetailEntity.setDepartment(department);
			
			stkDetailEntity.setBaseDetailCount(holder.getCount());
			stkDetailEntity.setItemDetailCount(holder.getCount());

			stkDetailEntity.setUnitDetailPrice(holder.getUnitPrice());
			stkDetailEntity.setBaseDetailAmount(holder.getCount().multiply(holder.getUnitPrice()));
			stkDetailEntity.setDetNote("Not:"+stkDocument.getDocNo()+":"+stkDocument.getDocDate().toString());
			stkTransactionHandler.newDetail(new CreateDetailEvent<StkDetailEntity>(stkDetailEntity, stkDocument.getUserCreated()));
		}		
		createFinFromStk(stkDocument.getId());
		return stkDocument;
	}

}
