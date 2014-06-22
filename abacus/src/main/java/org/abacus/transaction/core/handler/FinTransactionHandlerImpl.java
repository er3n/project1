package org.abacus.transaction.core.handler;

import java.util.List;





import org.abacus.transaction.core.persistance.FinTransactionDao;
import org.abacus.transaction.core.persistance.TransactionDao;
import org.abacus.transaction.core.persistance.repository.FinDetailRepository;
import org.abacus.transaction.shared.UnableToCreateDetailException;
import org.abacus.transaction.shared.entity.FinDetailEntity;
import org.abacus.transaction.shared.entity.FinDocumentEntity;
import org.abacus.transaction.shared.event.CreateDetailEvent;
import org.abacus.transaction.shared.event.DetailCreatedEvent;
import org.abacus.transaction.shared.event.ReadDetailEvent;
import org.abacus.transaction.shared.event.RequestReadDetailEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("finTransactionHandler")
public class FinTransactionHandlerImpl extends TraTransactionSupport<FinDocumentEntity, FinDetailEntity> {

	@Autowired
	private FinDetailRepository finDetailRepository;
	
	@Autowired
	private FinTransactionDao finTransactionDao;

	@Override
	public ReadDetailEvent<FinDetailEntity> readDetail(RequestReadDetailEvent<FinDetailEntity> event) {
		List<FinDetailEntity> details = finDetailRepository.findByDocumentId(event.getDocumentId());
		return new ReadDetailEvent<FinDetailEntity>(details);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public DetailCreatedEvent<FinDetailEntity> newDetail(CreateDetailEvent<FinDetailEntity> detailCreateEvent) throws UnableToCreateDetailException {
		Integer trStateDetail = detailCreateEvent.getDetail().getDocument().getTrStateDocument() * detailCreateEvent.getDetail().getDocument().getTypeEnum().getState();
		detailCreateEvent.getDetail().setTrStateDetail(trStateDetail);
		
		DetailCreatedEvent<FinDetailEntity> detailCreatedEvent=null;
		detailCreatedEvent = super.newDetail(detailCreateEvent);
		return detailCreatedEvent;
	}

	@Override
	protected TransactionDao<FinDocumentEntity, FinDetailEntity> getTransactionDao() {
		return finTransactionDao;
	}
}
