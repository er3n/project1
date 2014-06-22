package org.abacus.transaction.core.persistance;

import org.abacus.transaction.shared.entity.FinDetailEntity;
import org.abacus.transaction.shared.entity.FinDocumentEntity;
import org.abacus.transaction.shared.entity.StkDetailEntity;
import org.abacus.transaction.shared.entity.StkDocumentEntity;
import org.abacus.transaction.shared.entity.TraDocumentEntity;
import org.springframework.stereotype.Service;



@Service
public class FinTransactionDao extends TransactionDao<FinDocumentEntity, FinDetailEntity> {

	public Class getDocumentClass(){
		return FinDocumentEntity.class;
	}


}
