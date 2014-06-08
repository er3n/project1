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

public interface TraTransactionHandler {
	
	ReadDocumentEvent readDocument(RequestReadDocumentEvent event);
	DocumentCreatedEvent newDocument(CreateDocumentEvent event);
	DocumentUpdatedEvent updateDocument(UpdateDocumentEvent event) throws UnableToUpdateDocumentExpception;
	DocumentDeletedEvent deleteDocument(DeleteDocumentEvent event) throws UnableToDeleteDocumentException;
	
	ReadDetailEvent readDetail(RequestReadDetailEvent event);
	DetailCreatedEvent newDetail(CreateDetailEvent event);
	DetailUpdatedEvent updateDetail(UpdateDetailEvent evet) throws UnableToUpdateDetailException;
	DetailDeletedEvent deleteDetail(DeleteDetailEvent event) throws UnableToDeleteDetailException;
	
}
