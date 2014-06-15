package org.abacus.test.fixture;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.abacus.definition.core.persistance.DefItemDao;
import org.abacus.definition.core.persistance.repository.DefItemRepository;
import org.abacus.definition.core.persistance.repository.DefTaskRepository;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.entity.DefItemEntity;
import org.abacus.definition.shared.entity.DefTaskEntity;
import org.abacus.definition.shared.holder.ItemSearchCriteria;
import org.abacus.organization.core.persistance.repository.DepartmentRepository;
import org.abacus.organization.shared.entity.DepartmentEntity;
import org.abacus.transaction.shared.entity.StkDetailEntity;
import org.abacus.transaction.shared.entity.StkDocumentEntity;
import org.abacus.transaction.shared.entity.TraDetailEntity;
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
	private DefTaskRepository taskRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private DefItemRepository itemRepository;

	@Autowired
	private DefItemDao itemDao;

	private void enrichDocument(TraDocumentEntity entity, String organization, EnumList.DefTypeEnum documentType) {
		entity.setDocDate(Calendar.getInstance().getTime());
		entity.setDocNo("123456");
		entity.setDocNote("New stock item added");
		List<DefTaskEntity> taskList = taskRepository.getTaskList(organization, documentType.name());
		entity.setTask(taskList.get(0));
	}

	private void enrichDetail(TraDetailEntity detail, TraDocumentEntity document, String user, BigDecimal itemDetailCount) {
		detail.setBaseDetailAmount(new BigDecimal(250));

		DepartmentEntity department = departmentRepository.findByOrganizationAndGroup(document.getOrganization().getId(), EnumList.OrgDepartmentGroupEnum.S).get(0);
		detail.setDepartment(department);

		detail.setDocument(document);

		DefItemEntity item = itemDao.requestItems(new ItemSearchCriteria(document.getOrganization().getId(), EnumList.DefTypeEnum.ITM_SR_ST, EnumList.DefItemClassEnum.STK_M)).get(0);

		detail.setItem(item);
		detail.setLotDetailDate(document.getDateCreated());
		detail.setItemDetailCount(itemDetailCount);

		detail.setItemUnit(item.getItemUnitSet().iterator().next().getUnitCode());

	}

	public CreateDocumentEvent newDocument(String user, String organization, EnumList.DefTypeEnum documentType) {
		StkDocumentEntity document = new StkDocumentEntity();

		enrichDocument(document, organization, documentType); 

		CreateDocumentEvent event = new CreateDocumentEvent(document, user, organization,"#.#:2014");

		return event;
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CreateDetailEvent newDetail(TraDocumentEntity document, String user, BigDecimal itemDetailCount) {
		StkDetailEntity detail = new StkDetailEntity();

		enrichDetail(detail, document, user, itemDetailCount);
		detail.setDetNote("Stok not");
		detail.setBatchDetailNo("BathNo");

		return new CreateDetailEvent(detail, user);
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CreateDetailEvent newTransfer(TraDocumentEntity transferDocument, String user, BigDecimal bigDecimal) {
		CreateDetailEvent createDetailEvent = this.newDetail(transferDocument, user, bigDecimal);
		
		StkDetailEntity stkDetailEntity = (StkDetailEntity)createDetailEvent.getDetail();
		
		DepartmentEntity departmentOpp = departmentRepository.findByOrganizationAndGroup(transferDocument.getOrganization().getId(), EnumList.OrgDepartmentGroupEnum.S).get(1);
		stkDetailEntity.setDepartmentOpp(departmentOpp);
		
		return createDetailEvent;
	}

}
