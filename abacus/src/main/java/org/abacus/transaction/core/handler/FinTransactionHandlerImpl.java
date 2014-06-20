package org.abacus.transaction.core.handler;

import java.util.List;

import org.abacus.transaction.core.persistance.repository.FinDetailRepository;
import org.abacus.transaction.shared.entity.FinDetailEntity;
import org.abacus.transaction.shared.entity.FinDocumentEntity;
import org.abacus.transaction.shared.event.ReadDetailEvent;
import org.abacus.transaction.shared.event.RequestReadDetailEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("finTransactionHandler")
public class FinTransactionHandlerImpl extends TraTransactionSupport<FinDocumentEntity, FinDetailEntity> {

	@Autowired
	private FinDetailRepository finDetailRepository;  

	@Override
	public ReadDetailEvent<FinDetailEntity> readDetail(RequestReadDetailEvent<FinDetailEntity> event) {
		List<FinDetailEntity> details = finDetailRepository.findByDocumentId(event.getDocumentId());
		return new ReadDetailEvent<FinDetailEntity>(details);
	}
	
}
