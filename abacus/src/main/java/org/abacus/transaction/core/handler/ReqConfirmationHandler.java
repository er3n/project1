package org.abacus.transaction.core.handler;

import org.abacus.transaction.shared.entity.ReqDocumentEntity;
import org.abacus.transaction.shared.event.ConfirmDocumentEvent;


public interface ReqConfirmationHandler {

	ReqDocumentEntity requestDocument(ReqDocumentEntity document, String user);

	ReqDocumentEntity confirmDocument(ConfirmDocumentEvent confirmDocumentEvent);

	ReqDocumentEntity cancelDocument(ReqDocumentEntity document, String user);

	void reviewDocument(ReqDocumentEntity document, String currentUserName);

	void partiallyDoneDocument(ReqDocumentEntity document, String currentUserName);

	void backToReviewDocument(ReqDocumentEntity document, String currentUserName);

	void backToRequestDocument(ReqDocumentEntity document, String currentUserName);

}
