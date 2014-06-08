package org.abacus.test.fixture;

import java.util.Calendar;
import java.util.List;

import org.abacus.definition.core.persistance.repository.DefTaskRepository;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.entity.DefTaskEntity;
import org.abacus.organization.shared.entity.FiscalPeriodEntity;
import org.abacus.transaction.shared.entity.StkDocumentEntity;
import org.abacus.transaction.shared.entity.TraDocumentEntity;
import org.abacus.transaction.shared.event.CreateDocumentEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionFixture {

	@Autowired
	private DefTaskRepository taskRepository;
	
	private void enrichDocument(TraDocumentEntity entity, String organization){
		entity.setDocDate(Calendar.getInstance().getTime());
		entity.setDocNo("123456");
		entity.setDocNote("New stock item added");
		entity.setFiscalPeriod(new FiscalPeriodEntity("#.#:2014:01"));
		List<DefTaskEntity> taskList = taskRepository.getTaskList(organization, EnumList.DefTypeEnum.STK_IO_I.name());
		entity.setTask(taskList.get(0));
		entity.setTypeEnum(EnumList.DefTypeEnum.STK_IO_I);
	}
	
	public CreateDocumentEvent newDocument(String user, String organization) {
		StkDocumentEntity document = new StkDocumentEntity();
		
		enrichDocument(document,organization);
		
		CreateDocumentEvent event = new CreateDocumentEvent(document,user,organization);
		
		return event;
	}

}
