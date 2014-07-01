package org.abacus.transaction.core.handler;

import java.util.List;

import org.abacus.definition.core.handler.DefTaskHandler;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.entity.DefTaskEntity;
import org.abacus.organization.core.util.OrganizationUtils;
import org.abacus.organization.shared.entity.OrganizationEntity;
import org.abacus.transaction.core.persistance.repository.FinDetailRepository;
import org.abacus.transaction.core.persistance.repository.FinDocumentRepository;
import org.abacus.transaction.core.persistance.repository.StkDetailRepository;
import org.abacus.transaction.core.persistance.repository.StkDocumentRepository;
import org.abacus.transaction.shared.entity.FinDetailEntity;
import org.abacus.transaction.shared.entity.FinDocumentEntity;
import org.abacus.transaction.shared.entity.StkDetailEntity;
import org.abacus.transaction.shared.entity.StkDocumentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("finIntegrationHandler")
public class FinIntegrationHandlerImpl implements FinIntegrationHandler {

	@Autowired
	private StkDetailRepository stkDetailRepository;  

	@Autowired
	private StkDocumentRepository stkDocumentRepository;  

	@Autowired
	private FinDetailRepository finDetailRepository;  

	@Autowired
	private FinDocumentRepository finDocumentRepository;  

	@Autowired
	private DefTaskHandler taskHandler;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public FinDocumentEntity createFinFromDocument(Long docId) {

		StkDocumentEntity stkDoc = stkDocumentRepository.findWithFetch(docId);
		
		FinDocumentEntity finDoc = new FinDocumentEntity(stkDoc);
		finDoc.setId(null);
		OrganizationEntity rootOrg = OrganizationUtils.findRootOrganization(finDoc.getOrganization());
		DefTaskEntity finTask = taskHandler.getTaskList(rootOrg.getId(), EnumList.DefTypeEnum.FIN_B).get(0);
		finDoc.setTask(finTask);
		finDoc.setTypeEnum(finTask.getType().getTypeEnum());
		finDocumentRepository.save(finDoc);

		List<StkDetailEntity> stkDetList = stkDetailRepository.findByDocumentId(docId);
		for (StkDetailEntity stkDet : stkDetList) {
			FinDetailEntity finDet = new FinDetailEntity(stkDet);
			finDet.setDocument(finDoc);
			finDetailRepository.save(finDet);
		}
		
		return finDoc;	
		
	}
	
}
