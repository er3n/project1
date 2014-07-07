package org.abacus.transaction.core.handler;

import java.math.BigDecimal;
import java.util.List;

import org.abacus.definition.core.handler.DefTaskHandler;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.entity.DefTaskEntity;
import org.abacus.organization.core.util.OrganizationUtils;
import org.abacus.organization.shared.entity.DepartmentEntity;
import org.abacus.organization.shared.entity.OrganizationEntity;
import org.abacus.transaction.core.persistance.repository.StkDetailRepository;
import org.abacus.transaction.core.persistance.repository.StkDocumentRepository;
import org.abacus.transaction.shared.entity.FinDetailEntity;
import org.abacus.transaction.shared.entity.FinDocumentEntity;
import org.abacus.transaction.shared.entity.StkDetailEntity;
import org.abacus.transaction.shared.entity.StkDocumentEntity;
import org.abacus.transaction.shared.event.CreateDetailEvent;
import org.abacus.transaction.shared.event.CreateDocumentEvent;
import org.abacus.transaction.shared.event.UpdateDocumentEvent;
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

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public FinDocumentEntity createFinFromStk(Long docId) {

		//Create FinDocument
		StkDocumentEntity stkDoc = stkDocumentRepository.findWithFetch(docId);
		FinDocumentEntity finDoc = new FinDocumentEntity(stkDoc);
		finDoc.setId(null);
		OrganizationEntity rootOrg = OrganizationUtils.findRootOrganization(finDoc.getOrganization());
		DefTaskEntity finTask = taskHandler.getTaskList(rootOrg.getId(), EnumList.DefTypeEnum.FIN_B).get(0);
		finDoc.setTask(finTask);
		finDoc.setTypeEnum(finTask.getType().getTypeEnum());
		finTransactionHandler.newDocument(new CreateDocumentEvent<FinDocumentEntity>(finDoc));
		//Update Reference
		stkDoc.setRefFinDocumentId(finDoc.getId());
		stkTransactionHandler.updateDocument(new UpdateDocumentEvent<StkDocumentEntity>(stkDoc));
		
		//Convert FinDetail
		DepartmentEntity cakmaDepartment = null; //FIXME: documentte department gerekli gibi ???
		BigDecimal totalAmount = BigDecimal.ZERO;
		List<StkDetailEntity> stkDetList = stkDetailRepository.findByDocumentId(docId);
		for (StkDetailEntity stkDet : stkDetList) {
			FinDetailEntity finDet = new FinDetailEntity(stkDet);
			finDet.setDocument(finDoc);
			finDet.setTrStateDetail(finDoc.getTask().getType().getTrStateType()); 
			finTransactionHandler.newDetail(new CreateDetailEvent<FinDetailEntity>(finDet, false));
			totalAmount = totalAmount.add(finDet.getBaseDetailAmount());
			cakmaDepartment = finDet.getDepartment();
		}
		
		//Create FinDetail Info
		FinDetailEntity infoDet = new FinDetailEntity();
		infoDet.createHook(finDoc.getUserCreated());
		infoDet.setDocument(finDoc);
		infoDet.setTrStateDetail(finDoc.getTask().getType().getTrStateType()*(-1)); //Opposite State
		infoDet.setDepartment(cakmaDepartment);//FIXME:
		infoDet.setItem(finDoc.getItem());
		infoDet.setItemDetailCount(BigDecimal.ONE);
		infoDet.setBaseDetailAmount(totalAmount);
		infoDet.setResource(EnumList.DefTypeGroupEnum.ACC);
		finTransactionHandler.newDetail(new CreateDetailEvent<FinDetailEntity>(infoDet, false));
		
		return finDoc;	
	}
	
}
