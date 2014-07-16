package org.abacus.test.fixture;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.abacus.definition.core.handler.DefTaskHandler;
import org.abacus.definition.core.persistance.DefItemDao;
import org.abacus.definition.core.persistance.repository.DefItemRepository;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.entity.DefItemEntity;
import org.abacus.definition.shared.entity.DefTaskEntity;
import org.abacus.definition.shared.holder.ItemSearchCriteria;
import org.abacus.organization.core.persistance.repository.DepartmentRepository;
import org.abacus.organization.core.util.OrganizationUtils;
import org.abacus.organization.shared.entity.DepartmentEntity;
import org.abacus.organization.shared.entity.FiscalYearEntity;
import org.abacus.organization.shared.entity.OrganizationEntity;
import org.abacus.transaction.shared.entity.StkDetailEntity;
import org.abacus.transaction.shared.entity.StkDocumentEntity;
import org.abacus.transaction.shared.entity.TraDocumentEntity;
import org.abacus.transaction.shared.event.CreateDetailEvent;
import org.abacus.transaction.shared.event.CreateDocumentEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionFixture {

	@Autowired
	private DefTaskHandler taskRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private DefItemRepository itemRepository;

	@Autowired
	private DefItemDao itemDao;
	
	private void enrichDocument(TraDocumentEntity entity, OrganizationEntity organization, EnumList.DefTypeEnum documentType) {
		entity.setDocDate(Calendar.getInstance().getTime());
		entity.setDocNo("123456");
		entity.setDocNote("New stock item added");
		List<DefTaskEntity> taskList = taskRepository.getTaskList(organization, documentType);
		entity.setTask(taskList.get(0));
	}

	private void enrichDetail(StkDetailEntity detail, TraDocumentEntity document, String user, BigDecimal itemDetailCount) {

		DepartmentEntity department = departmentRepository.findByOrganizationAndGroup(document.getOrganization().getId(), EnumList.OrgDepartmentGroupEnum.S).get(0);
		detail.setDepartment(department);

		detail.setDocument(document);

		DefItemEntity item = itemDao.requestItems(new ItemSearchCriteria(document.getOrganization(), EnumList.DefTypeEnum.ITM_SR_ST, EnumList.DefItemClassEnum.STK_M)).get(0);

		detail.setItem(item);
		detail.setDueDate(document.getDocDate());
		detail.setItemDetailCount(itemDetailCount);
		detail.setBaseDetailAmount(itemDetailCount.multiply(new BigDecimal(1000)));

		detail.setItemUnit(item.getItemUnitSet().iterator().next().getUnitCode());

	}

	public CreateDocumentEvent<StkDocumentEntity> newDocument(String user, OrganizationEntity organization, EnumList.DefTypeEnum documentType, FiscalYearEntity fiscalYear) {
		StkDocumentEntity document = new StkDocumentEntity();

		enrichDocument(document, organization, documentType); 

		CreateDocumentEvent<StkDocumentEntity> event = new CreateDocumentEvent<StkDocumentEntity>(document, user, organization, fiscalYear);

		return event;
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CreateDetailEvent<StkDetailEntity> newDetail(TraDocumentEntity document, String user, BigDecimal itemDetailCount) {
		StkDetailEntity detail = new StkDetailEntity();

		enrichDetail(detail, document, user, itemDetailCount);
		detail.setDetNote("Stok not");
		detail.setBatchDetailNo("BathNo");

		return new CreateDetailEvent<StkDetailEntity>(detail, user);
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CreateDetailEvent<StkDetailEntity> newTransfer(TraDocumentEntity transferDocument, String user, BigDecimal bigDecimal) {
		CreateDetailEvent<StkDetailEntity> createDetailEvent = this.newDetail(transferDocument, user, bigDecimal);
		
		StkDetailEntity stkDetailEntity = (StkDetailEntity)createDetailEvent.getDetail();
		
		DepartmentEntity departmentOpp = departmentRepository.findByOrganizationAndGroup(transferDocument.getOrganization().getId(), EnumList.OrgDepartmentGroupEnum.S).get(1);
		stkDetailEntity.setDepartmentOpp(departmentOpp);
		
		return createDetailEvent;
	}

}
