package org.abacus.transaction.core.handler;

import org.abacus.transaction.shared.entity.FinDocumentEntity;

public interface TraIntegrationHandler {
	
	FinDocumentEntity createFinFromStk(Long docId);  
	
}
