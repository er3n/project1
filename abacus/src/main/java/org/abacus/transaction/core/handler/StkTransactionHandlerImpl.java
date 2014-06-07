package org.abacus.transaction.core.handler;

import org.abacus.transaction.shared.UnableToDeleteDetailException;
import org.abacus.transaction.shared.UnableToDeleteDocumentException;
import org.abacus.transaction.shared.UnableToUpdateDetailException;
import org.abacus.transaction.shared.UnableToUpdateDocumentExpception;
import org.abacus.transaction.shared.event.CreateDetailEvent;
import org.abacus.transaction.shared.event.CreateDocumentEvent;
import org.abacus.transaction.shared.event.DeleteDetailEvent;
import org.abacus.transaction.shared.event.DeleteDocumentEvent;
import org.abacus.transaction.shared.event.DetailCreatedEvent;
import org.abacus.transaction.shared.event.DetailDeletedEvent;
import org.abacus.transaction.shared.event.DetailUpdatedEvent;
import org.abacus.transaction.shared.event.DocumentCreatedEvent;
import org.abacus.transaction.shared.event.DocumentDeletedEvent;
import org.abacus.transaction.shared.event.DocumentUpdatedEvent;
import org.abacus.transaction.shared.event.ReadDetailEvent;
import org.abacus.transaction.shared.event.ReadDocumentEvent;
import org.abacus.transaction.shared.event.RequestReadDetailEvent;
import org.abacus.transaction.shared.event.RequestReadDocumentEvent;
import org.abacus.transaction.shared.event.UpdateDetailEvent;
import org.abacus.transaction.shared.event.UpdateDocumentEvent;
import org.springframework.stereotype.Service;

@SuppressWarnings("serial")
@Service("stkTransactionHandler")
public class StkTransactionHandlerImpl implements StkTransactionHandler {

	@Override
	public ReadDocumentEvent readDocument(RequestReadDocumentEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DocumentCreatedEvent newDocument(CreateDocumentEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DocumentUpdatedEvent updateDocument(UpdateDocumentEvent event)
			throws UnableToUpdateDocumentExpception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DocumentDeletedEvent deleteDocument(DeleteDocumentEvent event)
			throws UnableToDeleteDocumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReadDetailEvent readDetail(RequestReadDetailEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DetailCreatedEvent newDetail(CreateDetailEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DetailUpdatedEvent updateDetail(UpdateDetailEvent evet)
			throws UnableToUpdateDetailException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DetailDeletedEvent deleteDetail(DeleteDetailEvent event)
			throws UnableToDeleteDetailException {
		// TODO Auto-generated method stub
		return null;
	}

}
