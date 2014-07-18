package org.abacus.transaction.core.handler;

import java.util.List;

import org.abacus.transaction.shared.entity.ReqDocumentEntity;
import org.abacus.transaction.shared.event.ConfirmDocumentEvent;
import org.abacus.transaction.shared.holder.ReqPurcVendorHolder;


public interface ReqConfirmationHandler {

	ReqDocumentEntity requestDocument(ReqDocumentEntity document, String user);

	ReqDocumentEntity confirmDocument(ConfirmDocumentEvent confirmDocumentEvent);

	ReqDocumentEntity cancelDocument(ReqDocumentEntity document, String user);

	void reviewDocument(ReqDocumentEntity document, String currentUserName);

	void partiallyDoneDocument(ReqDocumentEntity document, String currentUserName);

	void backToReviewDocument(ReqDocumentEntity document, String currentUserName);

	void backToRequestDocument(ReqDocumentEntity document, String currentUserName);


}
