package org.abacus.transaction.core.handler;

import org.abacus.transaction.shared.UnableToCreateDetailException;
import org.abacus.transaction.shared.UnableToDeleteDetailException;
import org.abacus.transaction.shared.UnableToDeleteDocumentException;
import org.abacus.transaction.shared.UnableToUpdateDetailException;
import org.abacus.transaction.shared.UnableToUpdateDocumentExpception;
import org.abacus.transaction.shared.entity.TraDetailEntity;
import org.abacus.transaction.shared.entity.TraDocumentEntity;
import org.abacus.transaction.shared.event.CancelDocumentEvent;
import org.abacus.transaction.shared.event.CreateDetailEvent;
import org.abacus.transaction.shared.event.CreateDocumentEvent;
import org.abacus.transaction.shared.event.DeleteDetailEvent;
import org.abacus.transaction.shared.event.DeleteDocumentEvent;
import org.abacus.transaction.shared.event.DetailCreatedEvent;
import org.abacus.transaction.shared.event.DetailDeletedEvent;
import org.abacus.transaction.shared.event.DetailUpdatedEvent;
import org.abacus.transaction.shared.event.DocumentCanceledEvent;
import org.abacus.transaction.shared.event.DocumentCreatedEvent;
import org.abacus.transaction.shared.event.DocumentDeletedEvent;
import org.abacus.transaction.shared.event.DocumentUpdatedEvent;
import org.abacus.transaction.shared.event.ReadDetailEvent;
import org.abacus.transaction.shared.event.ReadDocumentEvent;
import org.abacus.transaction.shared.event.RequestReadDetailEvent;
import org.abacus.transaction.shared.event.RequestReadDocumentEvent;
import org.abacus.transaction.shared.event.UpdateDetailEvent;
import org.abacus.transaction.shared.event.UpdateDocumentEvent;

public interface TraTransactionHandler<T extends TraDocumentEntity, D extends TraDetailEntity> {
	
	ReadDocumentEvent<T> readDocument(RequestReadDocumentEvent<T> event);
	DocumentCreatedEvent<T> newDocument(CreateDocumentEvent<T> event);
	DocumentUpdatedEvent<T> updateDocument(UpdateDocumentEvent<T> event) throws UnableToUpdateDocumentExpception;
	DocumentDeletedEvent<T> deleteDocument(DeleteDocumentEvent<T> event) throws UnableToDeleteDocumentException;
	
	ReadDetailEvent<D> readDetail(RequestReadDetailEvent<D> event);
	DetailCreatedEvent<D> newDetail(CreateDetailEvent<D> event) throws UnableToCreateDetailException;
	DetailUpdatedEvent<D> updateDetail(UpdateDetailEvent<D> event) throws UnableToUpdateDetailException;
	DetailDeletedEvent<D> deleteDetail(DeleteDetailEvent<D> event) throws UnableToDeleteDetailException;
	DocumentCanceledEvent cancelDocument(CancelDocumentEvent cancelDocumentEvent)  throws UnableToUpdateDocumentExpception;;
	
}
